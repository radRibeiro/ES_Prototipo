package uberplus.entitiesDB;

import java.util.Calendar;
import java.util.Date;

import uberplus.utils.Utilities;

public class RentedVehicle extends Vehicle {
    private int rentalDuration;
    private float monthlyFee;
    private Calendar registrationDate;

    public RentedVehicle(String licensePlate, float price,
                         String category, String model, String brand, int year, int rentalDuration, float monthlyFee) {
        super(licensePlate, price, category, model, brand, year);
        this.rentalDuration = rentalDuration;
        this.monthlyFee = monthlyFee;
        registrationDate = Utilities.createCalendarDate();
    }

    public Date getRegistrationDate() {
        return registrationDate.getTime();
    }

    public int getRentalDuration() {
        return rentalDuration;
    }

    public float getMonthlyFee() {
        return monthlyFee;
    }

}
