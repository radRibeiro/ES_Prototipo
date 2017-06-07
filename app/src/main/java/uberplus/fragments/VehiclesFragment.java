package uberplus.fragments;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class VehiclesFragment extends Fragment {
    private static ListView listView;
    private static RentedVehicleListAdapter adapter;
    private Preferences pref;
    private OnFragmentInteractionListener mListener;

    public VehiclesFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static VehiclesFragment newInstance() {
        VehiclesFragment fragment = new VehiclesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_vehicles, container, false);
        ((AccountActivity)getActivity()).getSupportActionBar().setTitle("Rental vehicles list");

        listView = (ListView) v.findViewById(R.id.carsList);
        pref = new Preferences(getActivity());
        final ArrayList<RentedVehicle> vehiclesList = pref.getRentedCarsCollection();

        if (pref.getRentedCarsCollection() != null) {
            adapter = new RentedVehicleListAdapter(vehiclesList, getActivity().getApplicationContext());
            listView.setAdapter(adapter);
        }

        FloatingActionButton addCar = (FloatingActionButton) v.findViewById(R.id.registerVehicleButton);
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

                        reloadAllData();

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

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void reloadAllData() {
        ArrayList<RentedVehicle> vehiclesList = pref.getRentedCarsCollection();
        adapter.clear();
        adapter.addAll(vehiclesList);
        adapter.notifyDataSetChanged();
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
