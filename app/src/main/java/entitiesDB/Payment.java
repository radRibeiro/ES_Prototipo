package entitiesDB;

import java.util.Calendar;

public class Payment {
	User from,to;
	float paymentValue;
	Calendar dateP;
	public Payment(User from, User to, float paymentValue,Calendar dateP){
		this.from = from;
		this.to= to;
		this.paymentValue = paymentValue;
		this.dateP = dateP;			
	}
}
