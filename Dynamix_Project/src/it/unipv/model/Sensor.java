package it.unipv.model;

public class Sensor {
    public enum SensorType { PRESSURE, TEMPERATURE, WEIGHT }

    private SensorType type;
    private String unit;
    private double minValue;
    private double maxValue;
    private double value; // Current sensor value

    // Constructor
    public Sensor(SensorType type, double minValue, double maxValue, String unit) {
        if (minValue >= maxValue) {
            throw new IllegalArgumentException("Error: MinValue must be less than MaxValue.");
        }
        this.type = type;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.unit = unit;
        this.value = 0; // Initialize sensor to minimum value
    }

    // Method to update the sensor value
    public void updateValue(double newValue) {
        if (newValue < minValue || newValue > maxValue) {
            System.out.println("Warning: Value out of range for " + type + ": " + newValue + " " + unit);
            return;
        }
        this.value = newValue;
        System.out.println("Sensor " + type + " updated: " + value + " " + unit);
    }

    // Getter for the current value
    public double getValue() {
        return value;
    }

    // Getter for the sensor type
    public SensorType getType() {
        return type;
    }

    // Getter for the unit of measurement
    public String getUnit() {
        return unit;
    }

    // Getter for the sensor's minimum value
    public double getMinValue() {
        return minValue;
    }

    // Getter for the sensor's maximum value
    public double getMaxValue() {
        return maxValue;
    }

    // Override of the toString method to print the sensor's information
    @Override
    public String toString() {
        return "Sensor [" + type + "] → Value: " + value + " " + unit + 
               " (Range: " + minValue + " - " + maxValue + ")";
    }
}
