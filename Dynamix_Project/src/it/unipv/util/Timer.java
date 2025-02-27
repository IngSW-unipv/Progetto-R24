package it.unipv.util;

public class Timer {
	
	public Timer()
	{
		
	}
	
	public void waitFor(int seconds) {
	    try {
	        Thread.sleep(seconds * 1000); // Converts into seconds
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	        System.out.println("❌ Errore: Interruzione del timer.");
	    }
	}
}
