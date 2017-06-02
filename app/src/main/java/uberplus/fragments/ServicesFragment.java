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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fct.unl.pt.uberplus_p.R;
import uberplus.activities.AccountActivity;
import uberplus.entitiesDB.FoodDelivery;
import uberplus.entitiesDB.RentedVehicle;
import uberplus.entitiesDB.ServiceRequest;
import uberplus.entitiesDB.Transportation;
import uberplus.entitiesDB.Vehicle;
import uberplus.utils.Preferences;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ServiceFragmentInteraction} interface
 * to handle interaction events.
 * Use the {@link ServicesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServicesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    ListView listView ;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ServiceFragmentInteraction mListener;
    private List<ServiceRequest> requestsList;

    public ServicesFragment() {
        // Required empty public constructor
    }

    /**
     * Fragmento para os costumers apenas, pode-se solicitar requests e pagar requests
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServicesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServicesFragment newInstance(String param1, String param2) {
        ServicesFragment fragment = new ServicesFragment();
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
        View v = inflater.inflate(R.layout.fragment_services, container, false);
        listView = (ListView) v.findViewById(R.id.servicesListView);
        final Preferences pref = new Preferences(getActivity());
        final List<ServiceRequest> servicesList = pref.getServicesCollection();
        Button requestServiceButton = (Button) v.findViewById(R.id.createRequestButton);


        requestServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pref.getType().equals("Driver"))
                {
                    Toast.makeText(getActivity(),"You are Driver",Toast.LENGTH_SHORT).show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    View dialogView = getActivity().getLayoutInflater().inflate(R.layout.fragment_request, null);
                    builder.setView(dialogView);
                    // final Button submitRequestButton = (Button) dialogView.findViewById(R.id.buttonSubmitServiceRequest);
                    final RadioButton foodRadio = (RadioButton) dialogView.findViewById(R.id.foodDeliveryRadio);
                    final RadioButton transportRadio = (RadioButton) dialogView.findViewById(R.id.transportDeliveryRadio);
                    final View foodLay = (View) dialogView.findViewById(R.id.foodDeliveryLay);
                    final View transportLay = (View) dialogView.findViewById(R.id.transportLay);

                    final EditText foodDestination = (EditText) dialogView.findViewById(R.id.editTextFoodDelivery);
                    final EditText foodName = (EditText) dialogView.findViewById(R.id.editTextFoodName);
                    final EditText foodQuantity = (EditText) dialogView.findViewById(R.id.editTextFoodQuantity);

                    final EditText transportStart = (EditText) dialogView.findViewById(R.id.editTextTransportationStart);
                    final EditText transportDestination = (EditText) dialogView.findViewById(R.id.editTextTransportationDestination);
                    final RadioButton privateRadio = (RadioButton) dialogView.findViewById(R.id.privateRadio);
                    foodRadio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            transportRadio.setChecked(false);
                            foodLay.setVisibility(view.VISIBLE);
                            transportLay.setVisibility(view.GONE);
                            privateRadio.setVisibility(view.GONE);
                        }
                    });
                    transportRadio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            foodRadio.setChecked(false);
                            transportLay.setVisibility(view.VISIBLE);
                            foodLay.setVisibility(view.GONE);
                            privateRadio.setVisibility(view.VISIBLE);
                        }
                    });
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
                            ArrayList<ServiceRequest> services = pref.getServicesCollection();
                            if (transportRadio.isChecked()) {
                                Transportation request = new Transportation(transportStart.getText().toString(),
                                        transportDestination.getText().toString(),
                                        privateRadio.isChecked());
                                System.out.println(request);
                                if (services == null) {
                                    services = new ArrayList<>();
                                }
                                services.add(request);
                                pref.setServicesCollection(services);
                            } else if (foodRadio.isChecked()) {
                                FoodDelivery request = new FoodDelivery(foodDestination.getText().toString(),
                                        foodName.getText().toString(),
                                        Integer.parseInt(foodQuantity.getText().toString()));
                                if (services == null) {
                                    services = new ArrayList<>();

                                }
                                services.add(request);
                                pref.setServicesCollection(services);
                            }

                            listView.setAdapter(new ArrayAdapter<>(getActivity(),
                                    android.R.layout.simple_list_item_1, services));

                        }
                    });
                    builder.create();
                    builder.show();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_service_actions, null);
                TextView vehicleDT = (TextView) dialogView.findViewById(R.id.textView5);
                ArrayList<ServiceRequest> services = pref.getServicesCollection();
                ServiceRequest sr = services.get(i);
                System.out.println(sr instanceof FoodDelivery);
                    vehicleDT.setText(
                            "TRAJECTORY : " + sr.getOriginAndDestAddress() + "\n" +
                            "STATUS : " + sr.getStatus() + "\n");
                builder.setView(dialogView);
                builder.create();
                builder.show();
            }
        });
        if (pref.getServicesCollection() != null) {
            listView.setAdapter(new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_list_item_1, servicesList));
        }
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            //mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ServiceFragmentInteraction) {
            mListener = (ServiceFragmentInteraction) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ServiceFragmentInteraction");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface ServiceFragmentInteraction {
        List<ServiceRequest> getRequestList();
    }
}
