package ru.rtszh.tasktracker.service.impl;

import org.springframework.stereotype.Component;
import ru.rtszh.tasktracker.model.TempRecord;
import ru.rtszh.tasktracker.service.ActionsCacheService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ActionsCacheServiceImpl implements ActionsCacheService {

    private final List<TempRecord> tempRecordList = new ArrayList<>();


    @Override
    public void addRecord(TempRecord tempRecord) {
        tempRecordList.add(tempRecord);
    }

    @Override
    public Optional<TempRecord> getRecord(long chatId) {
        return tempRecordList.stream()
                .filter(tempRecord -> tempRecord.chatId() == chatId)
                .findFirst();
    }

    @Override
    public void deleteRecord(TempRecord tempRecord) {
        tempRecordList.remove(tempRecord);
    }

    @Override
    public void deleteRecord(long chatId) {
        var optionalTempRecord = getRecord(chatId);

        optionalTempRecord.ifPresent(this::deleteRecord);
    }
}
