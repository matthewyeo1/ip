package error_handling;

public final class InvalidTaskException extends Exception {
    public InvalidTaskException(String message) {
        super(message);
    }
}