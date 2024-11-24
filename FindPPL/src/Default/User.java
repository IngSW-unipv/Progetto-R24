package Default;

public class User {
	
    private String username;
    private String password;
    private Profile profile;

    //COSTRUTTORE
    public User(String username, String password, Profile profile)
    {    
    	this.username = username;
        this.password = password;
        this.profile = profile;
    }
}
