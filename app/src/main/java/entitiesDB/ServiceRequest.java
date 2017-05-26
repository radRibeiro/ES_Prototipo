package entitiesDB;
import java.util.Calendar;

public abstract class ServiceRequest {
	String serviceID;
	String originAddress;
	String destinationAddress;
	float payment;
	Calendar startTime;
	Calendar estimatedEndTime;
	String licensePlate;
	
	public ServiceRequest(String serviceID,String originAddress,String destinationAddress,float payment,Calendar startTime
			,Calendar estimatedEndTime,String licensePlate){
		this.serviceID = serviceID;
		this.originAddress = originAddress;
		this.destinationAddress = destinationAddress;
		this.payment = payment;
		this.startTime = startTime;
		this.estimatedEndTime = estimatedEndTime;
		this.licensePlate = licensePlate;
	}
	String getServiceID(){return serviceID;}
	String getOriginAndDestAddress(){return originAddress+"//"+destinationAddress;}
	float getServicePayment(){return payment;}
	Calendar getStartTime(){return startTime;}
	Calendar getEstEndTime(){return estimatedEndTime;}
	String getLicensePlate(){return licensePlate;}
	
}
