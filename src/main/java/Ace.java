import java.util.Scanner;
import lib.TaskManager;
import menu.Menu;
import ui.UI;

/**
 * The <code>Ace</code> class serves as the entry point for the application.
 * It initializes the necessary components such as <code>TaskManager</code>, 
 * <code>Menu</code>, <code>Scanner</code>, and <code>UI</code> to handle user interactions.
 *
 * @author [Matthew Yeo Xian Wen]
 * @version v0.1
 */
public class Ace {
    
    private TaskManager taskManager;
    private Menu menu;
    private Scanner scanner;
    private UI ui;

    public Ace() {}

    public static void main(String[] args) {
        new Ace().run();
    }

    /**
     * Initializes and starts the application by setting up 
     * the necessary components and processing user input.
     */
    public void run() {
        scanner = new Scanner(System.in);
        taskManager = new TaskManager();
        menu = new Menu();
        ui = new UI(scanner, taskManager, menu);
        
        menu.displayWelcomeMessage();
        ui.processUserInput();
    }
}
