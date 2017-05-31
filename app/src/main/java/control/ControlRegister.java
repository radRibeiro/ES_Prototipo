package control;

import java.util.HashMap;

import Utils.Preferences;
import entitiesDB.Costumer;
import entitiesDB.Driver;
import entitiesDB.PersonalData;
import entitiesDB.User;

public class ControlRegister {
    HashMap<String, User> users;
        public ControlRegister(){

            users = new HashMap<>();

        }
        public void addUser(String name,String surname, int age,String address,String email,String NIB,String password,String drivingL,String licensePlate,String function){
            PersonalData personalData = new PersonalData(name,surname,address,age);
            User newUser = null;
            if(function.equals("Costumer")){
                 newUser  = new Costumer(email,NIB,password,personalData,0);

            }
            else if(function.equals("Driver")){
                newUser  = new Driver(email,NIB,password,personalData,drivingL,licensePlate);
            }
            users.put(email,newUser);
        }
       public boolean hasUser(String email){
            return users.containsKey(email);
        }
}
