package ru.rtszh.tasktracker.processors.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.rtszh.tasktracker.model.ActionTypesWithWaiting;
import ru.rtszh.tasktracker.model.ActionTypesWithoutWaiting;
import ru.rtszh.tasktracker.dto.ConsumedMessage;
import ru.rtszh.tasktracker.processors.ActionTypeProcessor;
import ru.rtszh.tasktracker.processors.MessageProcessor;
import ru.rtszh.tasktracker.service.ActionsCacheService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static ru.rtszh.tasktracker.factories.TempRecordFactory.createTempRecord;

@Component
@Slf4j
public class MessageProcessorImpl implements MessageProcessor {

    private final ActionsCacheService actionsCacheService;
    private final ObjectMapper objectMapper;

    private final Map<String, ActionTypeProcessor> actionTypeMap;

    public MessageProcessorImpl(ActionsCacheService actionsCacheService, ObjectMapper objectMapper, Map<String, ActionTypeProcessor> actionTypeMap) {
        this.actionsCacheService = actionsCacheService;
        this.objectMapper = objectMapper;
        this.actionTypeMap = actionTypeMap;
    }

    @Override
    public void process(String message) {
        ConsumedMessage consumedMessage;

        try {
            consumedMessage = objectMapper.readValue(message, ConsumedMessage.class);
        } catch (Exception ex) {
            log.error("can't parse message:{}", message, ex);
            throw new RuntimeException("can't parse message:" + message);
        }

        process(consumedMessage);
    }

    private void process(ConsumedMessage message) {

        if (isMessageContainsActionWithWaiting(message)) {
            var tempRecord = createTempRecord(message.message());
            actionsCacheService.addRecord(tempRecord);
            log.info("Command saved to cache: {}", tempRecord);
        } else if (isMessageContainsActionWithoutWaiting(message)) {
            processCommandWithoutWaiting(message);
        } else {
            processNotCommandText(message);
        }

    }

    private void processCommandWithoutWaiting(ConsumedMessage message) {

        var actionType = getActionTypeWithoutWaiting(message.message().getText());

        var actionTypeProcessor = actionTypeMap.get(actionType);

        actionsCacheService.deleteRecord(message.message().getChatId());

        actionTypeProcessor.process(message);
    }

    private void processNotCommandText(ConsumedMessage message) {

        var tempRecordOptional = actionsCacheService.getRecord(message.message().getChatId());
        String actionType;

        if (tempRecordOptional.isEmpty()) {
            actionType = ActionTypesWithoutWaiting.NO_ENTERED_COMMAND.getTypeName();
        } else {
            var tempRecord = tempRecordOptional.get();
            actionType = tempRecord.actionType();
            actionsCacheService.deleteRecord(tempRecord);
            log.info("Command deleted from cache: {}", tempRecord);
        }

        var actionTypeProcessor = actionTypeMap.get(actionType);
        actionTypeProcessor.process(message);
    }

    private String getActionTypeWithoutWaiting(String text) {
        return Arrays.stream(ActionTypesWithoutWaiting.values())
                .map(ActionTypesWithoutWaiting::getTypeName)
                .filter(actionTypeString -> actionTypeString.equals(text))
                .findFirst()
                .orElse(ActionTypesWithoutWaiting.NO_ENTERED_COMMAND.getTypeName());
    }

    private boolean isMessageContainsActionWithWaiting(ConsumedMessage message) {

        var entitiesOptional = Optional.ofNullable(
                message.message().getEntities()
        );

        var entities = entitiesOptional.orElse(new ArrayList<>());

        if (entities.size() == 0) {
            return false;
        }

        var botCommandEntities = entities.stream()
                .filter(messageEntity -> messageEntity.getType().equals("bot_command"))
                .toList();

        if (botCommandEntities.size() == 0) {
            return false;
        }

        var messageBody = message.message().getText();

        for (var actionType : ActionTypesWithWaiting.values()) {
            var actionTypeString = actionType.getTypeName();

            if (messageBody.equals(actionTypeString)) {
                return true;
            }
        }

        return false;
    }

    private boolean isMessageContainsActionWithoutWaiting(ConsumedMessage message) {

        var entitiesOptional = Optional.ofNullable(
                message.message().getEntities()
        );

        var entities = entitiesOptional.orElse(new ArrayList<>());

        if (entities.size() == 0) {
            return false;
        }

        var botCommandEntities = entities.stream()
                .filter(messageEntity -> messageEntity.getType().equals("bot_command"))
                .toList();

        if (botCommandEntities.size() == 0) {
            return false;
        }

        var messageBody = message.message().getText();

        for (var actionType : ActionTypesWithoutWaiting.values()) {
            var actionTypeString = actionType.getTypeName();

            if (messageBody.equals(actionTypeString)) {
                return true;
            }
        }

        return false;
    }
}
