package it.unipv.view;

import it.unipv.controller.MachinePositionController;
import javax.swing.*;
import java.awt.*;

public class MachinePositionPanel extends JPanel {
    private JLabel coverStatusLabel;
    private JLabel tankStatusLabel;
    
    public MachinePositionPanel(MachinePositionController machinePositionController) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Machine Position Control"));
        
        // Current status of the components
        coverStatusLabel = new JLabel("Cover: " + machinePositionController.getCoverStateName());
        tankStatusLabel = new JLabel("Tank: " + machinePositionController.getTankStateName());
        add(coverStatusLabel);
        add(tankStatusLabel);
        
        // Panel for movement buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2));
        
        // Buttons for cover movement
        JButton coverUpButton = new JButton("Raise Cover");
        JButton coverDownButton = new JButton("Lower Cover");
        buttonPanel.add(coverUpButton);
        buttonPanel.add(coverDownButton);
        
        // Buttons for tank movement
        JButton tankInclineButton = new JButton("Incline Tank");
        JButton tankStandButton = new JButton("Standard Position");
        buttonPanel.add(tankInclineButton);
        buttonPanel.add(tankStandButton);
        
        add(buttonPanel);
        
        // Button actions
        coverUpButton.addActionListener(e -> {
            machinePositionController.moveCover(true);
            coverStatusLabel.setText("Cover: " + machinePositionController.getCoverStateName());
        });
        
        coverDownButton.addActionListener(e -> {
            machinePositionController.moveCover(false);
            coverStatusLabel.setText("Cover: " + machinePositionController.getCoverStateName());
        });
        
        tankInclineButton.addActionListener(e -> {
            machinePositionController.moveTank(true);
            tankStatusLabel.setText("Tank: " + machinePositionController.getTankStateName());
        });
        
        tankStandButton.addActionListener(e -> {
            machinePositionController.moveTank(false);
            tankStatusLabel.setText("Tank: " + machinePositionController.getTankStateName());
        });
    }
}
