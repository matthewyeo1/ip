package lib;

public class Deadline extends Task {
    private String dueDate;

    public Deadline (String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
