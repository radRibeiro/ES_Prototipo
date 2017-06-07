package uberplus.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fct.unl.pt.uberplus_p.R;
import uberplus.entitiesDB.RentedVehicle;
import uberplus.entitiesDB.Vehicle;

/**
 * Created by Duarte on 03/06/2017.
 */

public class RentedVehicleListAdapter extends ArrayAdapter<RentedVehicle> implements View.OnClickListener{
    private ArrayList<RentedVehicle> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView licensePlateView;
        TextView registrationDateView;
        TextView monthlyFeeView;
        TextView rentalDurationView;
    }

    public RentedVehicleListAdapter(ArrayList<RentedVehicle> data, Context context) {
        super(context, R.layout.vehicles_list_item, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {

        int position = (Integer) v.getTag();
        Object object = getItem(position);
        Vehicle dataModel = (Vehicle) object;

        switch (v.getId()) {
//            case R.id.item_info:
//                Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
//                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        RentedVehicle vehicle = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        RentedVehicleListAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new RentedVehicleListAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.vehicles_list_item, parent, false);
            viewHolder.licensePlateView = (TextView) convertView.findViewById(R.id.licensePlate);
            viewHolder.registrationDateView = (TextView) convertView.findViewById(R.id.registrationDate);
            viewHolder.monthlyFeeView = (TextView) convertView.findViewById(R.id.monthlyFee);
            viewHolder.rentalDurationView = (TextView) convertView.findViewById(R.id.rentalDuration);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (RentedVehicleListAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }

//        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        result.startAnimation(animation);
//        lastPosition = position;

        viewHolder.licensePlateView.setText(vehicle.getLicensePlate());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM - HH:mm");
        String registerDate = dateFormat.format(vehicle.getRegistrationDate());
        viewHolder.registrationDateView.setText(registerDate);

        String feeValue = String.valueOf(vehicle.getMonthlyFee());
        String monthlyFee = String.format("%s€/month", feeValue);
        viewHolder.monthlyFeeView.setText(monthlyFee);

        String rentalDuration = null;
        int duration = vehicle.getRentalDuration();
        String durationString = String.valueOf(duration);
        if(duration == 1)
            rentalDuration = String.format("%s day", durationString);
        else
            rentalDuration = String.format("%s days", durationString);

        viewHolder.rentalDurationView.setText(rentalDuration);

        //viewHolder.info.setOnClickListener(this);
        //viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }

}
