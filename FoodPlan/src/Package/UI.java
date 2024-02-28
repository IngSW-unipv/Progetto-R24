package Package;

import java.util.Scanner;

public class UI {

	private Scanner scan;
	
	private accountManager manager;
	private foodManager fdManager;
	private dietManager dtManager;
	private account current;
	
	//Costruttore
	UI()
	{
		scan = new Scanner(System.in);
		manager = new accountManager();
		fdManager = new foodManager();
		dtManager = new dietManager();
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
		System.out.println("1 - Create/Modify Day");
		System.out.println("2 - Calculator");
		System.out.println("3 - Edit Food List");
		System.out.println("4 - Log Out");
		
		int input = scan.nextInt();
		
		//Per evitare errore con scan.nextInt
		scan.nextLine();
		switch(input)
		{
		case 1:
			actionsOnDays();
			accountOptions();
		break;
		case 2:
			dtManager.dietOptions(current);
			break;
		case 3:
			editFoodList();
			accountOptions();
			break;
		case 4:
			current = null;
			System.out.println("You Logged Out");
			start();
			break;
		default:
			System.out.println("Incorrect input");
			accountOptions();
		}
	}
	
	void actionsOnDays()
	{
		System.out.println("1 - Create Day");
		System.out.println("2 - Modify Day");
		
		int sel = scan.nextInt();
		scan.nextLine();
		
		if(sel == 1)
			dtManager.addDay(current);
		else if(sel == 2)
			dtManager.selectDay(current);
	}
	
	void editFoodList()
	{
		System.out.println("1 - Create New Food");
		System.out.println("2 - Remove Existing Food");
		System.out.println("3 - Edit Existing Food");
		System.out.println("4 - List the Foods");
		System.out.println("5 - Back");
		int sel = scan.nextInt();
		scan.nextLine();
		if(sel == 1)
			fdManager.addFood(current);
		else if(sel == 2)
			fdManager.removeFood(current);
		else if(sel == 3)
			fdManager.editFood(current);
		else if(sel == 4)
			fdManager.listFoods(current);
		else if(sel == 5)
			return;
	}
}
