package uberplus.entitiesDB;

import java.util.Calendar;
import java.util.Date;

public class ServiceRequest {
    private String originAddress;
    private String destinationAddress;
    private RequestStatus status;
    private float payment;
    private Calendar startTime;
    private Calendar estimatedEndTime;
    private String licensePlate;

    public enum RequestStatus { NOT_ACCEPTED, ACCEPTED, PAID }

    public ServiceRequest(String originAddress, String destinationAddress,String type) {
        this.originAddress = originAddress;
        this.destinationAddress = destinationAddress;
        this.payment = 0f;
        this.status = RequestStatus.NOT_ACCEPTED;
        this.startTime = createCalendarDate();
        this.estimatedEndTime = null;
        this.licensePlate = null;
    }

    public RequestStatus getStatus() {
        return status;
    }

  public  String getOriginAndDestAddress() {
        return originAddress + "//" + destinationAddress;
    }

    float getServicePayment() {
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


    private Calendar createCalendarDate(){
        Calendar calendar = Calendar.getInstance();
        return calendar;

    }

}
