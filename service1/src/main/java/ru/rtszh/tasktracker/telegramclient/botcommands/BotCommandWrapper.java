package ru.rtszh.tasktracker.telegramclient.botcommands;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public abstract class BotCommandWrapper {

    @Getter
    protected final BotCommand botCommand;

    public BotCommandWrapper() {
        this.botCommand = new BotCommand();
    }

}
