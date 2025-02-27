package it.unipv.model;

public class Motor {
    
    private boolean on;
    private int state;
    private boolean direction; // true = forward, false = backward
    private boolean hasLimits;
    private LimitSwitch lowerLimit;
    private LimitSwitch upperLimit;
    private final int maxSpeed;
    private final int minSpeed;
    private int speed;

    // Constructor without limit switches
    public Motor(int minSpeed, int maxSpeed) {
        if (minSpeed < 0 || maxSpeed < minSpeed) {
            System.out.println("Error: Invalid maximum and minimum speed. Setting default values. Max = 100, Min = 0");
            maxSpeed = 100; // Default value
            minSpeed = 0;    // Default value
        }
        this.hasLimits = false;
        this.maxSpeed = maxSpeed;
        this.minSpeed = minSpeed;
        this.on = false;
        this.speed = 0;
        state = -1;
    }

    // Constructor with limit switches
    public Motor(int maxSpeed, int minSpeed, LimitSwitch lowerLimit, LimitSwitch upperLimit) {
        if (minSpeed < 0 || maxSpeed < minSpeed) {
            System.out.println("Error: Invalid maximum and minimum speed. Setting default values. Max = 100, Min = 0");
            maxSpeed = 100;
            minSpeed = 0;
        }
        this.hasLimits = true;
        this.maxSpeed = maxSpeed;
        this.minSpeed = minSpeed;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        setState();
        this.on = false;
        this.speed = 0;
    }

    // Start the motor with optional speed and direction
    public boolean start(int sp, boolean dir) {
        if (sp < minSpeed || sp > maxSpeed) {
            System.out.println("Error: Speed out of range, unable to start the motor.");
            return false;
        }

        // If the motor has limit switches, check if the limits are reached
        if (hasLimits) {
            if (lowerLimit != null && lowerLimit.getState() && !dir) {
                System.out.println("Cannot start the motor: Lower limit reached.");
                return false;
            }
            
            if (upperLimit != null && upperLimit.getState() && dir) {
                System.out.println("Cannot start the motor: Upper limit reached.");
                return false;
            }
        }

        this.direction = dir;
        this.on = true;
        this.speed = sp;
        
        System.out.println("Motor started: Speed " + sp + ", Direction " + (dir ? "Forward" : "Backward"));
        
        // If the motor has limits, update their states
        if (hasLimits) {
            if (dir) {
                upperLimit.press();
                lowerLimit.release();
                setState();
            } else {
                lowerLimit.press();
                upperLimit.release();
                setState();
            }
            return true;
        }
        return true;
    }

    // Stop the motor
    public void stop() {
        if (on) {
            this.on = false;
            this.speed = 0;
            System.out.println("Motor stopped.");
        }
    }

    // Getter for the motor's state (on/off)
    public boolean getOn() {
        return on;
    }

    // Getter for the motor's speed
    public int getSpeed() {
        return speed;
    }

    // Getter for the motor's minimum speed
    public int getMinSpeed() {
        return minSpeed;
    }

    // Getter for the motor's maximum speed
    public int getMaxSpeed() {
        return maxSpeed;
    }

    // Getter for the motor's direction (true = forward, false = backward)
    public boolean getDirection() {
        return direction;
    }

    // Set the motor's state based on the limit switch conditions
    public void setState() {
        if (hasLimits) {
            if (upperLimit.getState() && !lowerLimit.getState())
                state = 1; // Upper limit reached
            else if (!upperLimit.getState() && lowerLimit.getState())
                state = 0; // Lower limit reached
            else
                state = 2; // In motion between limits
        } else {
            state = -1; // Returns -1 if no limit switches are present
        }
    }

    // Getter for the motor's state (0 = stopped, 1 = at upper limit, 2 = at lower limit, -1 = no limits)
    public int getState() {
        setState();
        return state;
    }
}
