package error_handling;

public class MissingTaskIndexException extends Exception {
    public MissingTaskIndexException(String message) {
        super(message);
    }
}
