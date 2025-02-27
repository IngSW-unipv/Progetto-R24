package it.unipv.simulation;

import it.unipv.model.Sensor;

public class PressureSimulator extends Simulator {
    private static PressureSimulator instance;

    // Private constructor
    private PressureSimulator() { }

    // Method to get the unique instance
    public static PressureSimulator getInstance() {
        if (instance == null) {
            instance = new PressureSimulator();
        }
        return instance;
    }

    // Simulates pressure decrease 
    public void simulate(Sensor pressureSensor) {
        double currentPressure = pressureSensor.getValue();
        if (currentPressure >= pressureSensor.getMinValue()) {
            pressureSensor.updateValue(currentPressure - 0.1); // Decrease of 0.1°C with each call
        }
    }
}
