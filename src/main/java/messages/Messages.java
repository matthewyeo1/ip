package messages;

public final class Messages {

    public void emptyListMessage() {
        System.out.println("Your task list is empty.");
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

    public void numberOfTasksInListMessage(int listSize, String taskOrTasks) {
        System.out.println("You have " + listSize + taskOrTasks + " in your list.");
    }

    public String missingTaskIndexMessage() {
        return "ERROR: Task index is missing.";
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
}
