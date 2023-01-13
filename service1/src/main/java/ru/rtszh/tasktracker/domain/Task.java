package ru.rtszh.tasktracker.domain;

public record Task(String userLogin, String taskTitle, String taskDescription, String actionType) {

}
