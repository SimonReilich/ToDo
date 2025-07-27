package ch.cern.todo.reminder;

import ch.cern.todo.tag.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

public record ReminderContent(@NotNull @NotBlank String title, @NotNull String date, @Nullable Tag tag) {
}
