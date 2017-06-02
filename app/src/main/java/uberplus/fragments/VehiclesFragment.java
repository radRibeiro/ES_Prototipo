package uberplus.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fct.unl.pt.uberplus_p.R;
import uberplus.entitiesDB.RentedVehicle;
import uberplus.entitiesDB.Vehicle;
import uberplus.utils.Preferences;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VehiclesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VehiclesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VehiclesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static ListView listView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public VehiclesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VehiclesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VehiclesFragment newInstance(String param1, String param2) {
        VehiclesFragment fragment = new VehiclesFragment();
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
        View v = inflater.inflate(R.layout.fragment_vehicles, container, false);
        listView = (ListView)v.findViewById(R.id.carsList);
        final Preferences pref = new Preferences(getActivity());
        final List<Vehicle> vehiclesList = pref.getCarsCollection();
        Button addCar = (Button)v.findViewById(R.id.button3);
        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_register_vehicle,null);
                builder.setView(dialogView);

                final    EditText licensePlateT = (EditText)dialogView.findViewById(R.id.editTextLicensePlate);
                final    EditText brandT = (EditText)dialogView.findViewById(R.id.editTextBrand);
                final    EditText year = (EditText)dialogView.findViewById(R.id.editTextYear);
                final    EditText category = (EditText)dialogView.findViewById(R.id.editTextCategory);
                final    EditText model = (EditText)dialogView.findViewById(R.id.editTextModel);
                final    EditText price = (EditText)dialogView.findViewById(R.id.editTextPrice);
                final    EditText monthlyFee = (EditText)dialogView.findViewById(R.id.editTextMFee);
                final    EditText days = (EditText)dialogView.findViewById(R.id.editTextDuration);

                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //String licensePlate, float price,
                       // String category, String model, String brand, int year,int rentalDuration,float monthlyFee
                        ArrayList<Vehicle> vehicles = pref.getCarsCollection();
                        Vehicle v = new RentedVehicle(licensePlateT.getText().toString(),
                                                        Float.parseFloat(price.getText().toString()),
                                                        category.getText().toString(),
                                                        model.getText().toString(),
                                                        brandT.getText().toString(),
                                                        Integer.parseInt(year.getText().toString()),
                                                        Integer.parseInt(days.getText().toString()),
                                                        Float.parseFloat(monthlyFee.getText().toString()));
                        if(pref.getCarsCollection()==null){
                            vehicles =   new ArrayList<>();
                            vehicles.add(v);
                            pref.setCarsCollection(vehicles);
                        }
                        else{
                        vehicles.add(v);
                        pref.setCarsCollection(vehicles);
                        }
                        listView.setAdapter(new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_list_item_1, vehicles));

                    }
                });
                builder.create();
                builder.show();
            }
        });
        if(pref.getCarsCollection()!=null){
        listView.setAdapter(new ArrayAdapter<Vehicle>(getActivity(),
                android.R.layout.simple_list_item_1, vehiclesList));}
        return v;
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
