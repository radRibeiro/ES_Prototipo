package entitiesDB;
import java.util.ArrayList;
import java.util.Calendar;

public class FoodDelivery extends ServiceRequest {
	ArrayList<String> foodNames;
	ArrayList<Integer>quantityNames;
	public FoodDelivery(String serviceID,String originAddress, String destinationAddress, float payment, Calendar startTime,
			Calendar estimatedEndTime, String licensePlate,
			ArrayList<String> foodNames,ArrayList<Integer>quantityNames) {
		super(serviceID,originAddress, destinationAddress, payment, startTime, estimatedEndTime, licensePlate);
		// TODO Auto-generated constructor stub
		this.foodNames = new ArrayList<String>();
		this.quantityNames = new ArrayList<Integer>();
		this.foodNames = foodNames;
		this.quantityNames = quantityNames;
	}

}
