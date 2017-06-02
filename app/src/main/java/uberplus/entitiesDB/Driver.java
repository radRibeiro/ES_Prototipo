package uberplus.entitiesDB;
import java.util.ArrayList;

public class Driver extends User {
String driverLicense;
String licensePlate;
ArrayList<Vehicle>vehicles;
	public Driver(String email, String NIB, String password, PersonalData personalData,
			String driverLicense,String licensePlate,String function) {
		super(email, NIB, password, personalData,function);
		// TODO Auto-generated constructor stub
		this.driverLicense = driverLicense;
		this.licensePlate = licensePlate;
		vehicles = new ArrayList<>();
		if(!licensePlate.equals(""))
		{
			Vehicle startingVehicle = new PersonalVehicle(licensePlate,0.0f,"not available",
					"not available","not available",-1);
			vehicles.add(startingVehicle);
		}
		
	}
		String	getDriverLicense(){return driverLicense;}
		String  getCarLicensePlate(){return licensePlate;}
		void rentVehicle(Vehicle v){vehicles.add(v);} 
		
}
