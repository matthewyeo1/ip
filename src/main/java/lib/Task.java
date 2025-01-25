package lib;

public class Task {
    protected String description;
    protected boolean isDone;

    // Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Function to show marked/unmarked status
    public String getStatusIcon() {
        return (isDone ? "X" : " "); 
    }

    // Description getter
    public String getDescription() {
        return description;
    }

    // Status getter
    public Boolean getDoneStatus() {
        return isDone;
    }

    // Status setter
    public void setDone(Boolean x) {
        this.isDone = x;
    }
}