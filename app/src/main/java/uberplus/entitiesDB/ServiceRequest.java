package uberplus.entitiesDB;

import java.util.Calendar;
import java.util.Date;

import uberplus.utils.Utilities;

public abstract class ServiceRequest {
    private String originAddress;
    private String destinationAddress;
    private RequestStatus status;
    private float payment;
    private Calendar startTime;
    private Calendar estimatedEndTime;
    private String licensePlate;
    private int serviceID;


    public enum RequestStatus { NOT_ACCEPTED, ACCEPTED, PAID }

    public ServiceRequest(String originAddress, String destinationAddress) {
        this.originAddress = originAddress;
        this.destinationAddress = destinationAddress;
        this.payment = 0f;
        this.status = RequestStatus.NOT_ACCEPTED;
        this.startTime = Utilities.createCalendarDate();
        this.estimatedEndTime = null;
        this.licensePlate = null;
    }


    public String getServiceType() {
        if(this instanceof FoodDelivery)
            return "Food Delivery";
        else return "Transportation";
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public String getOriginAndDestAddress() {
        return originAddress + "//" + destinationAddress;
    }

    public String getOriginAddress() {
        return originAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public float getPayment() {
        return payment;
    }

    public float getServicePayment() {
        return payment;
    }

    public Date getStartTime() {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return startTime.getTime();
    }

    public Date getEstEndTime() {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return estimatedEndTime.getTime();
    }

    public void setEstimatedEndTime(Calendar setCalendar){
        estimatedEndTime = setCalendar;
    }

    public void setLicensePlate(String setLicense) {
        licensePlate = setLicense;
    }

}
