package Package;


import java.util.ArrayList;
import java.util.Scanner;

public class accountManager {
	
	final int MAX_ATTEMPTS = 3;
	
	private account _admin;
	private account _admin2;
	
	private ArrayList<account> users = new ArrayList<>();
	
	Scanner scan = new Scanner(System.in);
	
	private validatorPsw val;
	
	
	accountManager()
	{
		val = new validatorPsw();
		_admin = new account("Elian", "ADmin00");
		users.add(_admin);
		_admin2 = new account("Hannah", "GG1ers");
		users.add(_admin2);
	}
	
	account access()
	{
		System.out.println("Inserire username: ");
		String username = scan.next();
		System.out.println("Enter your password: ");
		String psw = scan.next();
		
		
		if(scanUsername(username, psw)!=null)
			return scanUsername(username, psw);

		return null;
	}
		
	void addUsername()
	{
		System.out.println("Inserire username: ");
		String username = scan.next();
		addPassword(username);
	}
	void addPassword(String username)
	{
		System.out.println("Enter your password: ");
		System.out.println("(The password has to include at least 2 uppercase letters, 3 lowercase letters and 1 digit)");
		String psw = scan.next();
		addAccount(username, psw);
	}
	
	void addAccount(String username, String psw)
	{
		if(val.check(psw))
		{
			account count = new account(username, psw);
			users.add(count);
			System.out.println("Your account has been created");
			listAccount();
		}
		else
		{
			System.out.println("Try again");
			addPassword(username);
		}
	}
	
	void removeAccount()
	{
		
	}
	
	
	void listAccount()
	{
		for(int i = 0; i<users.size(); i++)
		{
			System.out.println(users.get(i).getUser());
			System.out.println(users.get(i).getPsw());
		}
	}
	
	account scanUsername(String username, String psw)
	{		
		for(int j=0;j<users.size();j++)
		{
			if(username.equals(users.get(j).getUser()))
				if(psw.equals(users.get(j).getPsw()))				
					return users.get(j);
		}
		return null;
	}
}
