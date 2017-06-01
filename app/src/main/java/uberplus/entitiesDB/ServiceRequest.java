package uberplus.entitiesDB;
import java.util.Calendar;

public abstract class ServiceRequest {
	String serviceID;
	String originAddress;
	String destinationAddress;
	String status;
	float payment;
	Calendar startTime;
	Calendar estimatedEndTime;
	String licensePlate;
	
	public ServiceRequest(String serviceID,String originAddress,String destinationAddress,String status,float payment,Calendar startTime
			,Calendar estimatedEndTime,String licensePlate){
		this.serviceID = serviceID;
		this.originAddress = originAddress;
		this.destinationAddress = destinationAddress;
		this.payment = payment;
		this.status = status;
		this.startTime = startTime;
		this.estimatedEndTime = estimatedEndTime;
		this.licensePlate = licensePlate;
	}
	String getStatus(){return status;}
	String getServiceID(){return serviceID;}
	String getOriginAndDestAddress(){return originAddress+"//"+destinationAddress;}
	float getServicePayment(){return payment;}
	Calendar getStartTime(){return startTime;}
	Calendar getEstEndTime(){return estimatedEndTime;}
	String getLicensePlate(){return licensePlate;}
	
}
