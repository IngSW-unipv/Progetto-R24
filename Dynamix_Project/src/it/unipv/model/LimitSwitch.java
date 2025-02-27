package it.unipv.model;

public class LimitSwitch {
    
    private boolean pressed; // Indicates whether the switch is pressed

    // Constructor with no default state, set to not pressed by default
    public LimitSwitch() {
        this.pressed = false; // Default state is not pressed
    }
    
    // Constructor with an initial state for the switch
    public LimitSwitch(boolean initialState) {
        this.pressed = initialState;
    }

    // Simulates pressing the limit switch
    public void press() {
        this.pressed = true;
        System.out.println("Limit switch pressed.");
    }

    // Simulates releasing the limit switch
    public void release() {
        this.pressed = false;
        System.out.println("Limit switch released.");
    }

    // Returns the current state of the limit switch
    public boolean getState() {
        return pressed;
    }

    @Override
    public String toString() {
        return "LimitSwitch{" + "pressed=" + pressed + '}';
    }
}
