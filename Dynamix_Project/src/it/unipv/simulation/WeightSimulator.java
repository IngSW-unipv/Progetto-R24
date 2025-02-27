package it.unipv.simulation;

import it.unipv.model.Sensor;

public class WeightSimulator extends Simulator {
    
    private static WeightSimulator instance; // Singleton instance

    // Private constructor to prevent direct instantiation
    private WeightSimulator() {}

    // Method to get the unique instance of the class (Singleton pattern)
    public static WeightSimulator getInstance() {
        if (instance == null) {
            instance = new WeightSimulator();
        }
        return instance;
    }

    // Simulates the increment of weight in the sensor
    public void simulate(Sensor weightSensor) {
        double currentWeight = weightSensor.getValue();
        double increment = Math.random() * 5; // Adds between 0 and 5 kg

        // Prevents exceeding the maximum sensor value
        double newWeight = Math.min(currentWeight + increment, weightSensor.getMaxValue());
        weightSensor.updateValue(newWeight);
    }
}
