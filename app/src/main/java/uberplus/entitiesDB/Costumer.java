package uberplus.entitiesDB;

public class Costumer extends User {
int uberPoints;
	public Costumer(String email, String NIB, String password, PersonalData personalData,int uberPoints,String function) {
		super(email, NIB, password, personalData,function);
		// TODO Auto-generated constructor stub
		this.uberPoints = uberPoints;
	}
	int getUberPoints(){return uberPoints;}
}
