package it.unipv.controller;

import it.unipv.model.Motor;
import it.unipv.model.Valve;
import it.unipv.model.Sensor;
import it.unipv.simulation.PressureSimulator;

public class PressureController {
    private static PressureController instance; // Singleton instance

    private Motor pumpMotor;           // Vacuum pump motor
    private Valve waterValve;          // Water valve for the pump
    private Sensor pressureSensor;     // Tank pressure sensor

    private double targetPressure;     // Target pressure to reach
    private PressureSimulator simulator;
    private boolean isRunning;         // Indicates if the process is running

    // Private constructor for Singleton pattern
    private PressureController(Motor pumpMotor, Valve waterValve, Sensor pressureSensor) {
        this.pumpMotor = pumpMotor;
        this.waterValve = waterValve;
        this.pressureSensor = pressureSensor;
        this.simulator = PressureSimulator.getInstance();
        this.isRunning = false;
    }

    // Method to get the Singleton instance
    public static PressureController getInstance(Motor pumpMotor, Valve waterValve, Sensor pressureSensor) {
        if (instance == null) {
            instance = new PressureController(pumpMotor, waterValve, pressureSensor);
        }
        return instance;
    }

    // Sets the target pressure
    private boolean setTargetPressure(double targetPressure) {
        if (targetPressure < pressureSensor.getMinValue() || targetPressure > pressureSensor.getMaxValue()) {
            System.out.println("Invalid target pressure value!");
            return false;
        }
        this.targetPressure = targetPressure;
        System.out.println("Target pressure set to " + targetPressure + " units.");
        return true;
    }

    // Starts the pressure control process
    public boolean startPressureControl(double sp, MachinePositionController posController) {
        // Ensure the cover is closed before starting
        if (posController.getCoverState() != 0) {
            System.out.println("Error: The cover is not closed. Please close the cover before starting pressure control.");
            return false;
        }
        if (!setTargetPressure(sp)) {
            System.out.println("Set a valid target pressure first.");
            return false;
        }

        System.out.println("Starting pressure control...");
        isRunning = true;

        // Open the water valve and start the pump
        waterValve.open();
        pumpMotor.start(60, true); // Arbitrary speed and direction

        // Simulate pressure reduction until the target is reached or the process is stopped
        while (isRunning && pressureSensor.getValue() > targetPressure) {
            simulator.simulate(pressureSensor);
            System.out.println("Current pressure: " + pressureSensor.getValue());
            try {
                Thread.sleep(500); // Waits 500ms between updates
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Timer interrupted.");
            }
        }

        // Stop the pump and close the valve
        pumpMotor.stop();
        waterValve.close();

        if (pressureSensor.getValue() <= targetPressure) {
            System.out.println("Target pressure reached: " + pressureSensor.getValue());
        } else {
            System.out.println("Pressure control process stopped by the user.");
        }
        isRunning = false;
        return true;
    }

    // Allows the user to manually stop the process
    public void stopPressureControl() {
        System.out.println("Stopping pressure control...");
        isRunning = false;
    }
    
    // Returns the current pressure value
    public double getPressure() {
        return pressureSensor.getValue();
    }
}
