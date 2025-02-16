package lib;

import error_handling.InvalidTaskException;
import error_handling.MissingTaskIndexException;
import commands.Commands;
import java.util.ArrayList;
import messages.Messages;
import data_storage.TaskFileHandler;

public class TaskManager {
    private static final int MAX_TASKS = 100;
    private ArrayList<Task> tasks;
    private TaskFileHandler fileHandler;
    Messages messages = new Messages();

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

    private TaskType determineTaskType(String input) {
        if (input.contains(Commands.TASK_BY)) {
            return TaskType.DEADLINE;
        }
        if (input.contains(Commands.TASK_FROM) && input.contains(Commands.TASK_TO)) {
            return TaskType.EVENT;
        }
        return TaskType.TODO;
    }

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

    private void validateTaskId(int taskId) throws InvalidTaskException {
        if (taskId < 1 || taskId > MAX_TASKS) {
            throw new InvalidTaskException(messages.taskIdOutOfBoundsMessage());
        }
    }

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

