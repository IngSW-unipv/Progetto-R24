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
	
	void addFood(food food)
	{
		alimenti.add(food);
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
