package ru.rtszh.tasktracker.telegramclient.botcommands.impl;

import org.springframework.stereotype.Component;
import ru.rtszh.tasktracker.telegramclient.botcommands.BotCommandWrapper;

@Component
public class CreateTaskBotCommandWrapper extends BotCommandWrapper {
    private static final String COMMAND_NAME = "/create_task";
    private static final String COMMAND_DESCRIPTION = "Create new Task";


    public CreateTaskBotCommandWrapper() {
        botCommand.setCommand(COMMAND_NAME);
        botCommand.setDescription(COMMAND_DESCRIPTION);
    }
}
