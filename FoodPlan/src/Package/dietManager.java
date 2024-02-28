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
		
		System.out.println("Create/Modify Diet");
		
	}	
	
	void addDay(account curr)
	{	
		day day = new day();
		int num = curr.addDay(day);
		
		System.out.println("Day "+ num +" created");
		
		modifyDay(curr, num);
	}
	
	void selectDay(account curr)
	{
		ArrayList<day> _days = curr.getDays();
		int j;
		
		if(_days.size()>0)
			for(int i = 0; i<_days.size(); i++)
			{
				j = i+1;
				System.out.println("Day "+j);
			}
		else
			System.out.println("Your day's List is empty");
	}
	
	void modifyDay(account curr, int dayNumber)
	{
		System.out.println("Insert the name of the food");
		String foodName = scan.nextLine();
		
		for(int i = 0; i<curr.getFoods().size(); i++)
			if((foodName.equals(curr.getFoods().get(i).getName())))
			{
				float quant = addQuantity();
				if(quant>0)
				{
					curr.getDays().get(dayNumber).addFood(curr.getFoods().get(i));
					System.out.println("Alimento Aggiunto Correttamente");
					return;
				}
			}
	}
	/*
	 * Food food = getFood();
		float quantity = addQuantity();
		if(quantity == 0)
		{
			System.out.println("The quantity has to be more than zero");
			addQuantity();
		}
		food.changeQuantity(quantity);
		//meal.addAlimento(food);
	 */
	
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
