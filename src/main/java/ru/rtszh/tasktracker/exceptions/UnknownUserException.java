package ru.rtszh.tasktracker.exceptions;

public class UnknownUserException extends RuntimeException {
    public UnknownUserException(String s) {
        super(s);
    }
}
