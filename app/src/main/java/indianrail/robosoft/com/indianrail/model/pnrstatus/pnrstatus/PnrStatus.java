package indianrail.robosoft.com.indianrail.model.pnrstatus.pnrstatus;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by deena on 9/5/16.
 */
public class PnrStatus {
    public String train_name;
    @SerializedName("from_station")
    public FromStation fromStation;
    @SerializedName("reservation_upto")
    public ReservationUpto reservationUpto;
    public String chart_prepared;
    public String doj;
    @SerializedName("passengers")
    public List<PassengersItem> passengers;
}
