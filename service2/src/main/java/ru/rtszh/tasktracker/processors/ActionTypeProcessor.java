package ru.rtszh.tasktracker.processors;

import ru.rtszh.tasktracker.dto.ConsumedMessage;

public interface ActionTypeProcessor {
    void process(ConsumedMessage message);
}
