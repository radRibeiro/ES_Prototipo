package uberplus.entitiesDB;
import java.util.Calendar;

public class Transportation extends ServiceRequest {
	boolean isPrivate;
	public Transportation(String serviceID,String originAddress, String destinationAddress, String status, float payment, Calendar startTime,
			Calendar estimatedEndTime, String licensePlate,boolean isPrivate) {
		super(serviceID,originAddress, destinationAddress,status, payment, startTime, estimatedEndTime, licensePlate);
		// TODO Auto-generated constructor stub
		this.isPrivate = isPrivate;
	}

}
