package Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

import control.ControlRegister;

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
public Set<String>getUserEmails(){
    return usersPref.getStringSet("EMAILS",null);
}
public Set<String>getUserPasswords(){
    return usersPref.getStringSet("PASS",null);
}
}
