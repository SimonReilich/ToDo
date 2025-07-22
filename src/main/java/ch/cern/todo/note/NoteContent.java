package ch.cern.todo.note;

import ch.cern.todo.Category;
import ch.cern.todo.reminder.Reminder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record NoteContent(@NotNull @NotBlank String name, @NotNull @NotBlank String description, @NotNull Set<Reminder> reminders, @NotNull Category category) {
}
