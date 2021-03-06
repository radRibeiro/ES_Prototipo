package uberplus.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fct.unl.pt.uberplus_p.R;
import uberplus.activities.AccountActivity;
import uberplus.entitiesDB.FoodDelivery;
import uberplus.entitiesDB.ServiceRequest;
import uberplus.entitiesDB.Transportation;
import uberplus.utils.ServiceListAdapter;

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

    ListView listView;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
        private static ServiceListAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ServiceFragmentInteraction mListener;
    private ArrayList<ServiceRequest> requestsList;

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
        ((AccountActivity)getActivity()).getSupportActionBar().setTitle("Services list");

        requestsList = ((AccountActivity) getActivity()).getRequestList();
        listView = (ListView) v.findViewById(R.id.servicesListView);
        adapter = new ServiceListAdapter(requestsList, getActivity().getApplicationContext());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_service_data, null);

                final TextView status = (TextView) dialogView.findViewById(R.id.statusDialogText);
                final TextView destinationAddress = (TextView) dialogView.findViewById(R.id.endingDestinationDialogText);
                final TextView foodName = (TextView) dialogView.findViewById(R.id.foodNameDialogText);
                final TextView foodQuantity = (TextView) dialogView.findViewById(R.id.foodQuantityDialogText);
                final TextView originAddress = (TextView) dialogView.findViewById(R.id.startingDestinationDialogText);
                final ServiceRequest request = requestsList.get(i);

                status.setText(request.getStatus().toString());
                destinationAddress.setText(request.getDestinationAddress());

                if (request instanceof FoodDelivery) {
                    View foodNameLayout = dialogView.findViewById(R.id.foodNameLayout);
                    View foodQuantityLayout = dialogView.findViewById(R.id.foodQuantityLayout);
                    foodNameLayout.setVisibility(view.VISIBLE);
                    foodQuantityLayout.setVisibility(view.VISIBLE);
                    String name = ((FoodDelivery) request).getFoodName();
                    foodName.setText(name);
                    int quantity = ((FoodDelivery) request).getQuantity();
                    foodQuantity.setText(String.valueOf(quantity));
                } else {
                    View privateLayout = dialogView.findViewById(R.id.privateLayout);
                    View originAddressLayout = dialogView.findViewById(R.id.originAddressLayout);
                    originAddressLayout.setVisibility(view.VISIBLE);
                    originAddress.setText(request.getOriginAddress());
                    if (((Transportation) request).isPrivate())
                        privateLayout.setVisibility(view.VISIBLE);
                }

                builder.setView(dialogView);
                builder.create();
                final AlertDialog alert = builder.show();

                dialogView.findViewById(R.id.buttonPay).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        request.setStatus(ServiceRequest.RequestStatus.PAID);
                        if (alert != null && alert.isShowing()) {
                            alert.dismiss();
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

                dialogView.findViewById(R.id.buttonAccept).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        request.setStatus(ServiceRequest.RequestStatus.ACCEPTED);
                        if (alert != null && alert.isShowing()) {
                            alert.dismiss();
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

        FloatingActionButton requestServiceButton = (FloatingActionButton) v.findViewById(R.id.createRequestButton);
        requestServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestServiceFragment fragmentS = new RequestServiceFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame_account, fragmentS);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

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
