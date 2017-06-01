package uberplus.entitiesDB;
import java.util.ArrayList;
import java.util.Calendar;

public class FoodDelivery extends ServiceRequest {
	ArrayList<String> foodNames;
	ArrayList<Integer>quantityNames;
	public FoodDelivery(String serviceID,String originAddress, String destinationAddress, String status, float payment, Calendar startTime,
			Calendar estimatedEndTime, String licensePlate,
			ArrayList<String> foodNames,ArrayList<Integer>quantityNames) {
		super(serviceID,originAddress, destinationAddress,status, payment, startTime, estimatedEndTime, licensePlate);
		// TODO Auto-generated constructor stub
		this.foodNames = new ArrayList<String>();
		this.quantityNames = new ArrayList<Integer>();
		this.foodNames = foodNames;
		this.quantityNames = quantityNames;
	}

}
