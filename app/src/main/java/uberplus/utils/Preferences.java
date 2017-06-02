package uberplus.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Set;

import uberplus.control.ControlRegister;
import uberplus.entitiesDB.User;
import uberplus.entitiesDB.Vehicle;

/**
 * Created by ricardo on 30/05/2017.
 */

public class Preferences {
    Context context;
    SharedPreferences.Editor usersEditor;
    SharedPreferences usersPref;

public Preferences(Context context){

this.context = context;
usersPref = context.getSharedPreferences("users6",0);
    usersEditor = usersPref.edit();
}
public void storeUserEmails(Set<String> emails){
    usersEditor.putStringSet("EMAILS",emails);
    usersEditor.commit();
}
public void storeUserPasswords(Set<String> passwords){
    usersEditor.putStringSet("PASS",passwords);
    usersEditor.commit();
}
public void storeControlRegister(ControlRegister cr){
    Gson gson = new Gson();
    String json = gson.toJson(cr);
    usersEditor.putString("CONTROLREG",json);
    usersEditor.commit();
}
public void storeUserEmail(String email){
    usersEditor.putString("EMAIL",email);
    usersEditor.commit();
}
    public void storeUserFunction(String function){
        usersEditor.putString("FUNCTION",function);
        usersEditor.commit();
    }
public String getEmail(){
        return usersPref.getString("EMAIL",null);
    }

    public String getType(){
        return usersPref.getString("FUNCTION",null);
    }
public ControlRegister getControlRegister(){
    Gson gson = new Gson();
    String json = usersPref.getString("CONTROLREG","");
    ControlRegister cr = gson.fromJson(json,ControlRegister.class);
    return cr;
}
public void setCarsCollection(ArrayList<Vehicle> vehicles){
    Gson gson = new Gson();
    String json = gson.toJson(vehicles);
    usersEditor.putString("CARS",json);
    usersEditor.commit();
}
public void setUsersCollection(ArrayList<User> users){
    Gson gson = new Gson();
    String json = gson.toJson(users);
    usersEditor.putString("USERS",json);
    usersEditor.commit();
}
public ArrayList<User> getUsersCollection(){
    Gson gson = new Gson();
    String json = usersPref.getString("USERS",null);
    Type type = new TypeToken<ArrayList<User>>(){}.getType();
    ArrayList<User> cr = gson.fromJson(json,type);
    return cr;
}
public ArrayList<Vehicle> getCarsCollection(){
    Gson gson = new Gson();
    String json = usersPref.getString("CARS",null);
    Type type = new TypeToken<ArrayList<Vehicle>>(){}.getType();
    ArrayList<Vehicle> cr = gson.fromJson(json,type);
    return cr;
}
public Set<String>getUserEmails(){
    return usersPref.getStringSet("EMAILS",null);
}
public Set<String>getUserPasswords(){
    return usersPref.getStringSet("PASS",null);
}

}
