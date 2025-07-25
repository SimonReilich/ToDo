package ch.cern.todo.reminder;

import ch.cern.todo.Category;
import ch.cern.todo.tag.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

import java.time.Instant;

public record ReminderContent(@NotNull @NotBlank String title, @NotNull String date, Category category, @Nullable Tag tag) {
}
