package it.unipv.main;

import javax.swing.SwingUtilities;
import it.unipv.view.MainFrame;
import it.unipv.controller.*;
import it.unipv.model.*;

public class Main {
    public static void main(String[] args) {
        // Creating sensors
        Sensor tempSensor = new Sensor(Sensor.SensorType.TEMPERATURE, -50, 150, "°C");
        Sensor pressSensor = new Sensor(Sensor.SensorType.PRESSURE, -10, 2, "bar");
        pressSensor.updateValue(1);
        Sensor weightSensor = new Sensor(Sensor.SensorType.WEIGHT, 0, 200, "kg");

        // Creating valves
        Valve[] dosingValves = {
            new Valve(new LimitSwitch(false), new LimitSwitch(true)),
            new Valve(new LimitSwitch(false), new LimitSwitch(true)),
            new Valve(new LimitSwitch(false), new LimitSwitch(true))
        };

        Valve[] hotValves = {
            new Valve(new LimitSwitch(false), new LimitSwitch(true)),
            new Valve(new LimitSwitch(false), new LimitSwitch(true))
        };

        Valve[] coldValves = {
            new Valve(new LimitSwitch(false), new LimitSwitch(true)),
            new Valve(new LimitSwitch(false), new LimitSwitch(true))
        };

        Valve waterPumpValve = new Valve(new LimitSwitch(false), new LimitSwitch(true));

        // Creating motors
        Motor coverMotor = new Motor(50, 50, new LimitSwitch(true), new LimitSwitch(false));
        Motor tankMotor = new Motor(50, 50, new LimitSwitch(true), new LimitSwitch(false));
        Motor scrapingMotor = new Motor(100, 1250);
        Motor mixingMotor = new Motor(100, 1500);
        Motor turbineMotor = new Motor(100, 500);
        Motor pumpMotor = new Motor(20, 500);

        // Creating controllers
        TemperatureController tempController = TemperatureController.getInstance(hotValves, coldValves, tempSensor);
        MachinePositionController posController = MachinePositionController.getInstance(coverMotor, tankMotor);
        PressureController pressureController = PressureController.getInstance(pumpMotor, waterPumpValve, pressSensor);
        DosingController dosingController = DosingController.getInstance(dosingValves, weightSensor);
        MixingProcessController mixController = MixingProcessController.getInstance(mixingMotor, scrapingMotor, turbineMotor);

        // Creating and launching the graphical user interface
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(dosingController, posController, tempController, pressureController, mixController);
            frame.setVisible(true);
        });
    }
}
