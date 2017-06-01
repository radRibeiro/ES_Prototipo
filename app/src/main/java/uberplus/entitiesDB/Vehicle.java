package uberplus.entitiesDB;

public class Vehicle  {
	String licensePlate;
	float price;
	String category;
	String model;
	String brand;
	String year;
	public Vehicle(String licensePlate,float price,String category
			,String model,String brand,String year){
		this.licensePlate = licensePlate;
		this.price = price;
		this.category = category;
		this.model = model;
		this.brand = brand;
		this.year = year;
	}
	String getLicensePlate()
	{
		return licensePlate;
	}
}
