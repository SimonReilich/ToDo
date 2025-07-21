package ch.cern.todo.reminder;

import ch.cern.todo.Category;

import java.time.Instant;

public record ReminderContent(String title, Instant date, Category category) {
}
