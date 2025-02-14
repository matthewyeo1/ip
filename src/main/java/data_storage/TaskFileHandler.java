package data_storage;
import lib.ToDo;
import lib.Deadline;
import lib.Event;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import lib.Task;
import messages.Messages;

public class TaskFileHandler {
    private static final String FILE_NAME = "./tasks.txt";
    private static final String TODO_ICON = "T";
    private static final String DEADLINE_ICON = "D";
    private static final String EVENT_ICON = "E";
    private static final String DELIMITER = " | ";
    private static final String EMPTY_STRING = "";
    private static final String ATTRIBUTE_SEPARATOR = " \\| ";

    Messages messages = new Messages();

    public void saveTasks(ArrayList<Task> tasks) {
        File file = new File(FILE_NAME);
        File directory = file.getParentFile(); 
        
        if (directory != null && !directory.exists()) {
            directory.mkdirs(); 
        }
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(taskToFileFormat(task)); 
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(messages.saveTaskErrorMessage() + e);
        }
    }
    
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(FILE_NAME);
    
        if (!file.exists()) {
            Messages.missingTaskFileMessage();
            return taskList; 
        }
    
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println(Messages.loadTaskErrorMessage() + e.getMessage());
        } 
        return taskList;
    }
    
    private static String taskToFileFormat(Task task) {
        if (task instanceof Deadline) {
            return DEADLINE_ICON + DELIMITER + task.getDoneStatus() + 
            DELIMITER + task.getDescription() + DELIMITER + 
            ((Deadline) task).getDueDate();
        } else if (task instanceof Event) {
            return EVENT_ICON + DELIMITER + task.getDoneStatus() + 
            DELIMITER + task.getDescription() + DELIMITER + 
            ((Event) task).getStartDate() + DELIMITER + ((Event) task).getEndDate();
        } else if (task instanceof ToDo) {
            return TODO_ICON + DELIMITER + task.getDoneStatus() + 
            DELIMITER + task.getDescription();
        }
        return EMPTY_STRING;
    }

    private static Task parseTask(String line) {
        String[] parts = line.split(ATTRIBUTE_SEPARATOR);
        if (parts.length < 2) return null;

        String type = parts[0];
        String description = parts[2];

        switch (type) {
            case TODO_ICON: return new ToDo(description);
            case DEADLINE_ICON: return new Deadline(description, parts[3]);
            case EVENT_ICON: return new Event(description, parts[3], parts[4]);
            default: return null;
        }
    }

    public void updateTaskFile(ArrayList<Task> tasks) {
        File file = new File(FILE_NAME);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(taskToFileFormat(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(Messages.updateTaskFileError() + e.getMessage());
        }
    }
}

