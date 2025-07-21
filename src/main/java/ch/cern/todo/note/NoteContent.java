package ch.cern.todo.note;

import ch.cern.todo.Category;
import ch.cern.todo.reminder.Reminder;

import java.util.Set;

public record NoteContent(String name, String description, Set<Reminder> reminders, Category category) {
}
