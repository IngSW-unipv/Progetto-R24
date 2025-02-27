package it.unipv.view;

import it.unipv.controller.*;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    
    public MainFrame(DosingController dosingController, MachinePositionController positionController, TemperatureController temperatureController,
                     PressureController pressureController, MixingProcessController mixingProcessController) {
        setTitle("Impianto di Produzione - Controllo Processi");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panels creations
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Dosaggio", new DosingPanel(dosingController));
        tabbedPane.addTab("Position", new MachinePositionPanel(positionController));
        tabbedPane.addTab("Temperatura", new TemperaturePanel(temperatureController));
        tabbedPane.addTab("Pressione", new PressurePanel(pressureController, positionController));
        tabbedPane.addTab("Miscelazione", new MixingPanel(mixingProcessController, positionController));

        add(tabbedPane, BorderLayout.CENTER);
    }
}
