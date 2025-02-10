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

public class TaskFileHandler {
    private static final String FILE_NAME = "./tasks.txt";

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
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
    
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(FILE_NAME);
    
        if (!file.exists()) {
            System.out.println("Task file not found, creating new one...");
            return taskList; 
        }
    
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    taskList.add(task);
                    System.out.println("Loaded Task: " + task.getDescription()); 
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    
        System.out.println("Total tasks loaded: " + taskList.size()); 
        return taskList;
    }
    
    
    private static String taskToFileFormat(Task task) {
        if (task instanceof Deadline) {
            return "D | " + task.getDoneStatus() + " | " + task.getDescription() + " | " + ((Deadline) task).getDueDate();
        } else if (task instanceof Event) {
            return "E | " + task.getDoneStatus() + " | " + task.getDescription() + " | " + ((Event) task).getStartDate() + " | " + ((Event) task).getEndDate();
        } else if (task instanceof ToDo) {
            return "T | " + task.getDoneStatus() + " | " + task.getDescription();
        }
        return "";
    }

    private static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 2) return null;

        String type = parts[0];
        String description = parts[2];

        switch (type) {
            case "T": return new ToDo(description);
            case "D": return new Deadline(description, parts[3]);
            case "E": return new Event(description, parts[3], parts[4]);
            default: return null;
        }
    }
}

