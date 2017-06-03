package uberplus.fragments;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

import fct.unl.pt.uberplus_p.R;
import uberplus.activities.AccountActivity;
import uberplus.entitiesDB.RentedVehicle;
import uberplus.entitiesDB.Vehicle;
import uberplus.utils.Preferences;
import uberplus.utils.RentedVehicleListAdapter;

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
    private static RentedVehicleListAdapter adapter;
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
        listView = (ListView) v.findViewById(R.id.carsList);
        final Preferences pref = new Preferences(getActivity());
        final ArrayList<RentedVehicle> vehiclesList = pref.getRentedCarsCollection();

        Button addCar = (Button) v.findViewById(R.id.button3);
        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_register_vehicle, null);
                builder.setView(dialogView);

                final EditText licensePlateT = (EditText) dialogView.findViewById(R.id.editTextLicensePlate);
                final EditText brandT = (EditText) dialogView.findViewById(R.id.editTextBrand);
                final EditText year = (EditText) dialogView.findViewById(R.id.editTextYear);
                final EditText category = (EditText) dialogView.findViewById(R.id.editTextCategory);
                final EditText model = (EditText) dialogView.findViewById(R.id.editTextModel);
                final EditText price = (EditText) dialogView.findViewById(R.id.editTextPrice);
                final EditText monthlyFee = (EditText) dialogView.findViewById(R.id.editTextMFee);
                final EditText days = (EditText) dialogView.findViewById(R.id.editTextDuration);

                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //String licensePlate, float price,
                        // String category, String model, String brand, int year,int rentalDuration,float monthlyFee
                        ArrayList<RentedVehicle> vehicles = pref.getRentedCarsCollection();
                        boolean hasLicensePlate = false;
                        if (vehicles != null) {

                            Iterator<RentedVehicle> it = vehicles.iterator();

                            while (it.hasNext()) {
                                if (it.next().getLicensePlate().equals(licensePlateT.getText().toString())) {
                                    hasLicensePlate = true;
                                }
                            }
                        }
                        RentedVehicle v = new RentedVehicle(licensePlateT.getText().toString(),
                                Float.parseFloat(price.getText().toString()),
                                category.getText().toString(),
                                model.getText().toString(),
                                brandT.getText().toString(),
                                Integer.parseInt(year.getText().toString()),
                                Integer.parseInt(days.getText().toString()),
                                Float.parseFloat(monthlyFee.getText().toString()));

                        if (pref.getRentedCarsCollection() == null && !hasLicensePlate) {
                            vehicles = new ArrayList<>();
                            vehicles.add(v);
                            pref.setCarsCollection(vehicles);
                        } else if (pref.getRentedCarsCollection() != null && !hasLicensePlate) {
                            vehicles.add(v);
                            pref.setCarsCollection(vehicles);
                        } else if (hasLicensePlate) {
                            Toast.makeText(getActivity(), "License plate being used", Toast.LENGTH_SHORT).show();
                        }
//                        listView.setAdapter(new ArrayAdapter<>(getActivity(),
//                                android.R.layout.simple_list_item_1, vehicles));
                        //adapter = new RentedVehicleListAdapter(vehiclesList, ((AccountActivity) getActivity()).getApplicationContext());
                        //listView.setAdapter(adapter);

                    }
                });
                builder.create();
                builder.show();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_vehicle_data, null);
                TextView vehicleDT = (TextView) dialogView.findViewById(R.id.textViewVehicleData);
                ArrayList<RentedVehicle> vehicles = pref.getRentedCarsCollection();
                Vehicle v = vehicles.get(i);

                vehicleDT.setText("BRAND : " + v.getBrand() + "\n" +
                        "CATEGORY : " + v.getCategory() + "\n" +
                        "MODEL : " + v.getModel() + "\n" +
                        "YEAR : " + v.getYear() + "\n" +
                        "LICENSE PLATE : " + v.getLicensePlate() + "\n" +
                        "PRICE : " + v.getPrice() + " €");

                builder.setView(dialogView);
                builder.create();
                builder.show();
            }
        });
        if (pref.getRentedCarsCollection() != null) {
            adapter = new RentedVehicleListAdapter(vehiclesList, ((AccountActivity) getActivity()).getApplicationContext());
            listView.setAdapter(adapter);
        }
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
