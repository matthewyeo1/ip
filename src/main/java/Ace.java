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
            + "____________________"
        );
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

    // Show list
    public static void showList(Task[] taskArray) {
        setGap();
        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i] == null) {
                return;
            }
            System.out.println((i + 1) + ". " + taskArray[i].getDescription());
        }
    }

    public static void main(String[] args) {

        // Create a Scanner to read user input
        Scanner scanner = new Scanner(System.in); 

        String logo =
              " _____   _____   _____ \n"
            + "|  _  | |  __ | |  ___|\n" 
            + "| |_| | | |     | |___ \n"
            + "|  _  | | |     |  ___|\n"
            + "| | | | | |___  | |___ \n"
            + "|_| |_| |_____| |_____|\n";

        setDivider();
        setGap();
        System.out.println("Hello! I am...\n" + logo);
        System.out.println("How can I assist you?");
        setGap();
        setDivider();
        
        // Initialize task array of size 100
        Task[] tasks = new Task[100];

        // Infinite loop to perpetually scan for user input until user input == 'bye' (not case-sensitive)
        while (true) {
            setGap();
            System.out.print("> ");
            String input=scanner.nextLine();
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
            } else {
                setGap();
                Task t = new Task(input);
                addToList(tasks, t);
                setGap();
                setDivider();
            } 
        }
        scanner.close();
    }
}
