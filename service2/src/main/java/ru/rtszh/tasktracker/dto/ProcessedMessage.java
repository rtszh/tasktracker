package ru.rtszh.tasktracker.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProcessedMessage {
    private final ConsumedMessage consumedMessage;
    private boolean isProcessed;

}
