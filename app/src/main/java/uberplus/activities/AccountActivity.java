package uberplus.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import fct.unl.pt.uberplus_p.R;
import uberplus.control.ServiceRequestControl;
import uberplus.entitiesDB.ServiceRequest;
import uberplus.fragments.HomeFragment;
import uberplus.fragments.RequestServiceFragment;
import uberplus.fragments.ServicesFragment;

public class AccountActivity extends AppCompatActivity implements RequestServiceFragment.OnFragmentInteractionListener, ServicesFragment.ServiceFragmentInteraction {

    ServiceRequestControl requestControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        HomeFragment fragmentR = new HomeFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame_account,fragmentR);
        fragmentTransaction.commit();

        requestControl = new ServiceRequestControl();
    }
    @Override
    public void onBackPressed(){
        //super.onBackPressed();
        HomeFragment fragmentR = new HomeFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame_account,fragmentR);
        fragmentTransaction.commit();

    }

    @Override
    public void createRequest(ServiceRequest request) {
        requestControl.createRequest(request);
    }

    @Override
    public ArrayList<ServiceRequest> getRequestList() {
        return requestControl.getAllRequests();
    }
}
