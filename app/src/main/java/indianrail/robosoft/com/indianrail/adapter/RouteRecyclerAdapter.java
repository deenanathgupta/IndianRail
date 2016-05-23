package indianrail.robosoft.com.indianrail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import indianrail.robosoft.com.indianrail.ModuleSchedule.Route;
import indianrail.robosoft.com.indianrail.R;

/**
 * Created by archana on 20/5/16.
 */
public class RouteRecyclerAdapter extends RecyclerView.Adapter<RouteRecyclerAdapter.RouteLayoutHolder> {

   private Context mContext;
   private List<Route> mRouteList;

    public RouteRecyclerAdapter(Context mContext, List<Route> mRouteList) {
        this.mContext = mContext;
        this.mRouteList = mRouteList;
    }

    @Override
    public RouteLayoutHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mOneRow = LayoutInflater.from(mContext).inflate(R.layout.routerow,parent,false);
        RouteLayoutHolder routeLayoutHolder = new RouteLayoutHolder(mOneRow);
        return routeLayoutHolder;
    }

    @Override
    public void onBindViewHolder(RouteLayoutHolder holder, int position) {
        Route route = mRouteList.get(position);
        holder.mTextNo.setText(route.no);
        holder.mTextStnName.setText(route.fullname+"\n"+route.code);
        holder.mTextDep.setText(route.schdep);
        holder.mTextArr.setText(route.scharr);
        holder.mTextDay.setText(route.day);
        holder.mTextDist.setText(route.distance);

    }

    @Override
    public int getItemCount() {
        return mRouteList.size();
    }


    class RouteLayoutHolder extends RecyclerView.ViewHolder{
       private TextView mTextNo,mTextStnName,mTextArr,mTextDep,mTextDay,mTextDist;
       public RouteLayoutHolder(View itemView) {
           super(itemView);
           mTextNo = (TextView) itemView.findViewById(R.id.no);
           mTextStnName = (TextView) itemView.findViewById(R.id.stnName);
           mTextArr = (TextView)itemView.findViewById(R.id.arr);
           mTextDep = (TextView) itemView.findViewById(R.id.dep);
           mTextDay = (TextView) itemView.findViewById(R.id.day);
           mTextDist = (TextView) itemView.findViewById(R.id.distance);
       }
   }

}
