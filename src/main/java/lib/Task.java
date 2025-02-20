package lib;

/**
 * The <code>Task</code> class represents a general task with a description and completion status.
 * It serves as a base class for the ToDo, Deadline and Event subclasses.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); 
    }

    public String getDescription() {
        return description;
    }

    public Boolean getDoneStatus() {
        return isDone;
    }

    public void setDone(Boolean x) {
        this.isDone = x;
    }

    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }
}