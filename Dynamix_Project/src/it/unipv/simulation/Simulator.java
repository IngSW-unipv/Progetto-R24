package it.unipv.simulation;

import it.unipv.model.Sensor;

public abstract class Simulator {
    
    // Abstract method that each subclass must implement
    public abstract void simulate(Sensor sensor);
}
