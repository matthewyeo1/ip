import java.util.Scanner;
import lib.TaskManager;

public class Ace {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        
        displayWelcomeMessage();
        
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Have a good day.");
                setDivider();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                taskManager.showList();
                setDivider();
            } else {
                taskManager.handleMarkUnmark(input);
                setDivider();
            }
        }
        scanner.close();
    }

    public static void setDivider() {
        System.out.println("\n" + "_".repeat(80) + "\n");
    }

    private static void displayWelcomeMessage() {
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
    }
}
