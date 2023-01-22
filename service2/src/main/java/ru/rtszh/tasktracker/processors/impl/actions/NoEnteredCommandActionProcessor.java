package ru.rtszh.tasktracker.processors.impl.actions;

import org.springframework.stereotype.Component;
import ru.rtszh.tasktracker.dto.ConsumedMessage;
import ru.rtszh.tasktracker.processors.ActionTypeProcessor;
import ru.rtszh.tasktracker.processors.MessageSender;

import static ru.rtszh.tasktracker.factories.ProducedMessageFactory.createProducedMessageWithBody;

@Component("/no_entered_command")
public class NoEnteredCommandActionProcessor implements ActionTypeProcessor {

    private final MessageSender messageSender;

    public NoEnteredCommandActionProcessor(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Override
    public void process(ConsumedMessage message) {
        long chatId = message.message().getChatId();

        sendMessageThatCommandWasNotEntered(chatId);
    }

    private void sendMessageThatCommandWasNotEntered(Long chatId) {
        messageSender.sendMessage(
                createProducedMessageWithBody(
                        chatId,
                        "First you need to select a command from the menu"
                )
        );
    }
}
