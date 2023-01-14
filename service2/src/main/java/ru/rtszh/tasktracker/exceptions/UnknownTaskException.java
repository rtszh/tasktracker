package ru.rtszh.tasktracker.exceptions;

public class UnknownTaskException extends RuntimeException {
    public UnknownTaskException(String s) {
        super(s);
    }
}
