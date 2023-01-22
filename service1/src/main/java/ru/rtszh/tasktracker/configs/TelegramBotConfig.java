package ru.rtszh.tasktracker.configs;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.rtszh.tasktracker.properties.ScheduleProperties;
import ru.rtszh.tasktracker.properties.TelegramBotProperties;
import ru.rtszh.tasktracker.telegramclient.TelegramClient;
import ru.rtszh.tasktracker.telegramclient.botcommands.BotCommandWrapper;

import java.util.List;

@Configuration
@EnableScheduling
@Data
@EnableConfigurationProperties({TelegramBotProperties.class, ScheduleProperties.class})
@Slf4j
@RequiredArgsConstructor
public class TelegramBotConfig {
    private final TelegramClient telegramClient;
    private final List<BotCommandWrapper> botCommandWrapperList;

    @EventListener({ContextRefreshedEvent.class})
    public void init() {
        registerBot();
        registerBotCommands();
    }

    private void registerBot() {
        try {
            var telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(telegramClient);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

    private void registerBotCommands() {
        var botCommandList = botCommandWrapperList.stream()
                .map(BotCommandWrapper::getBotCommand)
                .toList();
        try {
            telegramClient.execute(
                    new SetMyCommands(botCommandList, new BotCommandScopeDefault(), null)
            );
        } catch (TelegramApiException e) {
            log.error("Error setting bots command list: " + e.getMessage());
        }
    }
}
