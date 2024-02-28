package Package;

import java.util.ArrayList;
import java.util.Scanner;


public class dietManager {
	
	private Scanner scan;
	private account current;
	private int index;
	
	//Costruttore
	dietManager ()
	{
		index = 0;
		current = new account("example", "example");
		scan = new Scanner (System.in);
	}
	
	//Opzioni dell'account corrente
	void dietOptions (account account)
	{
		current = account;
		
		System.out.println("Select a funcion:");
		System.out.println("1- List Days");
		System.out.println("2- Calculate the money spent and food's quantity");
		System.out.println("3- Back");
		
		int x = scan.nextInt();
		scan.nextLine();
		
		if(x == 1 || x == 2)
		{
			System.out.println("Are you interested on a single day or a raw of days?");
			System.out.println("1- Single");
			System.out.println("2- Raw");
			int y = scan.nextInt();
			scan.nextLine();
			
			ListDays(current, y);
		}
			
	
	}	
	
	void addDay(account curr)
	{	
		day day = new day();
		int num = curr.addDay(day);
		
		System.out.println("Day "+ num +" created");
	}
	
	void selectDay(account curr)
	{
		int j;
		
		if(curr.getDays().size()>0)
		{
			System.out.println("Insert the number of the day to select:");
			for(int i = 0; i<curr.getDays().size(); i++)
			{
				j = i+1;
				System.out.println("Day "+j);
				
				for(int k = 0; k < curr.getDays().get(i).getAlimenti().size(); k++)
				{
					System.out.println(" - "+ curr.getDays().get(i).getAlimenti().get(k).getName());
					System.out.println(curr.getDays().get(i).getAlimenti().get(k).getQuantity()+ " grams");
				}
			}
			
			int input = scan.nextInt();
			scan.nextLine();
			
			
			if(input<=curr.getDays().size())
			{
				System.out.println("1- Add Foods");
				System.out.println("2- Remove Foods");
				int x = scan.nextInt();
				scan.nextLine();
			
				modifyDay(curr, input-1, x);
					
			}
				
			else
			{
				System.out.println("The day number is not correct");
				selectDay(curr);
			}
		}
		else
			System.out.println("Your List of Days is empty");
	}
	
	void modifyDay(account curr, int dayNumber, int mode)
	{	
		if(mode == 1)
		{
			if(curr.getFoods().size() != 0)
			{
				System.out.println("Insert the name of the food");
				String foodName = scan.nextLine();
				System.out.println("Insert the quantity you should eat (grams)");
				float quant = scan.nextFloat();
				scan.nextLine();
				
				for(int i = 0; i<curr.getFoods().size(); i++)
				{
					if((foodName.equals(curr.getFoods().get(i).getName())))
					{
						curr.getDays().get(dayNumber).addFood(curr.getFoods().get(i), quant);
						
						System.out.println("Food Added Correctly");
						endAddingOnDays(curr, dayNumber);
						return;
					}
				}
				System.out.println("The Food Does Not Exist in your Food List");
				System.out.println("1- Try again");
				System.out.println("2- Exit");
				int in = scan.nextInt();
				scan.nextLine();
				if(in == 1)
					modifyDay(curr, dayNumber, mode);
				else if(in == 2)
					return;
			}
			else
			{
				System.out.println("Your Food List is Empty");
				System.out.println("Add at least one food to your food list to modify your days");
			}
		}
		else if(mode == 2)
		{
			if(curr.getDays().get(dayNumber).getAlimenti().size() > 0)
			{
				for(int k = 0; k < curr.getDays().get(dayNumber).getAlimenti().size(); k++)
				{
					System.out.println(" - "+ curr.getDays().get(dayNumber).getAlimenti().get(k).getName());
					System.out.println(curr.getDays().get(dayNumber).getAlimenti().get(k).getQuantity()+ " grams");
					
					System.out.println("Insert the name of the food you want to remove:");
				
					String foodName = scan.nextLine();
				
				
					for(int i = 0; i<curr.getFoods().size(); i++)
					{
						if((foodName.equals(curr.getDays().get(dayNumber).getAlimenti().get(i).getName())))
						{
							curr.getDays().get(i).removeFood(curr.getFoods().get(i));
							System.out.println("Food Removed Correctly");
							endRemovingOnDays(curr, dayNumber);
							return;
						}
					}
					System.out.println("Your Food Name does Not Exist");
				}
			}
			else
			{
				System.out.println("Your food List of this day is empty");
				System.out.println("You must have at least one food to remove foods");
			}
		}
	}

