package ru.rtszh.tasktracker.telegramclient.botcommands.impl;

import org.springframework.stereotype.Component;
import ru.rtszh.tasktracker.telegramclient.botcommands.BotCommandWrapper;

@Component
public class DeleteTaskBotCommandWrapper extends BotCommandWrapper {
    private static final String COMMAND_NAME = "/delete_task";
    private static final String COMMAND_DESCRIPTION = "Delete specified task";


    public DeleteTaskBotCommandWrapper() {
        botCommand.setCommand(COMMAND_NAME);
        botCommand.setDescription(COMMAND_DESCRIPTION);
    }
}
