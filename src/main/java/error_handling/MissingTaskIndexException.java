package error_handling;

public final class MissingTaskIndexException extends Exception {
    public MissingTaskIndexException(String message) {
        super(message);
    }
}
