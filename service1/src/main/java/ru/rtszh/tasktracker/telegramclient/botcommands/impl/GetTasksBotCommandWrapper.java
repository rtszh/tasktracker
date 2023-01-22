package ru.rtszh.tasktracker.telegramclient.botcommands.impl;

import org.springframework.stereotype.Component;
import ru.rtszh.tasktracker.telegramclient.botcommands.BotCommandWrapper;

@Component
public class GetTasksBotCommandWrapper extends BotCommandWrapper {
    private static final String COMMAND_NAME = "/get_tasks";
    private static final String COMMAND_DESCRIPTION = "Get all task";


    public GetTasksBotCommandWrapper() {
        botCommand.setCommand(COMMAND_NAME);
        botCommand.setDescription(COMMAND_DESCRIPTION);
    }
}
