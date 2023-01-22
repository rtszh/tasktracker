package ru.rtszh.tasktracker.telegramclient.botcommands.impl;

import org.springframework.stereotype.Component;
import ru.rtszh.tasktracker.telegramclient.botcommands.BotCommandWrapper;

@Component
public class StartBotCommandWrapper extends BotCommandWrapper {
    private static final String COMMAND_NAME = "/start";
    private static final String COMMAND_DESCRIPTION = "get a welcome message";


    public StartBotCommandWrapper() {
        botCommand.setCommand(COMMAND_NAME);
        botCommand.setDescription(COMMAND_DESCRIPTION);
    }
}
