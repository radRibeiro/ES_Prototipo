package uberplus.entitiesDB;
import java.io.Serializable;
import java.util.HashMap;

public abstract class User implements Serializable {
String email;
String NIB;
String password;
PersonalData personalData;
HashMap<String,ServiceRequest> services;
	public User(){
		
	}
	public User(String email,String NIB,String password,PersonalData personalData){
		this.email = email;
		this.NIB = NIB;
		this.password = password;
		this.personalData = personalData;
		services = new HashMap<String,ServiceRequest>();
	}
	public String getEmail(){return email;}
	public String getNIB(){return NIB;}
	public String getPassword(){return password;}
	public PersonalData getPersonalData(){return personalData;}
	public void addServiceRequest(ServiceRequest sr){services.put(sr.getServiceID(), sr);}
}
