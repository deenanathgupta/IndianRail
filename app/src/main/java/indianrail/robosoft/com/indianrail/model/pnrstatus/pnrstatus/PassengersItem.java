package indianrail.robosoft.com.indianrail.model.pnrstatus.pnrstatus;

import com.google.gson.annotations.SerializedName;

/**
 * Author: deena
 * Created by: ModelGenerator on 10/5/16
 */
public class PassengersItem {
    @SerializedName("current_status")
    public String currentStatus;    // CNF
    @SerializedName("no")
    public int no;    // 1
    @SerializedName("booking_status")
    public String bookingStatus;    // S7,10,GN
    @SerializedName("coach_position")
    public int coachPosition;    // 0
}