package ru.rtszh.tasktracker.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app-kafka")
@Data
public class KafkaProperties {
    private String topicName;
    private int partitions;
    private int replicas;
}
