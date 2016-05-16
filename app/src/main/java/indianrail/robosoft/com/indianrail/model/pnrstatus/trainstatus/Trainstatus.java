package indianrail.robosoft.com.indianrail.model.pnrstatus.trainstatus;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Author: deena
 * Created by: ModelGenerator on 12/5/16
 */
public class Trainstatus {
    public int total;
    @SerializedName("train")
    public List<TrainItem> train;
}