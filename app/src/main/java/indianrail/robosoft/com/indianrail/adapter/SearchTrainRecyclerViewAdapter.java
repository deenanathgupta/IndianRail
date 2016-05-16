package indianrail.robosoft.com.indianrail.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import indianrail.robosoft.com.indianrail.R;
import indianrail.robosoft.com.indianrail.model.pnrstatus.trainstatus.DaysItem;
import indianrail.robosoft.com.indianrail.model.pnrstatus.trainstatus.TrainItem;

/**
 * Created by deena on 12/5/16.
 */
public class SearchTrainRecyclerViewAdapter extends RecyclerView.Adapter<SearchTrainRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "test";
    private List<TrainItem> trainItemList;

    public SearchTrainRecyclerViewAdapter(List<TrainItem> trainItemList) {
        this.trainItemList = trainItemList;
    }

    @Override
    public SearchTrainRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_search_train, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchTrainRecyclerViewAdapter.ViewHolder holder, int position) {
        TrainItem trainItem = trainItemList.get(position);
        holder.txtTrainNo.setText(String.valueOf(trainItem.number));
        holder.txtTrainName.setText(trainItem.name);
        List<DaysItem> daysItems = trainItem.days;
        setDaysColorText(holder, daysItems);
        holder.txtArrival.setText(trainItem.srcDepartureTime);
        holder.txtDeparture.setText(trainItem.destArrivalTime);
        holder.txtSource.setText(trainItem.from.name);
        holder.txtDestionation.setText(trainItem.to.name);
        holder.txtTotailTime.setText(String.valueOf(trainItem.travelTime));
    }

    private void setDaysColorText(ViewHolder holder, List<DaysItem> daysItems) {
        for (DaysItem daysItem : daysItems) {
            switch (daysItem.dayCode) {
                case "MON":
                    if (daysItem.runs.equals("Y"))
                        holder.txtMon.setTextColor(Color.GREEN);
                    else
                        holder.txtMon.setTextColor(Color.RED);
                    break;
                case "TUE":
                    if (daysItem.runs.equals("Y"))
                        holder.txtTue.setTextColor(Color.GREEN);
                    else
                        holder.txtTue.setTextColor(Color.RED);
                    break;
                case "WED":
                    if (daysItem.runs.equals("Y"))
                        holder.txtWed.setTextColor(Color.GREEN);
                    else
                        holder.txtWed.setTextColor(Color.RED);
                    break;
                case "THU":
                    if (daysItem.runs.equals("Y"))
                        holder.txtThu.setTextColor(Color.GREEN);
                    else
                        holder.txtThu.setTextColor(Color.RED);
                    break;
                case "FRI":
                    if (daysItem.runs.equals("Y"))
                        holder.txtFri.setTextColor(Color.GREEN);
                    else
                        holder.txtFri.setTextColor(Color.RED);
                    break;
                case "SAT":
                    if (daysItem.runs.equals("Y")) {
                        holder.txtSat.setTextColor(Color.GREEN);
                    } else
                        holder.txtSat.setTextColor(Color.RED);
                    break;
                case "SUN":
                    if (daysItem.runs.equals("Y"))
                        holder.txtSun.setTextColor(Color.GREEN);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return trainItemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTrainName, txtTrainNo, txtSun, txtMon, txtTue, txtWed, txtThu, txtFri, txtSat, txtSource, txtDestionation, txtArrival, txtDeparture, txtTotailTime;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTrainName = (TextView) itemView.findViewById(R.id.txtTrainName);
            txtTrainNo = (TextView) itemView.findViewById(R.id.txtTrainNumber);
            txtSource = (TextView) itemView.findViewById(R.id.txtSource);
            txtDestionation = (TextView) itemView.findViewById(R.id.txtDestination);
            txtSun = (TextView) itemView.findViewById(R.id.txtSunday);
            txtMon = (TextView) itemView.findViewById(R.id.txtMonday);
            txtTue = (TextView) itemView.findViewById(R.id.txtTuesday);
            txtWed = (TextView) itemView.findViewById(R.id.txtWednesday);
            txtThu = (TextView) itemView.findViewById(R.id.txtThursday);
            txtFri = (TextView) itemView.findViewById(R.id.txtFriday);
            txtSat = (TextView) itemView.findViewById(R.id.txtSaturday);
            txtArrival = (TextView) itemView.findViewById(R.id.txtArrivalTime);
            txtDeparture = (TextView) itemView.findViewById(R.id.txtDepartureTime);
            txtTotailTime = (TextView) itemView.findViewById(R.id.txttraveltime);

        }
    }
}
