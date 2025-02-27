package it.unipv.model;

public class Valve {
    
    private boolean open;  // true = open, false = closed
    private LimitSwitch openLimit;
    private LimitSwitch closeLimit;

    // Constructor
    public Valve(LimitSwitch openLimit, LimitSwitch closeLimit) {
        this.openLimit = openLimit;
        this.closeLimit = closeLimit;
        if (!openLimit.getState() && closeLimit.getState())
            this.open = false;
        else // Set to true if there are feedback issues
            this.open = true;
    }

    // Method to open the valve
    public void open() {
        if (openLimit.getState()) {
            System.out.println("Valve is already open.");
            return;
        }
        
        closeLimit.release();
        openLimit.press();
        this.open = true;
        
        System.out.println("Valve opened.");
    }

    // Method to close the valve
    public void close() {
        if (closeLimit.getState()) {
            System.out.println("Valve is already closed.");
            return;
        }
        
        openLimit.release();
        closeLimit.press();
        this.open = false;
        
        System.out.println("Valve closed.");
    }

    // Method to get the current state of the valve
    public boolean getState() {
        return open;
    }
}
