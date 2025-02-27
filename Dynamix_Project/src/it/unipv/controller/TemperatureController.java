package it.unipv.controller;

import it.unipv.model.Valve;
import it.unipv.model.Sensor;
import it.unipv.simulation.TemperatureSimulator;

public class TemperatureController {
    // Singleton instance of the TemperatureController
    private static TemperatureController instance;

    // Arrays for hot and cold water valves
    private Valve[] hotWaterValves;
    private Valve[] coldWaterValves;
    
    // Temperature sensor used to measure the current temperature
    private Sensor temperatureSensor;
    
    // Target temperature to reach
    private double setPoint;
    
    // Temperature simulator instance
    private TemperatureSimulator simulator;
    
    // Flags for heating and cooling statuses
    private boolean isHeating;
    private boolean isCooling;

    // Constructor: Initializes the controller with valves and sensor
    private TemperatureController(Valve[] hotWaterValves, Valve[] coldWaterValves, Sensor temperatureSensor) {
        // Check if the sensor is of the correct type
        if (temperatureSensor.getType() != Sensor.SensorType.TEMPERATURE) {
            throw new IllegalArgumentException("The sensor must be of type TEMPERATURE.");
        }
        
        // Check that there are exactly two hot water valves
        if (hotWaterValves == null || hotWaterValves.length != 2) {
            throw new IllegalArgumentException("There must be exactly 2 valves for hot water.");
        }
        
        // Check that there are exactly two cold water valves
        if (coldWaterValves == null || coldWaterValves.length != 2) {
            throw new IllegalArgumentException("There must be exactly 2 valves for cold water.");
        }
        
        // Initialize fields with provided parameters
        this.hotWaterValves = hotWaterValves;
        this.coldWaterValves = coldWaterValves;
        this.temperatureSensor = temperatureSensor;
        this.simulator = TemperatureSimulator.getInstance();
        this.setPoint = 0;  // Default target temperature
        this.isHeating = false;  // Initially, not heating
        this.isCooling = false;  // Initially, not cooling
    }

    // Singleton pattern to get the instance of the controller
    public static TemperatureController getInstance(Valve[] hotWaterValves, Valve[] coldWaterValves, Sensor temperatureSensor) {
        if (instance == null) {
            instance = new TemperatureController(hotWaterValves, coldWaterValves, temperatureSensor);
        }
        return instance;
    }

    // Set the target temperature (setPoint)
    private boolean setTargetTemperature(double setPoint) {
        // Check if the set point is within the valid range of the sensor
        if (setPoint < temperatureSensor.getMinValue() || setPoint > temperatureSensor.getMaxValue()) {
            System.out.println("Invalid target temperature!");
            return false;
        }
        
        // Set the target temperature
        this.setPoint = setPoint;
        System.out.println("SetPoint set to " + setPoint + "°C.");
        return true;
    }

    // Start heating to the target temperature
    public boolean startHeating(double sp) {
        // If setting the target temperature fails, return false
        if (!setTargetTemperature(sp)) return false;
        
        // If the current temperature is already at or above the target, no action needed
        if (temperatureSensor.getValue() >= setPoint) {
            System.out.println("The temperature is already at or above the set point. No action needed.");
            return true;
        }
        
        System.out.println("Starting heating...");
        this.isHeating = true;
        this.isCooling = false;
        
        // Open hot water valves
        for (Valve valve : hotWaterValves) {
            valve.open();
        }
        
        // Simulate heating until the target temperature is reached
        while (temperatureSensor.getValue() < setPoint) {
            simulator.simulate(temperatureSensor);
            System.out.println("Current temperature: " + temperatureSensor.getValue() + "°C...");
            if (temperatureSensor.getValue() >= setPoint) {
                stopHeating();
            }
        }
        return true;
    }

    // Start cooling to the target temperature
    public boolean startCooling(double sp) {
        // If setting the target temperature fails, return false
        if (!setTargetTemperature(sp)) return false;
        
        // If the current temperature is already at or below the target, no action needed
        if (temperatureSensor.getValue() <= setPoint) {
            System.out.println("The temperature is already at or below the set point. No action needed.");
            return true;
        }
        
        System.out.println("Starting cooling...");
        this.isCooling = true;
        this.isHeating = false;
        
        // Open cold water valves
        for (Valve valve : coldWaterValves) {
            valve.open();
        }
        
        // Simulate cooling until the target temperature is reached
        while (temperatureSensor.getValue() > setPoint) {
            simulator.simulateReverse(temperatureSensor);
            System.out.println("Current temperature: " + temperatureSensor.getValue() + "°C...");
            if (temperatureSensor.getValue() <= setPoint) {
                stopCooling();
            }
        }
        return true;
    }

    // Stop heating if it is currently active
    public void stopHeating() {
        if (!isHeating) {
            System.out.println("Heating is already stopped.");
            return;
        }
        this.isHeating = false;
        System.out.println("Heating stopped. Reached temperature: " + temperatureSensor.getValue() + "°C.");
        
        // Close hot water valves
        for (Valve valve : hotWaterValves) {
            valve.close();
        }
    }

    // Stop cooling if it is currently active
    public void stopCooling() {
        if (!isCooling) {
            System.out.println("Cooling is already stopped.");
            return;
        }
        this.isCooling = false;
        System.out.println("Cooling stopped. Reached temperature: " + temperatureSensor.getValue() + "°C.");
        
        // Close cold water valves
        for (Valve valve : coldWaterValves) {
            valve.close();
        }
    }
    
    // Get the current state of the controller: 1 = heating, 2 = cooling, 0 = idle, 3 = error state
    public int getState() {
        if (isHeating && !isCooling) return 1;
        if (!isHeating && isCooling) return 2;
        if (!isHeating && !isCooling) return 0;
        return 3;
    }
    
    // Get the current temperature from the sensor
    public double getTemperature() {
    	return temperatureSensor.getValue();
    }
}
