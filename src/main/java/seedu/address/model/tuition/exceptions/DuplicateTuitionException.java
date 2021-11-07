package seedu.address.model.tuition.exceptions;

public class DuplicateTuitionException extends RuntimeException {
    public DuplicateTuitionException() {
        super("Operation would result in duplicate tuition classes");
    }
}
