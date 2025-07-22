package ch.cern.todo.errors;

public class ReminderNotFoundException extends RuntimeException {
    public ReminderNotFoundException(long id) {
        super("could not find reminder with id " + id);
    }
}
