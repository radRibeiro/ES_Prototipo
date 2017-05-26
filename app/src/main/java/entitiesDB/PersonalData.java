package entitiesDB;

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
		String getName(){return name;}
		String getSurname(){return surname;}
		String getAddress(){return address;}
		int getAge(){return age;}
}
