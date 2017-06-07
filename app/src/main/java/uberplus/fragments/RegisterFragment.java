package uberplus.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

import fct.unl.pt.uberplus_p.R;
import uberplus.activities.MainActivity;
import uberplus.control.ControlRegister;
import uberplus.entitiesDB.PersonalData;
import uberplus.entitiesDB.User;
import uberplus.utils.Preferences;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    public ControlRegister cr;
    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("User registration");
        final View v =  inflater.inflate(R.layout.fragment_register, container, false);
        final Preferences pref = new Preferences(getActivity());
        cr = new ControlRegister();
        final EditText name = (EditText)v.findViewById(R.id.NameT);
        final EditText surName = (EditText)v.findViewById(R.id.SurnameT);
        final EditText age = (EditText)v.findViewById(R.id.AgeT);
        final  EditText address = (EditText)v.findViewById(R.id.AddressT);
        final  EditText email = (EditText)v.findViewById(R.id.emailT);
        final EditText nib = (EditText)v.findViewById(R.id.NIBT);
        final  EditText password = (EditText)v.findViewById(R.id.passwordT);
        final  EditText passwordc = (EditText)v.findViewById(R.id.confPasswordT);
        final EditText drivingL = (EditText)v.findViewById(R.id.drivingLicenseT);
        final EditText licenseP = (EditText)v.findViewById(R.id.licensePlateT);
        final RadioButton costumerR = (RadioButton)v.findViewById(R.id.costumerRadio);
        final RadioButton driverR = (RadioButton)v.findViewById(R.id.driverRadio);


        costumerR.setChecked(true);
        costumerR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                driverR.setChecked(false);
                drivingL.setVisibility(view.GONE);
                licenseP.setVisibility(view.GONE);
            }
        });
        driverR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                costumerR.setChecked(false);
                drivingL.setVisibility(view.VISIBLE);
                licenseP.setVisibility(view.VISIBLE);
            }
        });
        Button confirmRegist = (Button) v.findViewById(R.id.submitT);
        confirmRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<User> users = pref.getUsersCollection();
                if(pref.getUsersCollection()==null){
                    users =   new ArrayList<>();
                    pref.setUsersCollection(users);
                }
                else if(pref.getUsersCollection()!=null){

                    pref.setUsersCollection(users);
                }
                    if(   name.getText().toString().equals("")
                            ||surName.getText().toString().equals("")
                            ||age.getText().toString().equals("")
                            ||address.getText().toString().equals("")
                            ||nib.getText().toString().equals("")
                            ||email.getText().toString().equals("")
                            ||password.getText().toString().equals("")
                            ||passwordc.getText().toString().equals("")){
                        Toast.makeText(getActivity(),"Not all fields are filled",Toast.LENGTH_SHORT).show();
                    }
                else if(cr.hasUser(email.getText().toString())){
                        Toast.makeText(getActivity(),"Another user is already using the email",Toast.LENGTH_SHORT).show();
                    }

                else if(driverR.isChecked() &&drivingL.getText().toString().equals("")&&licenseP.getText().toString().equals("")){
                        Toast.makeText(getActivity(),"Not all fields are filled",Toast.LENGTH_SHORT).show();
                }
                else if(!password.getText().toString().equals(passwordc.getText().toString())){
                        Toast.makeText(getActivity(),"Passwords do not match",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(driverR.isChecked()){
                            PersonalData personalData = new PersonalData(name.getText().toString()
                                    ,surName.getText().toString()
                                    ,address.getText().toString()
                                    , Integer.parseInt(age.getText().toString()));
                         cr.addUser(
                                 name.getText().toString(),
                                surName.getText().toString(),
                                Integer.parseInt(age.getText().toString()),
                                address.getText().toString(),
                                email.getText().toString(),
                                nib.getText().toString(),
                                password.getText().toString(),
                                drivingL.getText().toString(),
                                licenseP.getText().toString(),
                                "Driver");
                            User u = cr.getUser(name.getText().toString());
                            System.out.println(u);
                            users.add(u);
                        }
                        else if(costumerR.isChecked()){
                            PersonalData personalData = new PersonalData(name.getText().toString()
                                    ,surName.getText().toString()
                                    ,address.getText().toString()
                                    , Integer.parseInt(age.getText().toString()));
                            cr.addUser(
                                    name.getText().toString(),
                                    surName.getText().toString(),
                                    Integer.parseInt(age.getText().toString()),
                                    address.getText().toString(),
                                    email.getText().toString(),
                                    nib.getText().toString(),
                                    password.getText().toString(),
                                    "",
                                    "",
                                    "Costumer");

                            User u = cr.getUser(name.getText().toString());
                            System.out.println(u);
                            users.add(u);
                        }
                        Toast.makeText(getActivity(),"New user added",Toast.LENGTH_SHORT).show();
                        if(pref.getUserEmails()!=null||pref.getUserPasswords()!=null){
                        Set<String> emails = pref.getUserEmails();
                        Set<String>passwords = pref.getUserPasswords();
                            emails.add(email.getText().toString());
                            passwords.add(password.getText().toString());
                            pref.storeUserEmails(emails);
                            pref.storeUserPasswords(passwords);
                        }else{
                            Set<String> emails = new ArraySet<String>();
                            Set<String> passwords = new ArraySet<String>();
                            emails.add(email.getText().toString());
                            passwords.add(password.getText().toString());
                            pref.storeUserEmails(emails);
                            pref.storeUserPasswords(passwords);
                        }
                        pref.setUsersCollection(users);
                        pref.storeControlRegister(cr);
                        StartingFragment fragmentR = new StartingFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.content_frame,fragmentR);
                        fragmentTransaction.commit();

                    }
            }
        });
        return v;
    }
    public ControlRegister  onRetainCustomNonConfigurationInstance(){
        return cr;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }

    }
    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
