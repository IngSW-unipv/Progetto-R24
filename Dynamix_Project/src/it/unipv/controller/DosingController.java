package it.unipv.controller;

import javax.swing.JLabel;
import it.unipv.model.Valve;
import it.unipv.model.Sensor;
import it.unipv.simulation.WeightSimulator;

public class DosingController {
    private static DosingController instance;
    
    private Valve[] valves;
    private Sensor weightSensor;
    private Valve selectedValve;
    private double setPoint;
    private WeightSimulator simulator;

    // Private constructor for Singleton pattern
    private DosingController(Valve[] valves, Sensor weightSensor) {
        if (weightSensor.getType() != Sensor.SensorType.WEIGHT) {
            throw new IllegalArgumentException("The sensor must be of type WEIGHT.");
        }
        if (valves == null || valves.length == 0) {
            throw new IllegalArgumentException("At least one valve is required.");
        }
        
        this.valves = valves;
        this.weightSensor = weightSensor;
        this.simulator = WeightSimulator.getInstance(); // Singleton instance
        this.setPoint = 0;
        this.selectedValve = valves[0]; // Default to the first valve
    }

    // Method to get the Singleton instance
    public static DosingController getInstance(Valve[] valves, Sensor weightSensor) {
        if (instance == null) {
            instance = new DosingController(valves, weightSensor);
        }
        return instance;
    }

    // Selects the valve based on the raw material line
    private void selectValve(int lineIndex) {
        this.selectedValve = valves[lineIndex];
        System.out.println("Valve selected: Line " + lineIndex);
    }

    // Sets the target weight
    private void setTargetWeight(double setPoint) {
        this.setPoint = setPoint;
        System.out.println("SetPoint set to " + setPoint + " kg.");
    }

    // Starts the dosing process using the selected valve
    public boolean startDosing(double sp, int lineIndex, JLabel currentWeightLabel) {
        if (sp <= 0 || sp > weightSensor.getMaxValue()) {
            System.out.println("Invalid target weight! Enter a value between 0 and " + weightSensor.getMaxValue() + " kg.");
            return false;
        }
        if (lineIndex < 0 || lineIndex >= valves.length) {
            System.out.println("Invalid line index!");
            return false;
        }
        setTargetWeight(sp);
        
        selectValve(lineIndex);

        System.out.println("Starting dosing with selected valve...");
        selectedValve.open();

        while (weightSensor.getValue() < setPoint) {
            simulator.simulate(weightSensor);
            // Update the label with the current weight
            currentWeightLabel.setText("Current weight: " + String.format("%.2f", weightSensor.getValue()) + " kg");
            
            System.out.println("Current weight: " + weightSensor.getValue() + " kg...");
            
            try {
                Thread.sleep(100);  // Pause to simulate weight increase and avoid GUI overload
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        selectedValve.close();
        System.out.println("Dosing complete! Final weight: " + weightSensor.getValue() + " kg.");
        return true;
    }
    
    // Resets the weight sensor value to zero
    public void empty() {
        weightSensor.updateValue(0);
    }
}
