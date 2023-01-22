package ru.rtszh.tasktracker.processors.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.rtszh.tasktracker.processors.MessageSender;
import ru.rtszh.tasktracker.properties.KafkaProperties;

@Service
@Slf4j
public class KafkaMessageSender implements MessageSender {

    private final KafkaProperties kafkaProperties;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaMessageSender(KafkaProperties kafkaProperties, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaProperties = kafkaProperties;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> String sendMessage(T message) {
        String messageAsString;

        try {
            messageAsString = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        kafkaTemplate.send(
                kafkaProperties.getTopicName(),
                messageAsString
        );

        log.info("message sent: {}", messageAsString);

        return "message sent: " + messageAsString;
    }


}
