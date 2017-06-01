package uberplus.entitiesDB;

public class PersonalData {
String name;
String surname;
String address;
int age;
		public PersonalData(String name,String surname, String address,int age){
			this.name = name;
			this.surname = surname;
			this.address = address;
			this.age = age;
		}
		public String getName(){return name;}
	public String getSurname(){return surname;}
	public 	String getAddress(){return address;}
	public	int getAge(){return age;}
}
