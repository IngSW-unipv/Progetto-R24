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
	void addFood(account current)
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
		
		endAddingFood(current);
		return;
	}
	
	void endAddingFood(account current)
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
			return;
		default:
			System.out.println("The input is not correct");
			endAddingFood(current);
		}
	}
	
	void removeFood(account curr)
	{
		System.out.println("Insert Food Name: ");
		String name = scan.nextLine();
		curr.removeFood(name);
	}
	
	void editFood(account curr)
	{
		System.out.println("Select the food to edit");
		for(int i = 0; i<curr.getFoods().size(); i++)
		{
			System.out.print(i+1 + "- " + curr.getFoods().get(i).getName());
			System.out.println(" " + curr.getFoods().get(i).getCost());
		}
		int sel = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Type again the name and the cost of the food");
		addFood(curr);
		curr.getFoods().remove(sel);
		System.out.println("Your food has been modified correctly");
		
	}

	void listFoods(account curr)
	{
		if(curr.getFoods().size() > 0) 
		{
			for(int i = 0; i<curr.getFoods().size(); i++)
			{
				System.out.print("- " + curr.getFoods().get(i).getName());
				System.out.println(" " + curr.getFoods().get(i).getCost());
			}
		}
		else 
			System.out.println("Your Food List is Empty");
	}
}
