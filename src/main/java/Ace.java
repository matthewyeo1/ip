import java.util.Scanner;
import lib.TaskManager;
import commands.Commands;
import menu.Menu;

public class Ace {

    public Ace () {};
    public static void main(String[] args) {
        new Ace().run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        Menu menu = new Menu();
        
        menu.displayWelcomeMessage();
        
        while (true) {
            System.out.print(Commands.INPUT_INDICATOR);
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase(Commands.BYE)) {
                taskManager.handleExit();
                menu.setDivider();
                break;
            } else if (input.equalsIgnoreCase(Commands.LIST)) {
                taskManager.showList();
                menu.setDivider();
            } else if (input.contains(Commands.DELETE)) {
                taskManager.handleDelete(input);
                menu.setDivider();
            } else if (input.contains(Commands.MARK) || input.contains(Commands.UNMARK)){
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
