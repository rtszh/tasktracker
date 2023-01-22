package ru.rtszh.tasktracker.service;

import ru.rtszh.tasktracker.model.TempRecord;

import java.util.Optional;

public interface ActionsCacheService {
    void addRecord(TempRecord tempRecord);

    Optional<TempRecord> getRecord(long chatId);

    void deleteRecord(TempRecord tempRecord);

    void deleteRecord(long chatId);
}
