package Package;

public class account {
	
	private String username;
	private String psw;

	
	account(String _username, String _psw)
	{
		this.username = _username;
		this.psw = _psw;
	}
	
	
	String getUser(){
		return username;
	}
	String getPsw(){
		return psw;
	}
}
