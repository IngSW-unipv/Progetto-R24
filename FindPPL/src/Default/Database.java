package Default;

import java.util.ArrayList;
import java.util.List;

public class Database {
	
    private int memory;
    private List<Profile> profiles;

    //COSTRUTTORE
    public Database(int memory) {
        this.memory = memory;
        this.profiles = new ArrayList<>();
    }

    public boolean addProfile(Profile profile) {
        if(memory > profiles.size())
        {
        	profiles.add(profile);
        	return true;
        }
        else
        	return false;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }
    
    public boolean removeProfile(String name) {
    	// Logica per rimuovere un profilo
    	return false;
    }

    public List<Profile> searchProfiles(String keyword) {
        // Logica di ricerca
        List<Profile> results = new ArrayList<>();
        for (Profile profile : profiles) {
            if (profile.getGender().equalsIgnoreCase(keyword) || profile.getEyeColor().equalsIgnoreCase(keyword)) {
                results.add(profile);
            }
        }
        return results;
    }
}
