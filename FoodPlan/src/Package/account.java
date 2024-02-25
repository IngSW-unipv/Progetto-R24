package Package;

import java.util.ArrayList;

public class account {
	
	private String username;
	private String psw;
	private ArrayList<food> foods;
	private ArrayList<day> days;
	
	account(String _username, String _psw)
	{
		this.username = _username;
		this.psw = _psw;
		foods = new ArrayList<food>();
		days = new ArrayList<day>();
	}
	boolean addFood(String name, float cost)
	{
		//Controllo per verificare che l'alimento non esista già
		if(foods != null)
		{
			for(int i = 0; i<foods.size(); i++)
			{
				if(name.equalsIgnoreCase(foods.get(i).getName()))
				{
					return false;
				}
			}
		}
		food food = new food(name, cost, 0);
		foods.add(food);
		
		//DEBUG
		System.out.print(food.getName() + "  ");
		System.out.println(food.getCost() + " Euro/Kg");
		
		return true;
	}
	
	int addDay(day day)
	{
		days.add(day);
		return days.size();
	}
	
	ArrayList<day> getDays(){return days;}
	String getUser(){
		return username;
	}
	String getPsw(){
		return psw;
	}
	ArrayList<food> getFoods()
	{
		return foods;
	}
}
