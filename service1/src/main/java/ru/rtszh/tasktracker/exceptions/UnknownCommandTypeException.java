package ru.rtszh.tasktracker.exceptions;

public class UnknownCommandTypeException extends UnsupportedOperationException {
    public UnknownCommandTypeException(String msg) {
        super(msg);
    }
}
