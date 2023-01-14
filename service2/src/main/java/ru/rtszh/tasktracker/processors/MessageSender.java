package ru.rtszh.tasktracker.processors;

public interface MessageSender {
    <T> String sendMessage(T message);
}
