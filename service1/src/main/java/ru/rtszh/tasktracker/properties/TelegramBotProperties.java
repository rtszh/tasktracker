package ru.rtszh.tasktracker.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "bot")
public class TelegramBotProperties {

    private String botUserName;

    private String token;
}
