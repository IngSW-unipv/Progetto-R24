package it.unipv.controller;

import it.unipv.util.Timer;
import it.unipv.model.Motor;

public class MachinePositionController {
    private static MachinePositionController instance; // Singleton instance
    
    private int coverState; // 0: Down | 1: Up | 2: Middle
    private int tankState;  // 0: Stand | 1: Inclined | 2: Middle
    
    private Motor coverMotor;
    private Motor tankMotor;
    
    private Timer timer;

    // Private constructor for Singleton pattern
    private MachinePositionController(Motor coverMotor, Motor tankMotor) {
        this.coverMotor = coverMotor;
        this.tankMotor = tankMotor;
        this.coverState = getCoverState();
        this.tankState = getTankState();
        this.timer = new Timer();
    }

    // Method to get the Singleton instance
    public static MachinePositionController getInstance(Motor coverMotor, Motor tankMotor) {
        if (instance == null) {
            instance = new MachinePositionController(coverMotor, tankMotor);
        }
        return instance;
    }
    
    // Moves the cover (UP/DOWN/MIDDLE)
    public void moveCover(boolean direction) {
        System.out.println("Moving cover...");
        if (!coverMotor.start(50, direction)) return;  // Arbitrary speed for movement
        timer.waitFor(3);  // Simulates movement time
        coverMotor.stop();
        setCoverState();
        coverState = coverMotor.getState();
        System.out.println("Cover moved to new position: " + getCoverStateName());
    }

    // Moves the tank (STAND/INCLINED/MIDDLE)
    public void moveTank(boolean direction) {
        System.out.println("Moving tank...");
        if (!tankMotor.start(50, direction)) return;  // Arbitrary speed for movement
        timer.waitFor(4);  // Simulates tilting time
        tankMotor.stop();
        setTankState();
        tankState = tankMotor.getState();
        System.out.println("Tank moved to new position: " + getTankStateName());
    }
    
    // Updates the cover state based on the motor's current state
    public void setCoverState() {
        coverState = coverMotor.getState();
    }
    
    // Getter for the cover state
    public int getCoverState() {
        return coverState;
    }

    // Returns the cover state as a readable string
    public String getCoverStateName() {
        return switch (coverState) {
            case 0 -> "Down";
            case 1 -> "Up";
            case 2 -> "Middle";
            default -> "Unknown";
        };
    }

    // Updates the tank state based on the motor's current state
    public void setTankState() {
        tankState = tankMotor.getState();
    }
    
    // Getter for the tank state
    public int getTankState() {
        return tankState;
    }

    // Returns the tank state as a readable string
    public String getTankStateName() {
        return switch (tankState) {
            case 0 -> "Stand";
            case 1 -> "Inclined";
            case 2 -> "Middle";
            default -> "Unknown";
        };
    }
}
