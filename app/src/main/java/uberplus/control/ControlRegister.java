package uberplus.control;

import java.io.Serializable;
import java.util.HashMap;

import uberplus.entitiesDB.Costumer;
import uberplus.entitiesDB.Driver;
import uberplus.entitiesDB.PersonalData;
import uberplus.entitiesDB.User;

public class ControlRegister implements Serializable {
    HashMap<String, User> users;
        public ControlRegister(){
            users = new HashMap<>();
        }
        public void addUser(String name,String surname, int age,String address,String email,String NIB,String password,String drivingL,String licensePlate,String function){
            PersonalData personalData = new PersonalData(name,surname,address,age);
            User newUser = null;
            if(function.equals("Costumer")){
                 newUser  = new Costumer(email,NIB,password,personalData,0,function);
            }
            else if(function.equals("Driver")){
                newUser  = new Driver(email,NIB,password,personalData,drivingL,licensePlate,function);
            }
            users.put(email,newUser);
        }
       public boolean hasUser(String email){
            return users.containsKey(email);
        }
        public User getUser(String email){
            return users.get(email);
        }
}
