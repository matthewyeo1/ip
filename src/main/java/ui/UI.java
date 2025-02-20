package ui;

import java.util.Scanner;
import lib.TaskManager;
import commands.Commands;
import menu.Menu;

/**
 * The <code>UI</code> class handles user interaction by processing input 
 * commands and delegating tasks to the <code>TaskManager</code>.
 * It continuously listens for user input and performs the corresponding 
 * operations based on recognized commands.
 *
 * <p>Commands such as listing tasks, deleting tasks, finding tasks, 
 * marking/unmarking tasks, and adding new tasks are supported.</p>
 */
public class UI {
    private Scanner scanner;
    private TaskManager taskManager;
    private Menu menu;

    /**
     * Constructs a <code>UI</code> object with a specified scanner, 
     * task manager, and menu.
     *
     * @param scanner The <code>Scanner</code> used for reading user input.
     * @param taskManager The <code>TaskManager</code> responsible for task operations.
     * @param menu The <code>Menu</code> for displaying UI elements.
     */
    public UI(Scanner scanner, TaskManager taskManager, Menu menu) {
        this.scanner = scanner;
        this.taskManager = taskManager;
        this.menu = menu;
    }

    /**
     * Processes user input continuously until the exit command is received.
     * Recognized commands are handled by the appropriate methods in 
     * <code>TaskManager</code>. Unrecognized input is treated as a new task.
     * The scanner is closed upon exiting.
     */
    public void processUserInput() {
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            System.out.print(Commands.INPUT_INDICATOR);

            if (input.equalsIgnoreCase(Commands.BYE)) {
                taskManager.handleExit();
                menu.setDivider();
                break;
            } else if (input.equalsIgnoreCase(Commands.LIST)) {
                System.out.println();
                taskManager.showList();
                menu.setDivider();
            } else if (input.contains(Commands.DELETE)) {
                taskManager.handleDelete(input);
                menu.setDivider();
            } else if (input.contains(Commands.FIND)) {
                taskManager.handleFind(input);
                menu.setDivider();
            } else if (input.contains(Commands.MARK) || input.contains(Commands.UNMARK)) {
                taskManager.handleMarkUnmark(input);
                menu.setDivider();
            } else {
                taskManager.addTask(input);
                menu.setDivider();
            }
        }
        scanner.close();
    }
}

