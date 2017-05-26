package entitiesDB;

public class Costumer extends User {
int uberPoints;
	public Costumer(String email, String NIB, String password, PersonalData personalData,int uberPoints) {
		super(email, NIB, password, personalData);
		// TODO Auto-generated constructor stub
		this.uberPoints = uberPoints;
	}
	int getUberPoints(){return uberPoints;}
}
