package it.unipv.view;

import it.unipv.controller.TemperatureController;
import javax.swing.*;
import java.awt.*;

public class TemperaturePanel extends JPanel {
    private JTextField setPointField;
    private JLabel currentTemperatureLabel;
    private static final double MIN_SETPOINT = -50.0;
    private static final double MAX_SETPOINT = 150.0;

    public TemperaturePanel(TemperatureController temperatureController) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Temperature Control"));

        // Input field for the set point
        JPanel inputPanel = new JPanel(new GridLayout(1, 2));
        inputPanel.add(new JLabel("Set Point (°C):"));
        setPointField = new JTextField();
        inputPanel.add(setPointField);
        add(inputPanel);

        // Label to display the current temperature
        currentTemperatureLabel = new JLabel("Current Temperature: " + String.format("%.2f", temperatureController.getTemperature()) + "°C");
        add(currentTemperatureLabel);

        // Label for the minimum and maximum setPoint values
        JLabel rangeLabel = new JLabel("SetPoint Min: " + MIN_SETPOINT + "°C - Max: " + MAX_SETPOINT + "°C");
        add(rangeLabel);

        // Panel for the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JButton heatButton = new JButton("Start Heating");
        JButton coolButton = new JButton("Start Cooling");
        JButton stopHeatButton = new JButton("Stop Heating");
        JButton stopCoolButton = new JButton("Stop Cooling");

        buttonPanel.add(heatButton);
        buttonPanel.add(coolButton);
        buttonPanel.add(stopHeatButton);
        buttonPanel.add(stopCoolButton);

        add(buttonPanel);

        // Button logic
        heatButton.addActionListener(e -> {
            try {
                double setPoint = Double.parseDouble(setPointField.getText());
                if (setPoint < MIN_SETPOINT || setPoint > MAX_SETPOINT) {
                    JOptionPane.showMessageDialog(this, "Enter a value between " + MIN_SETPOINT + " and " + MAX_SETPOINT, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!temperatureController.startHeating(setPoint)) {
                    JOptionPane.showMessageDialog(this, "Error starting heating.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                currentTemperatureLabel.setText("Current Temperature: " + String.format("%.2f", temperatureController.getTemperature()) + "°C");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid numerical value for the set point.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        coolButton.addActionListener(e -> {
            try {
                double setPoint = Double.parseDouble(setPointField.getText());
                if (setPoint < MIN_SETPOINT || setPoint > MAX_SETPOINT) {
                    JOptionPane.showMessageDialog(this, "Enter a value between " + MIN_SETPOINT + " and " + MAX_SETPOINT, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!temperatureController.startCooling(setPoint)) {
                    JOptionPane.showMessageDialog(this, "Error starting cooling.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                currentTemperatureLabel.setText("Current Temperature: " + String.format("%.2f", temperatureController.getTemperature()) + "°C");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid numerical value for the set point.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        stopHeatButton.addActionListener(e -> {
            temperatureController.stopHeating();
            JOptionPane.showMessageDialog(this, "Heating stopped.", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        stopCoolButton.addActionListener(e -> {
            temperatureController.stopCooling();
            JOptionPane.showMessageDialog(this, "Cooling stopped.", "Info", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
