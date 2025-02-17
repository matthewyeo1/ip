package menu;
import commands.Commands;

public final class Menu {
    public void setDivider() {
        System.out.println("\n" + "_".repeat(80) + "\n");
    }

    public void displayWelcomeMessage() {
        String logo = " _____   _____   _____\n"
                    + "|  _  | |  __ | |  ___|\n"
                    + "| |_| | | |     | |___ \n"
                    + "|  _  | | |     |  ___|\n"
                    + "| | | | | |___  | |___\n"
                    + "|_| |_| |_____| |_____|\n";
        
        setDivider();
        System.out.println("Hello! I am...\n" + logo);
        System.out.println("How can I assist you?" + System.lineSeparator());
        System.out.println();
        displayCommandList();
        setDivider();
    }

    public void displayCommandList() {
        String commandList = Commands.LIST + ": " + Commands.getCommandDescription(Commands.LIST) 
                            + System.lineSeparator()
                            + Commands.FIND + ": " + Commands.getCommandDescription(Commands.FIND)
                            + System.lineSeparator()
                            + Commands.MARK + ": " + Commands.getCommandDescription(Commands.MARK) 
                            + System.lineSeparator()
                            + Commands.UNMARK + ": " + Commands.getCommandDescription(Commands.UNMARK) 
                            + System.lineSeparator()
                            + Commands.DELETE + ": " + Commands.getCommandDescription(Commands.DELETE) 
                            + System.lineSeparator()
                            + Commands.BYE + ": " + Commands.getCommandDescription(Commands.BYE) 
                            + System.lineSeparator();

        System.out.println("COMMANDS: " + System.lineSeparator());
        System.out.println(commandList);
    }
}
