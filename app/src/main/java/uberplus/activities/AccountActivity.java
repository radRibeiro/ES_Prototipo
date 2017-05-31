package uberplus.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fct.unl.pt.uberplus_p.R;
import uberplus.fragments.HomeFragment;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        HomeFragment fragmentR = new HomeFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,fragmentR);
        fragmentTransaction.commit();
    }
    @Override
    public void onBackPressed(){
        //super.onBackPressed();
        HomeFragment fragmentR = new HomeFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,fragmentR);
        fragmentTransaction.commit();

    }
}
