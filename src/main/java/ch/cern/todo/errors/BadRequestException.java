package ch.cern.todo.errors;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super("bad request");
    }
}
