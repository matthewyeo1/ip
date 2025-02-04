package lib;
import error_handling.InvalidTaskException;
import error_handling.MissingTaskIndexException;

public class TaskManager {
    private static final int MAX_TASKS = 100;
    private Task[] tasks;
    private int taskCount;

    public TaskManager() {
        tasks = new Task[MAX_TASKS];
        taskCount = 0;
    }

    public void showList() {
        if (taskCount == 0) {
            System.out.println("Your task list is empty.");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i].toString());
            }
        }
    }

    private Task createTask(String input) {
        TaskType taskType = determineTaskType(input);
        switch (taskType) {
            case DEADLINE:
                String[] deadlineParts = input.split("/by", 2);
                return new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
            case EVENT:
                String[] eventParts = input.split("/from", 2);
                String[] eventDates = eventParts[1].split("/to", 2);
                return new Event(eventParts[0].trim(), eventDates[0].trim(), eventDates[1].trim());
            case TODO:
                return new ToDo(input.trim());
            default:
                throw new IllegalArgumentException("Invalid input for task creation: " + input);
        }
    }

    private TaskType determineTaskType(String input) {
        if (input.contains("/by")) return TaskType.DEADLINE;
        if (input.contains("/from") && input.contains("/to")) return TaskType.EVENT;
        return TaskType.TODO;
    }

    public void addTask(String input) {
        if (input.isEmpty()) {
            System.out.println("Please enter a command.");
            return;
        }

        Task task = createTask(input);
        if (taskCount < MAX_TASKS) {
            tasks[taskCount++] = task;
            System.out.println("Added: " + task.getDescription());
            System.out.println("You have " + taskCount + numberOfTasks(taskCount) + " in your list.");
        } else {
            System.out.println("Error: Task list is full. Could not add: " + task.getDescription());
        }
    }

    private String numberOfTasks(int count) {
        return count == 1 ? " task" : " tasks";
    }

    public void handleMarkUnmark(String input) {
        try {
            if (input.toLowerCase().equals("mark") || input.toLowerCase().equals("unmark")) {
                throw new MissingTaskIndexException("Task index is missing."); 
            } else if (input.toLowerCase().startsWith("unmark ")) {
                unmarkTask(input);  
            } else if (input.toLowerCase().startsWith("mark ")) {
                markTask(input); 
            } else {
                addTask(input);
            }
        } catch (MissingTaskIndexException | InvalidTaskException e) {
            System.out.println(e.getMessage());
        }
    }

    public void markTask(String input) throws MissingTaskIndexException, InvalidTaskException {
        int taskId = extractTaskId(input, "mark ");
        validateTaskId(taskId);

        if (taskId > taskCount || taskId < 1) { 
            throw new InvalidTaskException("Task " + taskId + " does not exist.");
        }

        if (tasks[taskId - 1].getDoneStatus()) {
            System.out.println("Task " + taskId + " is already marked.");
        } else {
            tasks[taskId - 1].setDone(true);
            System.out.println("Marked task " + taskId + " as done.");
        }
    }

    public void unmarkTask(String input) throws MissingTaskIndexException, InvalidTaskException {
        int taskId = extractTaskId(input, "unmark ");
        validateTaskId(taskId);

        if (taskId > taskCount || taskId < 1) { 
            throw new InvalidTaskException("Task ID " + taskId + " does not exist.");
        }

        if (!tasks[taskId - 1].getDoneStatus()) {
            System.out.println("Task " + taskId + " is already unmarked.");
        } else {
            tasks[taskId - 1].setDone(false);
            System.out.println("Unmarked task " + taskId + ".");
        }
    }

    private int extractTaskId(String input, String prefix) throws MissingTaskIndexException, InvalidTaskException {
        String taskIdStr = input.substring(prefix.length()).trim();
        
        try {
            int taskId = Integer.parseInt(taskIdStr);
            return taskId;
        } catch (NumberFormatException e) {
            throw new InvalidTaskException("Invalid task index. Please enter a number.");
        }
    }

    private void validateTaskId(int taskId) throws InvalidTaskException {
        if (taskId < 1 || taskId > MAX_TASKS) {
            throw new InvalidTaskException("Task ID must be between 1 and " + MAX_TASKS + ".");
        }
    }
}

