package ru.rtszh.tasktracker.model;

import lombok.Builder;

@Builder
public record RecordParams(
//        long userId,
        long chatId
) {
}
