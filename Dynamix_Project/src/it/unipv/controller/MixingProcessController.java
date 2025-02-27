package it.unipv.controller;

import it.unipv.util.Timer;
import it.unipv.model.Motor;

public class MixingProcessController {
    private static MixingProcessController instance; // Singleton instance
    
    private boolean canMix;
    private Motor mixerMotor;
    private Motor scrapingMotor;
    private Motor turbineMotor;
    private Timer timer;
    
    // Private constructor for Singleton pattern
    private MixingProcessController(Motor mixerMotor, Motor scrapingMotor, Motor turbineMotor) {
        this.mixerMotor = mixerMotor;
        this.scrapingMotor = scrapingMotor;
        this.turbineMotor = turbineMotor;
        this.canMix = false;
        timer = new Timer();
    }
    
    // Method to get the Singleton instance
    public static MixingProcessController getInstance(Motor mixerMotor, Motor scrapingMotor, Motor turbineMotor) {
        if (instance == null) {
            instance = new MixingProcessController(mixerMotor, scrapingMotor, turbineMotor);
        }
        return instance;
    }
    
    // Starts the mixing process
    public boolean startMixingProcess(int mixingSpeed, boolean mixingDir, int scrapingSpeed, boolean scrapingDir, int turbineSpeed, boolean turbineDir, int time) {
        
        // Validate input parameters
        if (mixingSpeed < mixerMotor.getMinSpeed() || mixingSpeed > mixerMotor.getMaxSpeed()) return false;
        if (scrapingSpeed < scrapingMotor.getMinSpeed() || scrapingSpeed > scrapingMotor.getMaxSpeed()) return false;
        if (turbineSpeed < turbineMotor.getMinSpeed() || turbineSpeed > turbineMotor.getMaxSpeed()) return false;
        if (time < 0) return false;
        
        System.out.println("Starting mixing process...");
        
        mix(mixingSpeed, mixingDir, scrapingSpeed, scrapingDir, turbineSpeed, turbineDir, time);
        
        System.out.println("Mixing process completed.");
        return true;
    }
    
    // Starts the motors for mixing
    private void mix(int mixingSpeed, boolean mixingDir, int scrapingSpeed, boolean scrapingDir, int turbineSpeed, boolean turbineDir, int time) {
        System.out.println("Starting mixing motor...");
        mixerMotor.start(mixingSpeed, mixingDir);
        scrapingMotor.start(scrapingSpeed, scrapingDir);
        turbineMotor.start(turbineSpeed, turbineDir);
        timer.waitFor(time);
        mixerMotor.stop();
        scrapingMotor.stop();
        turbineMotor.stop();
        System.out.println("Mixing completed.");
    }
    
    // Sets whether mixing is allowed
    public void setCanMix(boolean allow) {
        canMix = allow;
    }
    
    // Determines if mixing can proceed based on machine position
    public boolean getCanMix(MachinePositionController posController) {
        setCanMix(posController.getCoverState() == 0 && posController.getTankState() == 0);
        return canMix;
    }
}
