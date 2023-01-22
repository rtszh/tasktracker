package ru.rtszh.tasktracker.dto;

import lombok.Builder;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import static java.util.Objects.isNull;

@Builder
public record TaskDto(
        @NonNull
        String title,
        @Nullable
        String description,
        @NonNull
        Integer orderNumber) {

    @Override
    public String toString() {

        if (isNull(description) || description.isBlank() || description.isEmpty()) {
            if (orderNumber == 0) {
                return title;
            } else {
                return String.format("%s (%d)", title, (orderNumber + 1));
            }
        } else {
            if (orderNumber == 0) {
                return String.format("%s, %s", title, description);
            } else {
                return String.format("%s (%d), %s", title, (orderNumber + 1), description);
            }
        }

    }
}

