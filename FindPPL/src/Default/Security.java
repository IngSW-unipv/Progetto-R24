package Default;

public class Security {
	
    public boolean verifyPassword(String inputPassword, String actualPassword) {
        // Logica per verificare una password
        return inputPassword.equals(actualPassword);
    }
}
