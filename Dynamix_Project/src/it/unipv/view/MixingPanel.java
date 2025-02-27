package it.unipv.view;

import it.unipv.controller.MixingProcessController;  // Import the mixing process controller
import it.unipv.controller.MachinePositionController;

import it.unipv.model.Motor;  // Assuming you have a Motor model to represent motors
import javax.swing.*;
import java.awt.*;

public class MixingPanel extends JPanel {
    private JTextField mixingSpeedField;     // Text field for mixing speed
    private JTextField scrapingSpeedField;   // Text field for scraping speed
    private JTextField turbineSpeedField;    // Text field for turbine speed
    private JTextField timeField;            // Text field for time

    private JRadioButton mixingForwardButton, mixingBackwardButton;   // Buttons for mixer direction
    private JRadioButton scrapingForwardButton, scrapingBackwardButton; // Buttons for scraper direction
    private JRadioButton turbineForwardButton, turbineBackwardButton;  // Buttons for turbine direction

    public MixingPanel(MixingProcessController mixingProcessController, MachinePositionController posController) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Mixing Control"));

        // Input panel for speeds and directions
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2)); // 4 rows, 2 columns

        inputPanel.add(new JLabel("Mixer Speed (100 - 1500):"));
        mixingSpeedField = new JTextField();
        inputPanel.add(mixingSpeedField);

        inputPanel.add(new JLabel("Scraper Speed (100 - 1250):"));
        scrapingSpeedField = new JTextField();
        inputPanel.add(scrapingSpeedField);

        inputPanel.add(new JLabel("Turbine Speed (100 - 500):"));
        turbineSpeedField = new JTextField();
        inputPanel.add(turbineSpeedField);

        inputPanel.add(new JLabel("Time (s):"));
        timeField = new JTextField();
        inputPanel.add(timeField);

        add(inputPanel);

        // Input panel for motor directions
        JPanel directionPanel = new JPanel();
        directionPanel.setLayout(new GridLayout(3, 2));

        // Mixer direction
        mixingForwardButton = new JRadioButton("Forward");
        mixingBackwardButton = new JRadioButton("Backward");
        ButtonGroup mixingDirectionGroup = new ButtonGroup();
        mixingDirectionGroup.add(mixingForwardButton);
        mixingDirectionGroup.add(mixingBackwardButton);
        directionPanel.add(new JLabel("Mixer Direction (default: back):"));
        directionPanel.add(mixingForwardButton);
        directionPanel.add(mixingBackwardButton);

        // Scraper direction
        scrapingForwardButton = new JRadioButton("Forward");
        scrapingBackwardButton = new JRadioButton("Backward");
        ButtonGroup scrapingDirectionGroup = new ButtonGroup();
        scrapingDirectionGroup.add(scrapingForwardButton);
        scrapingDirectionGroup.add(scrapingBackwardButton);
        directionPanel.add(new JLabel("Scraper Direction (default: back):"));
        directionPanel.add(scrapingForwardButton);
        directionPanel.add(scrapingBackwardButton);

        // Turbine direction
        turbineForwardButton = new JRadioButton("Forward");
        turbineBackwardButton = new JRadioButton("Backward");
        ButtonGroup turbineDirectionGroup = new ButtonGroup();
        turbineDirectionGroup.add(turbineForwardButton);
        turbineDirectionGroup.add(turbineBackwardButton);
        directionPanel.add(new JLabel("Turbine Direction (default: back):"));
        directionPanel.add(turbineForwardButton);
        directionPanel.add(turbineBackwardButton);

        add(directionPanel);

        // Label to display the process status
        JLabel statusLabel = new JLabel("Status: Not running");
        add(statusLabel);

        // Button to start the mixing process
        JButton startButton = new JButton("Start Mixing");
        add(startButton);

        // Button logic
        startButton.addActionListener(e -> {
            try {
                // Check if the machine is in the correct position
                boolean correctPosition = mixingProcessController.getCanMix(posController);
                if (!correctPosition) {
                    JOptionPane.showMessageDialog(this, "Incorrect position!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Get values from text fields
                int mixingSpeed = Integer.parseInt(mixingSpeedField.getText());
                int scrapingSpeed = Integer.parseInt(scrapingSpeedField.getText());
                int turbineSpeed = Integer.parseInt(turbineSpeedField.getText());
                int time = Integer.parseInt(timeField.getText());
                
             // Check if the values are within the valid range
                if (mixingSpeed < 100 || mixingSpeed > 1500 ||
                    scrapingSpeed < 100 || scrapingSpeed > 1250 ||
                    turbineSpeed < 100 || turbineSpeed > 500 ||
                    time <= 0) {
                    JOptionPane.showMessageDialog(this, "Enter valid values within the specified ranges!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;  // Exit if the values are not valid
                }
                statusLabel.setText("Status: Mixing in progress...");
                
                // Get motor directions (true = forward, false = backward)
                boolean mixingDir = mixingForwardButton.isSelected();
                boolean scrapingDir = scrapingForwardButton.isSelected();
                boolean turbineDir = turbineForwardButton.isSelected();
                
                 
                
                // Start the mixing process in a new thread
                new Thread(() -> {
                
                	boolean success = mixingProcessController.startMixingProcess(
                        mixingSpeed, mixingDir, scrapingSpeed, scrapingDir, turbineSpeed, turbineDir, time
                    );
                    
                    // After completion, update the UI status in the main thread
                    SwingUtilities.invokeLater(() -> {
                        if (!success) {
                            JOptionPane.showMessageDialog(this, "Enter valid values!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        statusLabel.setText("Status: Mixing completed");
                    });
                }).start();
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter valid values!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

    }
}
