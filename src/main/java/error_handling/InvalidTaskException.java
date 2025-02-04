package error_handling;

public class InvalidTaskException extends Exception {
    public InvalidTaskException(String message) {
        super(message);
    }
}