package it.unipv.view;

import it.unipv.controller.PressureController;
import it.unipv.controller.MachinePositionController;
import javax.swing.*;
import java.awt.*;

public class PressurePanel extends JPanel {
    private JTextField setPointField;
    private JLabel currentPressureLabel;
    private static final double MIN_SETPOINT = -10;
    private static final double MAX_SETPOINT = 2;

    public PressurePanel(PressureController pressureController, MachinePositionController posController) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Pressure Control"));

        // Input field for the set point
        JPanel inputPanel = new JPanel(new GridLayout(1, 2));
        inputPanel.add(new JLabel("Set Point (bar):"));
        setPointField = new JTextField();
        inputPanel.add(setPointField);
        add(inputPanel);

        // Label to display the current pressure
        currentPressureLabel = new JLabel("Current Pressure: " + String.format("%.2f", pressureController.getPressure()) + " bar");
        add(currentPressureLabel);

        // Label for the minimum and maximum setPoint values
        JLabel rangeLabel = new JLabel("SetPoint Min: " + MIN_SETPOINT + " bar - Max: " + MAX_SETPOINT + " bar");
        add(rangeLabel);

        // Panel for the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 1, 10, 10));

        JButton startButton = new JButton("Start Pressure Control");
        buttonPanel.add(startButton);

        add(buttonPanel);

        // Button logic
        startButton.addActionListener(e -> {
            try {
                double setPoint = Double.parseDouble(setPointField.getText());
                if (setPoint < MIN_SETPOINT || setPoint > MAX_SETPOINT) {
                    JOptionPane.showMessageDialog(this, "Enter a value between " + MIN_SETPOINT + " and " + MAX_SETPOINT, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!pressureController.startPressureControl(setPoint, posController)) {
                    JOptionPane.showMessageDialog(this, "Error starting pressure control.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                currentPressureLabel.setText("Current Pressure: " + String.format("%.2f", pressureController.getPressure()) + " bar");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid numerical value for the set point.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
