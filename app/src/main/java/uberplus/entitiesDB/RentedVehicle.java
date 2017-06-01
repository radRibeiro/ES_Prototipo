package uberplus.entitiesDB;

public class RentedVehicle extends Vehicle {
int rentalDuration;
float monthlyFee; 
	public RentedVehicle(String licensePlate, float price,
			String category, String model, String brand, int year,int rentalDuration,float monthlyFee ) {
		super(licensePlate, price, category, model, brand, year);
		this.rentalDuration = rentalDuration;
		this.monthlyFee = monthlyFee;
		// TODO Auto-generated constructor stub
	}

}
