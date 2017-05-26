package entitiesDB;
import java.util.Calendar;

public class Transportation extends ServiceRequest {
	boolean isPrivate;
	public Transportation(String serviceID,String originAddress, String destinationAddress, float payment, Calendar startTime,
			Calendar estimatedEndTime, String licensePlate,boolean isPrivate) {
		super(serviceID,originAddress, destinationAddress, payment, startTime, estimatedEndTime, licensePlate);
		// TODO Auto-generated constructor stub
		this.isPrivate = isPrivate;
	}

}
