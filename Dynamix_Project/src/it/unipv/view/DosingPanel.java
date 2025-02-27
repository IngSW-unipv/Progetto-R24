package it.unipv.view;

import it.unipv.controller.DosingController;
import javax.swing.*;
import java.awt.*;

public class DosingPanel extends JPanel {
    private JTextField setPointField;  // Text field for the setPoint
    private JTextField lineIndexField; // Text field for lineIndex
    private JLabel currentWeightLabel; // Label to display the current weight

    public DosingPanel(DosingController dosingController) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Dosing Control"));
        
        // Add input fields for setPoint and lineIndex
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));

        inputPanel.add(new JLabel("Target Weight (0 - 200 kg):"));
        setPointField = new JTextField();
        inputPanel.add(setPointField);

        inputPanel.add(new JLabel("Line Index (0 - 2):"));
        lineIndexField = new JTextField();
        inputPanel.add(lineIndexField);
        
        add(inputPanel);
        
        // Label to display the current weight
        currentWeightLabel = new JLabel("Current Weight: 0.00 kg");
        add(currentWeightLabel);
        
        // Add button to start dosing
        JButton startButton = new JButton("Start Dosing");
        add(startButton);

        // Logic for the start button
        startButton.addActionListener(e -> {
            try {
                // Get values from the text fields
                double setPoint = Double.parseDouble(setPointField.getText());
                int lineIndex = Integer.parseInt(lineIndexField.getText());
                
                // Start dosing with the values from the fields and check if it was successful
                boolean success = dosingController.startDosing(setPoint, lineIndex, currentWeightLabel);
                
                if (!success) {
                    JOptionPane.showMessageDialog(this, "Please enter valid values!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid values!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Add button to empty the sensor
        JButton emptyButton = new JButton("Empty");
        add(emptyButton);

        // Logic for the empty button
        emptyButton.addActionListener(e -> {
            dosingController.empty();  // Call the empty method to reset the sensor
            currentWeightLabel.setText("Current Weight: 0.00 kg");  // Update the label to reflect the value 0
        });
    }
}
