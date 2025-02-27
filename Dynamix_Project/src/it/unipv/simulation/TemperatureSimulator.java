package it.unipv.simulation;

import it.unipv.model.Sensor;

public class TemperatureSimulator extends Simulator {
    private static TemperatureSimulator instance;

    // Singleton pattern
    private TemperatureSimulator() { }

    public static TemperatureSimulator getInstance() {
        if (instance == null) {
            instance = new TemperatureSimulator();
        }
        return instance;
    }

    // Simulates the increase of temperature
    public void simulate(Sensor temperatureSensor) {
        double currentTemp = temperatureSensor.getValue();
        if (currentTemp <= temperatureSensor.getMaxValue()) {
            temperatureSensor.updateValue(currentTemp + 0.1); // Increase of 0.1°C with each call
        }
        else
        	return;
    }

    // Simulates the decrease of temperature
    public void simulateReverse(Sensor temperatureSensor) {
        double currentTemp = temperatureSensor.getValue();
        if (currentTemp >= temperatureSensor.getMinValue()) {
            temperatureSensor.updateValue(currentTemp - 0.1); // Decrease of 0.1°C with each call
        }
        else
        	return;
    }
}
