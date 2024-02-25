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
	
	boolean addFood(food food)
	{
		for(int i = 0; i<alimenti.size(); i++)
		{
			if((food.getName().equals(alimenti.get(i).getName())))
			{
				return false;
			}
		}
		return true;
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
}
