package uberplus.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Set;

import uberplus.control.ControlRegister;

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
public String getEmail(){
    return usersPref.getString("EMAIL",null);
}
public ControlRegister getControlRegister(){
    Gson gson = new Gson();
    String json = usersPref.getString("CONTROLREG","");
    ControlRegister cr = gson.fromJson(json,ControlRegister.class);
    return cr;
}
public Set<String>getUserEmails(){
    return usersPref.getStringSet("EMAILS",null);
}
public Set<String>getUserPasswords(){
    return usersPref.getStringSet("PASS",null);
}

}
