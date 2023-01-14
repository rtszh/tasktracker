package ru.rtszh.tasktracker.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import ru.rtszh.tasktracker.dto.Message;
import ru.rtszh.tasktracker.processors.MessageProcessor;

@Configuration
@RequiredArgsConstructor
@Slf4j
@EnableKafka
public class KafkaConsumerConfig {

    private final ObjectMapper objectMapper;

    private final MessageProcessor messageProcessor;

    private static final String DEFAULT_GROUP_ID = "gateway";
    private static final String DEFAULT_TOPIC = "taskManager.request";

    @KafkaListener(groupId = DEFAULT_GROUP_ID, topics = DEFAULT_TOPIC)
    public void taskRequestListen(String msgAsString) {
        Message message;

        try {
            message = objectMapper.readValue(msgAsString, Message.class);
        } catch (Exception ex) {
            log.error("can't parse message:{}", msgAsString, ex);
            throw new RuntimeException("can't parse message:" + msgAsString);
        }

        log.info("message consumed {}", message);

        messageProcessor.process(message);
    }


}
