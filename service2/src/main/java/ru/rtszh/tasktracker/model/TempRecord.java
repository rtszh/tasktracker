package ru.rtszh.tasktracker.model;

import lombok.Builder;

@Builder
public record TempRecord(
//        long id,
//        long userId,
        long chatId,
        int unixDate,
        String actionType
) {
}
