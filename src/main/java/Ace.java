import java.util.Scanner;


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
    
    // Echo()
    public static void echo(String input) {
        System.out.print(input + "\n");
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

        // Infinite loop to perpetually scan for user input until user input == 'bye' (not case-sensitive)
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
            } else {
                echo(input);
                setGap();
                setDivider();
            }
        }
        scanner.close();
    }
}
