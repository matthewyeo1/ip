import java.util.Scanner;
import lib.Task;

public class Ace {

    public static void setDivider() {
        System.out.println("__________________"
                + "____________________"
                + "____________________"
                + "____________________"
                + "____________________"
                + "____________________"
                + "____________________");
    }

    public static void setGap() {
        System.out.println("");
    }

    // Echo function
    public static void echo(String input) {
        System.out.print(input + "\n");
    }

    // Add-to-list function
    public static void addToList(Task[] taskArray, Task t) {
        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] == null) {
                taskArray[i] = t;
                System.out.println("Added: " + t.getDescription());
                return;
            }
        }
        // If no empty slot is found
        System.out.println("Error: Task list is full. Could not add: " + t.getDescription());
    }

    // Show list function
    public static void showList(Task[] taskArray) {
        setGap();
        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] == null) {
                return;
            }
            String statusIcon = taskArray[i].getStatusIcon();
            System.out.println((i + 1) + ".[" + statusIcon + "] " + taskArray[i].getDescription());
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
            setGap();
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
        setGap();
        System.out.println("Hello! I am...\n" + logo);
        System.out.println("How can I assist you?");
        setGap();
        setDivider();

        // Initialize array for task list
        Task[] tasks = new Task[100];

        while (true) {
            setGap();
            System.out.print("> ");
            String input = scanner.nextLine();
            setGap();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Have a good day.");
                setGap();
                setDivider();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                showList(tasks);
                setGap();
                setDivider();
            } else if (isMarkCommand(input)) {
                int taskId = Integer.parseInt(input.substring(5)); // extract task ID

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
                setGap();
                setDivider();
            } else if (isUnMarkCommand(input)) {
                int taskId = Integer.parseInt(input.substring(7)); // extract task ID

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
                setGap();
                setDivider();
            } else if (!input.toLowerCase().startsWith("mark") && !input.toLowerCase().startsWith("unmark")) {
                if (input.trim().isEmpty()) {
                    // Handle empty input
                    System.out.println("Please enter a command.");
                } else {
                    setGap();
                    Task t = new Task(input);
                    addToList(tasks, t);
                    setGap();
                    setDivider();
                }
            } else {
                setGap();
                System.out.println("Invalid command.");
                setGap();
                setDivider();
            }
        }
        scanner.close();
    }
}