	void endAddingOnDays(account curr, int i)
	{
		System.out.println("Would you continue adding foods?");
		System.out.println("1 - Yes");
		System.out.println("2 - No");
		
		int input = scan.nextInt();
		
		//Per evitare un errore con la funzione scan.nextInt();
		scan.nextLine();
	
		switch(input)
		{
		case 1:
			modifyDay(curr, i, 1);
			break;
		case 2:
			return;
		default:
			System.out.println("The input is not correct");
			endAddingOnDays(current, i);
		}
	}
	
	void endRemovingOnDays(account curr, int i)
	{
		System.out.println("Would you continue removing foods?");
		System.out.println("1 - Yes");
		System.out.println("2 - No");
		
		int input = scan.nextInt();
		
		//Per evitare un errore con la funzione scan.nextFloat();
		scan.nextLine();
	
		switch(input)
		{
		case 1:
			modifyDay(curr, i, 2);
			break;
		case 2:
			return;
		default:
			System.out.println("The input is not correct");
			endRemovingOnDays(current, i);
		}
	}
	
	void ListDays(account curr, int op)
	{
		if(op == 1)
		{
			System.out.println("Type the number of the day");
		}
		else if(op == 2)
			System.out.println("Type the number of days (from now to N days ago)");
		
		
		for(int i = 0; i<curr.getDays().size(); i++)
		{
			for(int k = 0; k < curr.getDays().get(i).getAlimenti().size(); k++)
			{
				System.out.println(" - "+ curr.getDays().get(i).getAlimenti().get(k).getName());
				System.out.println(curr.getDays().get(i).getAlimenti().get(k).getQuantity()+ " grams");
			}
		}
		
		int num = scan.nextInt();
		scan.nextLine();
		
		if(num <= curr.getDays().size() && num > 0)
		{
			float money = 0, quant = 0;
			
			if(op == 1)
			{
				System.out.println("Calcolo soldi spesi e quantita");
				//for(int k = 0; k<curr.getDays().get(num).getAlimenti().size(); k++)
				{
					//money+=curr.getDays().get(num).getAlimenti().get(k).getCost();
					
					//quant+=curr.getDays().get(num).getAlimenti().get(k).getQuantity();
					//System.out.println(" - "+ curr.getDays().get(num).getAlimenti().get(k).getName());
					//System.out.println(curr.getDays().get(num).getAlimenti().get(k).getQuantity()+ " grams");
				}
			}
		}
		else
			System.out.println("Number of days not correct");
	}
	
	food getFood()
	{
		System.out.println("Type the name of a food:");
		String food = scan.nextLine();
		if(foodCheck(food, current) !=null)
				return foodCheck(food, current);
		else
		{
			System.out.println("The name of the food does not exist in your Food List");
			System.out.println("Will you:");
			System.out.println("1 - Type again");
			System.out.println("2 - Create a new food");
			getFood();
			
		}
		return null;
	}
	
	food foodCheck(String foodName, account account)
	{
		for(int i = 0; i < account.getFoods().size(); i++)
			if(foodName.equalsIgnoreCase(account.getFoods().get(i).getName()))
				return account.getFoods().get(i);
		
		return null;
	}
	
	float addQuantity()
	{
		System.out.println("Insert the quantity:");
		float quantity = scan.nextFloat();
		scan.nextLine();
			
		if(quantity>=0)
			return quantity;
		else
		{
			return 0;
		}
	}
	
	boolean listDiet(account x)
	{
		if(x.getFoods().size()!= 0)
		{
			System.out.println("The foods you added to your list are:");
			for(int i = 0; i < x.getFoods().size(); i++)
			{
				String name = x.getFoods().get(i).getName();
				float cost = x.getFoods().get(i).getCost();
				System.out.println(name + ", "+ cost+" euro/kg");
			}
			return true;
		}
		else
			return false;
		
	}
}
