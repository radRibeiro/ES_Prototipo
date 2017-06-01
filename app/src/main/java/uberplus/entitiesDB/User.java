package uberplus.entitiesDB;
import java.util.HashMap;

public abstract class User {
String email;
String NIB;
String password;
PersonalData personalData;
HashMap<String,ServiceRequest> services;

	public User(String email,String NIB,String password,PersonalData personalData){
		this.email = email;
		this.NIB = NIB;
		this.password = password;
		this.personalData = personalData;
		services = new HashMap<String,ServiceRequest>();
	}
	String getEmail(){return email;}
	String getNIB(){return NIB;}
	String getPassword(){return password;}
	PersonalData getPersonalData(){return personalData;}
	void addServiceRequest(ServiceRequest sr){services.put(sr.getServiceID(), sr);}
}
