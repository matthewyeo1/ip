package commands;

/**
 * This class defines a collection of constants representing various commands 
 * that can be used in the task management program. These commands allow the user 
 * to interact with tasks by listing, finding, marking, unmarking, deleting tasks, 
 * and exiting the program. It also provides a method to retrieve descriptions for 
 * each command.
 *
 */
public final class Commands {
    public static final String INPUT_INDICATOR = "> ";
    public static final String LIST = "list";
    public static final String FIND = "find";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String DELETE = "delete";
    public static final String BYE = "bye";

    public static final String TASK_BY = "/by";
    public static final String TASK_FROM = "/from";
    public static final String TASK_TO = "/to";

    public static String getCommandDescription(String command) {
        switch (command) {
            case LIST:
                return "View task list";
            case FIND:
                return "Input this command followed by a keyword in your tasks";
            case MARK:
                return "Input this command followed by the desired task ID to mark it as complete";
            case UNMARK:
                return "Input this command followed by the desired task ID to unmark it";
            case DELETE:
                return "Input this command followed by the desired task ID to delete it";
            case BYE:
                return "Exit the program";
            default:
                throw new IllegalArgumentException("Invalid command");
        }
    }
}
