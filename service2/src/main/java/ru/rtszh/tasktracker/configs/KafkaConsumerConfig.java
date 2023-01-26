package ru.rtszh.tasktracker.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import ru.rtszh.tasktracker.processors.MessageProcessor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@RequiredArgsConstructor
@Slf4j
@EnableKafka
public class KafkaConsumerConfig {

    private final MessageProcessor messageProcessor;

    private final ExecutorService executorService;


    @KafkaListener(groupId = "#{'${spring.kafka.consumer.group-id}'}", topics = "#{'${spring.kafka.template.default-topic}'}")
    public void taskRequestListen(String msgAsString) {
        log.info("message consumed {}", msgAsString);

        executorService.submit(
                () -> messageProcessor.process(msgAsString)
        );

    }

}
