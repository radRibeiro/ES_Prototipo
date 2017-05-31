package layout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fct.unl.pt.uberplus_p.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StartingFragment fragmentR = new StartingFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,fragmentR);
        fragmentTransaction.commit();

    }
    @Override
    public void onBackPressed(){
        //super.onBackPressed();
        StartingFragment fragmentR = new StartingFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,fragmentR);
        fragmentTransaction.commit();

    }
    @Override
    public void onResume(){
        super.onResume();
    }
    @Override
    public void onStop()
    {
        super.onStop();

    }

}
