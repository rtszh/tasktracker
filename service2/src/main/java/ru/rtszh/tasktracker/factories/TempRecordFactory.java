package ru.rtszh.tasktracker.factories;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.rtszh.tasktracker.model.TempRecord;

public class TempRecordFactory {
    public static TempRecord createTempRecord(Message message) {
        return TempRecord.builder()
                .chatId(message.getChatId())
                .unixDate(message.getDate())
                .actionType(message.getText())
                .build();
    }
}
