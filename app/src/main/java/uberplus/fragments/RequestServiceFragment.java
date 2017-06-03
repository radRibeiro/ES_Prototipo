package uberplus.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

import fct.unl.pt.uberplus_p.R;
import uberplus.activities.AccountActivity;
import uberplus.entitiesDB.FoodDelivery;
import uberplus.entitiesDB.ServiceRequest;
import uberplus.entitiesDB.Transportation;


/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link RequestServiceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RequestServiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RequestServiceFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;
    // The URL to +1.  Must be a valid URL.
    private final String PLUS_ONE_URL = "http://developer.android.com";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    public RequestServiceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RequestServiceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RequestServiceFragment newInstance(String param1, String param2) {
        RequestServiceFragment fragment = new RequestServiceFragment();
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
        View v = inflater.inflate(R.layout.fragment_request, container, false);

        final Button submitRequestButton = (Button) v.findViewById(R.id.buttonSubmitServiceRequest);
        final RadioButton foodRadio = (RadioButton)v.findViewById(R.id.foodDeliveryRadio);
        final RadioButton transportRadio = (RadioButton)v.findViewById(R.id.transportDeliveryRadio);
        final View foodLay = (View) v.findViewById(R.id.foodDeliveryLay);
        final View transportLay = (View) v.findViewById(R.id.transportLay);

        final EditText foodDestination = (EditText) v.findViewById(R.id.editTextFoodDelivery);
        final EditText foodName = (EditText) v.findViewById(R.id.editTextFoodName);
        final EditText foodQuantity = (EditText) v.findViewById(R.id.editTextFoodQuantity);

        final EditText transportStart = (EditText) v.findViewById(R.id.editTextTransportationStart);
        final EditText transportDestination = (EditText) v.findViewById(R.id.editTextTransportationDestination);
        final Switch privateSwitch = (Switch) v.findViewById(R.id.privateSwitch);

        transportRadio.setChecked(true);

        foodRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodLay.setVisibility(view.VISIBLE);
                transportLay.setVisibility(view.GONE);
            }
        });
        transportRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transportLay.setVisibility(view.VISIBLE);
                foodLay.setVisibility(view.GONE);
            }
        });

        submitRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(foodRadio.isChecked()){
                    FoodDelivery delivery = new FoodDelivery(foodDestination.getText().toString(),foodName.getText().toString(), Integer.parseInt(foodQuantity.getText().toString()));
                    ((AccountActivity)getActivity()).createRequest(delivery);
                }
                else{
                    Transportation transport = new Transportation(transportStart.getText().toString(),transportDestination.getText().toString(), privateSwitch.isChecked());
                    ((AccountActivity)getActivity()).createRequest(transport);
                }
                ServicesFragment fragmentS = new ServicesFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame_account, fragmentS);
                fragmentTransaction.commit();
            }
        });


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Refresh the state of the +1 button each time the activity receives focus.

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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
    public interface OnFragmentInteractionListener {
        void createRequest(ServiceRequest request);
    }


}
