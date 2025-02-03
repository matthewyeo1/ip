import java.util.Scanner;

import lib.Event;
import lib.Task;
import lib.TaskType;
import lib.Deadline;
import lib.ToDo;


public class Ace {

    // Divider function
    public static void setDivider() {
        System.out.println("\n__________________"
                + "____________________"
                + "____________________"
                + "____________________"
                + "____________________"
                + "____________________"
                + "____________________\n");
    }

    // Echo function
    public static void echo(String input) {
        System.out.print(input + "\n");
    }

    // Create task function
    public static Task createTask(String input) {
        TaskType taskType;

        if (input.contains("/by")) {
            taskType = TaskType.DEADLINE;
        } else if (input.contains("/from") && input.contains("/to")) {
            taskType = TaskType.EVENT;
        } else {
            taskType = TaskType.TODO;
        }

        switch (taskType) {
            case DEADLINE:
                String[] deadlineParts = input.split("/by", 2);
                String deadlineDescription = deadlineParts[0].trim();
                String dueDate = deadlineParts[1].trim();
                return new Deadline(deadlineDescription, dueDate);
            case EVENT:
                String[] eventParts = input.split("/from", 2);
                String eventDescription = eventParts[0].trim();
                String[] eventDates = eventParts[1].split("/to", 2);
                String startDate = eventDates[0].trim();
                String endDate = eventDates[1].trim();
                return new Event(eventDescription, startDate, endDate);
            case TODO:
                String todoDescription = input.trim();
                return new ToDo(todoDescription);
            default: 
                throw new IllegalArgumentException("Invalid input for task creation: " + input);
        }
    }

    // Function to display "task" or "tasks"
    public static String numberOfTasks(int i) {
        if (i == 1) {
            return " task"; 
        }
        return " tasks";
    }

    // Add-to-list function
    public static void addToList(Task[] taskArray, Task t) {
        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] == null) {
                taskArray[i] = t;
                System.out.println("Added: " + t.getDescription());
                System.out.println("You have " + (i + 1) + numberOfTasks(i + 1) + " in your list.");
                return;
            }
        }
        System.out.println("Error: Task list is full. Could not add: " + t.getDescription());   // If no empty slot is found
    }

    // Show list function
    public static void showList(Task[] taskArray) {
        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] == null) {
                return;
            }
            System.out.println((i + 1) + "." + taskArray[i].toString());
        }
    }

    // Mark task as done function
    public static void markAsDone(Task t) {
        t.setDone(true);
    }

    // Unmark task function
    public static void unMark(Task t) {
        t.setDone(false);
    }

    // Function to check if the mark command is valid
    public static boolean isMarkCommand(String input) {
        if (input.length() > 5 && input.substring(0, 5).equalsIgnoreCase("mark ")) {
            String taskIdStr = input.substring(5);
            try {
                int taskId = Integer.parseInt(taskIdStr);
                return (isTaskIDValid(taskId));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    // Function to check if the unmark command is valid
    public static boolean isUnMarkCommand(String input) {
        if (input.length() > 7 && input.substring(0, 7).equalsIgnoreCase("unmark ")) {
            String taskIdStr = input.substring(7);
            try {
                int taskId = Integer.parseInt(taskIdStr);
                return (isTaskIDValid(taskId));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    // Function to check validity of input task ID
    public static boolean isTaskIDValid(int taskId) {
        if (taskId < 1 || taskId > 100) {
            System.out.println("Invalid task id: " + taskId + ". Task ID must be between 1 and 100.");
            setDivider();
            return false;
        }
        return true;
    }

    // Main function
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner to read user input
        String logo = " _____   _____   _____\n"
                + "|  _  | |  __ | |  ___|\n"
                + "| |_| | | |     | |___ \n"
                + "|  _  | | |     |  ___|\n"
                + "| | | | | |___  | |___\n"
                + "|_| |_| |_____| |_____|\n";

        setDivider();
        System.out.println("Hello! I am...\n" + logo);
        System.out.println("How can I assist you?");
        setDivider();
        
        Task[] tasks = new Task[100];   // Initialize array for task list

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Have a good day.");
                setDivider();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                showList(tasks);
                setDivider();
            } else if (isMarkCommand(input)) {
                int taskId = Integer.parseInt(input.substring(5));  // extract task ID
                if (tasks[taskId - 1] != null) {
                    if ((tasks[taskId - 1]).getDoneStatus() == true) {
                        System.out.println("Task " + taskId + " is already marked.");
                    } else {
                        markAsDone(tasks[taskId - 1]);
                        System.out.println("Marked task " + taskId + " as done.");
                    }
                } else {
                    System.out.println("Task " + taskId + " does not exist.");
                }
                setDivider();
            } else if (isUnMarkCommand(input)) {
                int taskId = Integer.parseInt(input.substring(7));  // extract task ID
                if (tasks[taskId - 1] != null) {
                    if ((tasks[taskId - 1]).getDoneStatus() == false) {
                        System.out.println("Task " + taskId + " is already unmarked.");
                    } else {
                        unMark(tasks[taskId - 1]);
                        System.out.println("Unmarked task " + taskId + ".");
                    }
                } else {
                    System.out.println("Task " + taskId + " does not exist.");
                }
                setDivider();
            } else if (!input.toLowerCase().startsWith("mark") && !input.toLowerCase().startsWith("unmark")) {
                if (input.trim().isEmpty()) {   // Handle empty input
                    System.out.println("Please enter a command.");
                    setDivider();
                } else {    // Create task
                    Task t = createTask(input);
                    addToList(tasks, t);
                    setDivider();
                }
            }
        }
        scanner.close();
    }
}
