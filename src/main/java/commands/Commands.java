package commands;

public final class Commands {
    public static final String INPUT_INDICATOR = "> ";
    public static final String LIST = "list";
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
