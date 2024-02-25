package Package;

import java.util.Scanner;

public class foodManager {
	
	//Dichiarazione dello Scanner
	Scanner scan;
	
	//Costruttore
	foodManager()
	{
		scan = new Scanner(System.in);
	}
	
	//Funzione per aggiungere un nuovo alimento
	boolean addFood(account current)
	{
		//Inserimento del nome e del prezzo
		System.out.println("Insert Food Name: ");
		String name = scan.nextLine();
		System.out.println("Insert Euro/Kg: ");
		float cost = scan.nextFloat();
		
		//Per evitare un errore con la funzione scan.nextFloat();
		scan.nextLine();
		
		if(current.addFood(name, cost))
			System.out.println("Your new food is being added correctly");
		else
		{
			System.out.println("Error adding the food. Try again");
			addFood(current);
		}
		
		if(!endAddingFood(current))
			return true;
		else 
			return false;
	}
	
	boolean endAddingFood(account current)
	{
		System.out.println("Would you continue adding foods?");
		System.out.println("1 - Yes");
		System.out.println("2 - No");
		
		int input = scan.nextInt();
		
		//Per evitare un errore con la funzione scan.nextFloat();
		scan.nextLine();
	
		switch(input)
		{
		case 1:
			addFood(current);
			break;
		case 2:
			return false;
		default:
			System.out.println("The input is not correct");
			endAddingFood(current);
		}
		return true;
	}
}
