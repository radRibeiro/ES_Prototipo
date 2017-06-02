package uberplus.entitiesDB;

public class Vehicle  {
	String licensePlate;
	float price;
	String category;
	String model;
	String brand;
	int year;
	public Vehicle(String licensePlate,float price,String category
			,String model,String brand,int year){
		this.licensePlate = licensePlate;
		this.price = price;
		this.category = category;
		this.model = model;
		this.brand = brand;
		this.year = year;
	}
	public String getLicensePlate()
	{
		return licensePlate;
	}
	public float getPrice()
	{
		return price;
	}
	public String getCategory(){return category;}
	public String getModel(){return model;}
	public String getBrand(){return brand;}
	public int getYear(){return year;}
}
