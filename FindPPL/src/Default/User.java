package Default;

public class User {
	
    private String username;
    private String password;
    private Profile profile;

    //COSTRUTTORE
    public User(String username, String password, Profile profile) {
        this.username = username;
        this.password = password;
        this.profile = profile;
    }

    public void createAccount() {
        // Logica per creare un account
    }

    public String getUsername() {
        return username;
    }
    
    public void setProfile(Profile profile)
    {
    	this.profile = profile;
    }
    
    public Profile getProfile() {
    	return profile;
    }
    
    public boolean login(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public void logout() {
        // Logica per il logout
    }
}
