package lib;

import error_handling.InvalidTaskException;
import error_handling.MissingTaskIndexException;
import error_handling.MissingKeywordException;
import commands.Commands;
import java.util.ArrayList;
import messages.Messages;
import data_storage.TaskFileHandler;

/**
 * Manages a list of tasks, allowing users to add, delete, find, and modify tasks.
 */
public class TaskManager {
    private static final int MAX_TASKS = 100;
    private ArrayList<Task> tasks;
    private TaskFileHandler fileHandler;
    Messages messages = new Messages();

    /**
     * Initializes TaskManager by loading tasks from storage.
     */
    public TaskManager() {
        this.fileHandler = new TaskFileHandler();
        this.tasks = TaskFileHandler.loadTasks();
    }

    public String setSpacing() {
        return " ";
    }

    public void showList() {
        if (tasks.isEmpty()) {
            messages.emptyListMessage();
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(messages.taskIndex(i) + tasks.get(i).toString());
            }
        }
    }

    /**
     * Instantiates the corresponding subclass of the Task class according to certain
     * identifiers in the user input string (task description).
     * 
     * @param input The task description which may contain the subclass identifiers.
     */
    private Task createTask(String input) {
        TaskType taskType = determineTaskType(input);
        switch (taskType) {
            case DEADLINE:
                String[] deadlineParts = input.split(Commands.TASK_BY, 2);
                return new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
            case EVENT:
                String[] eventParts = input.split(Commands.TASK_FROM, 2);
                String[] eventDates = eventParts[1].split(Commands.TASK_TO, 2);
                return new Event(eventParts[0].trim(), eventDates[0].trim(), eventDates[1].trim());
            case TODO:
                return new ToDo(input.trim());
            default:
                throw new IllegalArgumentException(messages.invalidTaskTypeMessage() + input);
        }
    }

    /**
     * Finds and displays tasks containing the specified keyword.
     * 
     * @param input The user input containing the keyword.
     */
    public void findTask(String input) {
        String keyword = extractKeyword(input, Commands.FIND + setSpacing());

        if (keywordExists(keyword, tasks)) {
            displayMatchingTasks(keyword, tasks);
        } else {
            messages.unknownTaskErrorMessage(keyword);
        }
    }

    Boolean keywordExists(String keyword, ArrayList<Task> tasks) {
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    void displayMatchingTasks(String keyword, ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            messages.emptyListMessage();
        } else {
            int i = 0;
            for (Task task : tasks) {
                if (task.getDescription().toLowerCase().contains(keyword)) {
                    System.out.println(messages.taskIndex(i) + tasks.get(i).toString());
                    i++;
                }
            }
        }
    }

    String extractKeyword(String input, String prefix) {
        return input.substring(prefix.length()).trim();
    }

    private TaskType determineTaskType(String input) {
        if (input.contains(Commands.TASK_BY)) {
            return TaskType.DEADLINE;
        }

        if (input.contains(Commands.TASK_FROM) && input.contains(Commands.TASK_TO)) {
            return TaskType.EVENT;
        }
        
        return TaskType.TODO;
    }

    /**
     * Adds a new task to the task list based on the user input.
     * 
     * @param input The user input specifying the task details.
     */
    public void addTask(String input) {
        if (input.isEmpty()) {
            messages.emptyInputMessage();
            return;
        }

        if (tasks.size() >= MAX_TASKS) {
            messages.fullTaskListMessage();
            return;
        }

        Task task = createTask(input);
        tasks.add(task);
        fileHandler.saveTasks(tasks);
        messages.addedTaskSuccessfullyMessage(task.getDescription());
        messages.numberOfTasksInListMessage(tasks.size());
    }

    public void handleMarkUnmark(String input) {
        try {
            if (input.toLowerCase().equals(Commands.MARK) || input.toLowerCase().equals(Commands.UNMARK)) {
                throw new MissingTaskIndexException(messages.missingTaskIndexMessage()); 
            } else if (input.toLowerCase().startsWith(Commands.UNMARK + setSpacing())) {
                unmarkTask(input);  
            } else if (input.toLowerCase().startsWith(Commands.MARK + setSpacing())) {
                markTask(input); 
            }
            fileHandler.saveTasks(tasks);
        } catch (MissingTaskIndexException | InvalidTaskException e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleFind(String input) {
        try {
            if (input.toLowerCase().equals(Commands.FIND)) {
                throw new MissingKeywordException(messages.missingKeywordMessage());
            }
            findTask(input);
        } catch (MissingKeywordException e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleDelete(String input) {
        try {
            if (input.toLowerCase().equals(Commands.DELETE)) {
                throw new MissingTaskIndexException(messages.missingTaskIndexMessage()); 
            } else if (input.toLowerCase().startsWith(Commands.DELETE + setSpacing())) {
                deleteTask(input);  
            }
            fileHandler.saveTasks(tasks);
        } catch (MissingTaskIndexException | InvalidTaskException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Marks a task as completed. Marked tasks will be displayed 
     * in the task list with and X beside them.
     * 
     * @param input The user input containing the task index to mark.
     * @throws MissingTaskIndexException if the index is missing.
     * @throws InvalidTaskException if the task ID is invalid.
     */
    public void markTask(String input) throws MissingTaskIndexException, InvalidTaskException {
        int taskId = extractTaskId(input, Commands.MARK + setSpacing());
        validateTaskId(taskId);
        
        if (taskId > tasks.size() || taskId < 1) { 
            throw new InvalidTaskException(messages.nonexistentTaskIndex(taskId));
        }

        if (tasks.get(taskId - 1).getDoneStatus()) {
            messages.taskAlreadyMarkedMessage(taskId);
        } else {
            tasks.get(taskId - 1).setDone(true);
            fileHandler.saveTasks(tasks);
            fileHandler.updateTaskFile(tasks);
            messages.markedTaskSuccessfullyMessage(taskId);
        }
    }

    /**
     * Unmarks a completed task. Tasks that were previously marked
     * will have the X removed in the task list.
     * 
     * @param input The user input containing the task index to unmark.
     * @throws MissingTaskIndexException if the index is missing.
     * @throws InvalidTaskException if the task ID is invalid.
     */
    public void unmarkTask(String input) throws MissingTaskIndexException, InvalidTaskException {
        int taskId = extractTaskId(input, Commands.UNMARK + setSpacing());
        validateTaskId(taskId);
        
        if (taskId > tasks.size() || taskId < 1) { 
            throw new InvalidTaskException(messages.nonexistentTaskIndex(taskId));
        }

        if (!tasks.get(taskId - 1).getDoneStatus()) {
            messages.taskAlreadyUnmarkedMessage(taskId);
        } else {
            tasks.get(taskId - 1).setDone(false);
            fileHandler.saveTasks(tasks);
            fileHandler.updateTaskFile(tasks);
            messages.unmarkedTaskSuccessfullyMessage(taskId);
        }
    }

    private int extractTaskId(String input, String prefix) throws MissingTaskIndexException, InvalidTaskException {
        String taskIdStr = input.substring(prefix.length()).trim();
        
        try {
            int taskId = Integer.parseInt(taskIdStr);
            return taskId;
        } catch (NumberFormatException e) {
            if (taskIdStr.length() > 10 || taskIdStr.length() < 1) {
                throw new IndexOutOfBoundsException(messages.indexOutOfBoundsMessage());
            }
            throw new InvalidTaskException(messages.invalidTaskIdMessage());
        }
    }

    /**
     * Validates if the given task ID is within the allowed range.
     * 
     * @param taskId The task ID to validate.
     * @throws InvalidTaskException if the ID is out of range.
     */
    private void validateTaskId(int taskId) throws InvalidTaskException {
        if (taskId < 1 || taskId > MAX_TASKS) {
            throw new InvalidTaskException(messages.taskIdOutOfBoundsMessage());
        }
    }

    /**
     * Deletes a task from the list.
     * 
     * @param input The user input containing the task index to delete.
     * @throws MissingTaskIndexException if the index is missing.
     * @throws InvalidTaskException if the task ID is invalid.
     */
    public void deleteTask(String input) throws MissingTaskIndexException, InvalidTaskException {
        if (input.toLowerCase().equals(Commands.DELETE)) {
            throw new MissingTaskIndexException(messages.missingTaskIndexMessage());
        }

        int taskId = extractTaskId(input, Commands.DELETE + setSpacing());
        validateTaskId(taskId);
        
        if (taskId > tasks.size() || taskId < 1) { 
            throw new InvalidTaskException(messages.nonexistentTaskIndex(taskId));
        }

        tasks.remove(taskId - 1);
        messages.deleteTaskSuccessfullyMessage(taskId);
        fileHandler.updateTaskFile(tasks);
    }

    public void handleExit() {
        messages.exitMessage();
    }
}

