package ch.cern.todo.errors;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(long id) {
        super("could not find note with id " + id);
    }
}
