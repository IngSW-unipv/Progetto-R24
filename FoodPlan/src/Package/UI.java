package Package;

import java.util.Scanner;

public class UI {

	private Scanner scan;
	
	private accountManager manager;
	private account current;
	
	//Costruttore
	UI()
	{
		scan = new Scanner(System.in);
		manager = new accountManager();
		current = new account("Prova", "prova");
	}

	//Start
	void start()
	{
		System.out.println("Choose an option");
		accessOptions();
		System.out.println("Choose an option");
		accountOptions();
	}
	
	//Opzioni di accesso/registrazione
	void accessOptions()
	{
		System.out.println("1- Access");
		System.out.println("2- Sign up");
	
		//Rilevare l'input + risoluzione errore su scan.nextInt()
		int input = scan.nextInt();
		scan.nextLine();
	
		//Selezione dell'opzione
		switch(input)
		{
		case 1:
			logIn();
			break;
		case 2:
			singUp();
			break;
		default:
			System.out.println("The input is not correct");
			accessOptions();
		}
	}
	
	
	void singUp()
	{
		manager.addUsername();
		accountOptions();
	}

	
	void logIn()
	{
		current = manager.access();
		
		if(current != null)
		{
			System.out.println("Welcome "+current.getUser());
		}
		else
		{
			System.out.println("Account not found");
			logIn();
		}
	}
	
	void accountOptions()
	{
			
	}
}
