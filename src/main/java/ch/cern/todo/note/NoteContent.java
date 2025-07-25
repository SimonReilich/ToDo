package ch.cern.todo.note;

import ch.cern.todo.Category;
import ch.cern.todo.reminder.Reminder;
import ch.cern.todo.tag.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

import java.util.Set;

public record NoteContent(@NotNull @NotBlank String name, @NotNull @NotBlank String description, @NotNull Set<Reminder> reminders, @NotNull Category category, @Nullable Tag tag) {
}
