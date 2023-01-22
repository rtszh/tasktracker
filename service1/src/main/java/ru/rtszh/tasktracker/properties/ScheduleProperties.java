package ru.rtszh.tasktracker.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "schedule")
public class ScheduleProperties {

    private String cron;
}
