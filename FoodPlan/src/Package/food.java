package Package;

public class food {

	private String name;
	private float cost;
	private float quantity;
	
	//Costruttore
	food(String name, float cost, float quantity)
	{
		this.name = name;
		this.cost = cost;
		this.quantity = quantity;
	}
	
	String getName(){
		return name;}
	
	float getCost(){
		return cost;}
	
	float getQuantity(){
		return quantity;}
	
	void changeQuantity(float _quantity)
	{
		quantity = _quantity;
	}
}
