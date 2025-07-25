package ch.cern.todo.errors;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException(long id) {
        super("could not find tag with id " + id);
    }
}
