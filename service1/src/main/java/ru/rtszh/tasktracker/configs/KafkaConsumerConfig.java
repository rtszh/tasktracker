package ru.rtszh.tasktracker.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import ru.rtszh.tasktracker.dto.messages.consuming.ReceivedMessage;
import ru.rtszh.tasktracker.processors.MessageProcessor;

@Configuration
@RequiredArgsConstructor
@Slf4j
@EnableKafka
public class KafkaConsumerConfig {

    private final ObjectMapper objectMapper;

    private final MessageProcessor messageProcessor;

    @KafkaListener(groupId = "#{'${spring.kafka.consumer.group-id}'}", topics = "#{'${spring.kafka.template.default-topic}'}")
    public void taskRequestListen(String msgAsString) {
        ReceivedMessage receivedMessage;

        try {
            receivedMessage = objectMapper.readValue(msgAsString, ReceivedMessage.class);
        } catch (Exception ex) {
            log.error("can't parse message:{}", msgAsString, ex);
            throw new RuntimeException("can't parse message:" + msgAsString);
        }

        log.info("message consumed {}", receivedMessage);

        messageProcessor.process(receivedMessage);
    }

}
