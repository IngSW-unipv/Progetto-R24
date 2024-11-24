package Default;

import java.util.ArrayList;
import java.util.List;

public class Database {
	 
	private int memory;
	private List<Profile> profiles;

	//COSTRUTTORE
	public Database(int memory) 
	{
		this.memory = memory;
	    this.profiles = new ArrayList<>();
	}
}
