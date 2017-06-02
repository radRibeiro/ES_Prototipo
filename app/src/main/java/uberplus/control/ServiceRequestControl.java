package uberplus.control;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import uberplus.entitiesDB.ServiceRequest;

public class ServiceRequestControl {
    SparseArray<ServiceRequest> map;
    private int serviceID;

    public ServiceRequestControl(){
        map = new SparseArray<ServiceRequest>();
        serviceID = 0;
    }

    public void createRequest(ServiceRequest request){
        map.put(serviceID++, request);
    }

    public ServiceRequest getRequest(int serviceID){
            return map.get(serviceID);
    }

    public List<ServiceRequest> getAllRequests(){
        List<ServiceRequest> list = new ArrayList<ServiceRequest>(map.size());
        for(int i = 0; i < map.size(); i++) {
            int key = map.keyAt(i);
            list.add(map.get(key));
        }
        return list;
    }

}
