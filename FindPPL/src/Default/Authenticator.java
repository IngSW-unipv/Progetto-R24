package Default;

import java.util.ArrayList;
import java.util.List;

public class Authenticator {
	
	private Security security;
    private List<User> users;

    //COSTRUTTORE
    public Authenticator() {
        this.users = new ArrayList<>();
        this.security = new Security();
    }

    public boolean registerUser(String username, String password, Profile profile) {
    	
    	for (User user : users) {
            if (user.getUsername().equals(username))
            		return false;
    	}
    	
    	User newUser = new User(username, password, profile);
    	users.add(newUser);
    	return true;
    }

    public void removeUser(User user) {
        users.remove(user);
    }
    
    public List<User> getUsers(){
    	return users;
    }
    public User authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.login(password)) {
                return user;
            }
        }
        return null;
    }
}
