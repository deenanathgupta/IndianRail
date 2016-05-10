package indianrail.robosoft.com.indianrail.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import indianrail.robosoft.com.indianrail.R;
import indianrail.robosoft.com.indianrail.model.pnrstatus.pnrstatus.PassengersItem;

/**
 * Created by deena on 10/5/16.
 */
public class PnrStatusRecyclerviewAdapter extends RecyclerView.Adapter<PnrStatusRecyclerviewAdapter.ViewHolder> {
    private ArrayList<PassengersItem> passengersItems;
    private ViewHolder mViewHolder;

    public PnrStatusRecyclerviewAdapter(ArrayList<PassengersItem> passengersItems) {
        this.passengersItems = passengersItems;
    }

    @Override
    public PnrStatusRecyclerviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pnr_status_single_row, parent, false));
    }

    @Override
    public void onBindViewHolder(PnrStatusRecyclerviewAdapter.ViewHolder holder, int position) {
        PassengersItem passengersItem = passengersItems.get(position);
        holder.txtSno.setText(passengersItem.no + "");
        holder.txtBookingstatus.setText(passengersItem.bookingStatus);
        holder.txtCurrentStatus.setText(passengersItem.currentStatus);
        holder.txtCoachPosition.setText(passengersItem.coachPosition+"");
    }

    @Override
    public int getItemCount() {
        return passengersItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtSno, txtBookingstatus, txtCurrentStatus, txtCoachPosition;

        public ViewHolder(View itemView) {
            super(itemView);
            txtSno = (TextView) itemView.findViewById(R.id.txtsno);
            txtBookingstatus = (TextView) itemView.findViewById(R.id.txtBookingStatus);
            txtCurrentStatus = (TextView) itemView.findViewById(R.id.txtcurrentstatus);
            txtCoachPosition = (TextView) itemView.findViewById(R.id.txtcoachPosition);
        }
    }
}
