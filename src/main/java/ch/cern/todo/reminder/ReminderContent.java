package ch.cern.todo.reminder;

import ch.cern.todo.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record ReminderContent(@NotNull @NotBlank String title, @NotNull Instant date, Category category) {
}
