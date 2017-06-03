package uberplus.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fct.unl.pt.uberplus_p.R;
import uberplus.entitiesDB.ServiceRequest;

public class ServiceListAdapter extends ArrayAdapter<ServiceRequest> implements View.OnClickListener {

    private ArrayList<ServiceRequest> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView idView;
        TextView serviceTypeView;
        TextView timestampView;
        TextView status;
    }

    public ServiceListAdapter(ArrayList<ServiceRequest> data, Context context) {
        super(context, R.layout.services_list_item, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {

        int position = (Integer) v.getTag();
        Object object = getItem(position);
        ServiceRequest dataModel = (ServiceRequest) object;

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
        ServiceRequest request = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.services_list_item, parent, false);
            viewHolder.idView = (TextView) convertView.findViewById(R.id.serviceID);
            viewHolder.serviceTypeView = (TextView) convertView.findViewById(R.id.serviceType);
            viewHolder.timestampView = (TextView) convertView.findViewById(R.id.timestamp);
            viewHolder.status = (TextView) convertView.findViewById(R.id.serviceStatus);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

//        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        result.startAnimation(animation);
//        lastPosition = position;

        viewHolder.idView.setText(String.valueOf(request.getServiceID()));
        viewHolder.serviceTypeView.setText(request.getServiceType());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM - HH:mm");
        String start = dateFormat.format(request.getStartTime());
        viewHolder.timestampView.setText(start);
        viewHolder.status.setText(request.getStatus().toString());

        ServiceRequest.RequestStatus status = request.getStatus();
        if(status == ServiceRequest.RequestStatus.NOT_ACCEPTED){
            viewHolder.status.setTextColor(Color.RED);
        }
        else if(status == ServiceRequest.RequestStatus.ACCEPTED){
            viewHolder.status.setTextColor(Color.GREEN);
        }
        else viewHolder.status.setTextColor(Color.BLUE);

        //viewHolder.info.setOnClickListener(this);
        //viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}