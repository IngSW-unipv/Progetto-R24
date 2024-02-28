package Package;

import java.util.ArrayList;

public class day {
	
	private ArrayList<food> alimenti;
	private int number;
	
	//Costruttore
	day()
	{
		alimenti = new ArrayList<food>();
	}
	int getNum(){return number;}
	
	void addFood(food food, float quantity)
	{
		food newFood = new food(food.getName(), food.getCost(), quantity);
		alimenti.add(newFood);
	}
	
	void removeFood(food food)
	{
		alimenti.remove(food);
	}
	
	void ListDay()
	{
		for(int i = 0; i<alimenti.size(); i++)
		{
			System.out.println(alimenti.get(i).getName());
			System.out.println(alimenti.get(i).getQuantity());
			System.out.println(alimenti.get(i).getCost());
			System.out.println();
		}
	}

	ArrayList<food> getAlimenti()
	{
		return alimenti;
	}
}
