package messages;

/**
 * The <code>Messages</code> class provides predefined messages for user interactions.
 * It contains methods to display system messages related to task management, such as 
 * errors, confirmations, and status updates.
 *
 * <p>This class is designed to standardize message outputs and improve code readability.</p>
 */
public final class Messages {

    public void emptyListMessage() {
        System.out.println("Your task list is empty.");
    }

    public String taskIndex(int index) {
        return index + 1 + ". ";
    }

    public String invalidTaskTypeMessage() {
        return "ERROR: Invalid input for task creation.";
    }

    public void emptyInputMessage() {
        System.out.println("Please enter a command.");
    }

    public void fullTaskListMessage() {
        System.out.println("Task list is full.");
    }

    public void addedTaskSuccessfullyMessage(String taskDescription) {
        System.out.println("Added: " + taskDescription);
    }

    public void numberOfTasksInListMessage(int listSize) {
        if (listSize > 1) {
            System.out.println("You have " + listSize + " tasks in your list.");
        } else {
            System.out.println("You have " + listSize + " task in your list.");
        }
    }

    public void unknownTaskErrorMessage(String keyword) {
        System.out.println("ERROR: No task containing " + "\"" + keyword + "\"" + " exists.");
    }

    public String missingTaskIndexMessage() {
        return "ERROR: Task index is missing.";
    }

    public String missingKeywordMessage() {
        return "ERROR: Keyword is missing.";
    }
    
    public String nonexistentTaskIndex(int taskId) {
        return "ERROR: Task " + taskId + " does not exist.";
    }

    public void taskAlreadyMarkedMessage(int taskId) {
        System.out.println("Task " + taskId + " is already marked.");
    }

    public void markedTaskSuccessfullyMessage(int taskId) {
        System.out.println("Marked task " + taskId + " as done.");
    }

    public void taskAlreadyUnmarkedMessage(int taskId) {
        System.out.println("Task " + taskId + " is already unmarked.");
    }

    public void unmarkedTaskSuccessfullyMessage(int taskId) {
        System.out.println("Unmarked task " + taskId + ".");
    }

    public String invalidTaskIdMessage() {
        return "ERROR: Invalid task index. Please enter a number.";
    }

    public String taskIdOutOfBoundsMessage() {
        return "ERROR: Task ID must be between 1 and 100.";
    }

    public void deleteTaskSuccessfullyMessage(int taskId) {
        System.out.println("Task " + taskId + " removed successfully.");
    }

    public void exitMessage() {
        System.out.println("Have a good day.");
    }

    public String indexOutOfBoundsMessage() {
        return "ERROR: That is an awfully large task ID";
    }

    public String saveTaskErrorMessage() {
        return "Error saving tasks: ";
    }

    public static void missingTaskFileMessage() {
        System.out.println("Task file not found, creating new one...");
    }

    public static String loadTaskErrorMessage() {
        return "Error loading tasks: ";
    }

    public static String updateTaskFileError() {
        return "Error updating tasks file: ";
    }
}
