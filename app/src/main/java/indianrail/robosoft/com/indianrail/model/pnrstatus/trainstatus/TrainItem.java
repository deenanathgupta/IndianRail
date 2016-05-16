package indianrail.robosoft.com.indianrail.model.pnrstatus.trainstatus;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Author: deena
 * Created by: ModelGenerator on 12/5/16
 */
public class TrainItem {
    @SerializedName("src_departure_time")
    public String srcDepartureTime;
    @SerializedName("to")
    public To to;
    @SerializedName("classes")
    public List<ClassesItem> classes;
    public int number;
    @SerializedName("days")
    public List<DaysItem> days;
    @SerializedName("dest_arrival_time")
    public String destArrivalTime;
    @SerializedName("travel_time")
    public String travelTime;
    @SerializedName("name")
    public String name;
    public int no;
    public From from;
}