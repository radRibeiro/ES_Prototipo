package uberplus.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import fct.unl.pt.uberplus_p.R;
import uberplus.activities.AccountActivity;
import uberplus.activities.MainActivity;
import uberplus.entitiesDB.User;
import uberplus.utils.Preferences;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.home_menu, container, false);
        Preferences pref = new Preferences(getActivity());
        ((AccountActivity)getActivity()).getSupportActionBar().setTitle("Home");
        //  ControlRegister cr = pref.getControlRegister();
        //  ArrayList<User> users = pref.getUsersCollection();

        //TextView email = (TextView) v.findViewById(R.id.textViewEmail);
        //TextView name = (TextView) v.findViewById(R.id.textViewName);
        TextView function = (TextView) v.findViewById(R.id.functionTextView);
        //email.setText(pref.getEmail());
     /*  System.out.println(users);
        if(users!=null) {
            System.out.println(users.size());
            User u = users.get(/*indexOf(pref.getEmail(),users)0);
            name.setText(u.getPersonalData().getName() + " " + u.getPersonalData().getSurname());
           System.out.println(u);
           function.setText(u.getFunction());
        }*/
        //name.setText(pref.getEmail());
        function.setText(pref.getType());
        Button servicesButton = (Button) v.findViewById(R.id.buttonServices);
        Button rentCarButton = (Button) v.findViewById(R.id.buttonRentCar);
        Button logOutButton = (Button) v.findViewById(R.id.buttonLogOut);
        
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(intent);
            }
        });
        servicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServicesFragment fragmentS = new ServicesFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame_account, fragmentS);
                fragmentTransaction.commit();
            }
        });

        rentCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VehiclesFragment fragmentR = new VehiclesFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame_account, fragmentR);
                fragmentTransaction.commit();
            }
        });
        return v;
    }

    private int indexOf(String email, ArrayList<User> users) {

        int index = 0;
        for (User user : users) {
            if (user.getEmail().equals(email)) {

                break;
            } else {
                index++;
            }
        }
        return index;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
