package indianrail.robosoft.com.indianrail.asyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

import indianrail.robosoft.com.indianrail.communicator.ResonceCommunicator;
import indianrail.robosoft.com.indianrail.model.PnrStatus;
import indianrail.robosoft.com.indianrail.util.Util;

/**
 * Created by deena on 6/5/16.
 */
public class PnrResonseAsyncTask extends AsyncTask<String, Void, String> {
    private Context mContext;
    private ResonceCommunicator mResonceCommunicator;
    private ArrayList<PnrStatus> mPnrStatusArrayList;

    public PnrResonseAsyncTask(Context mContext) {
        this.mContext = mContext;
        mResonceCommunicator = (ResonceCommunicator) mContext;
    }

    @Override
    protected String doInBackground(String... params) {
        String response = Util.httpUrlConnection(params[0]);
        try {
            JSONObject jsonObject = (JSONObject) new JSONTokener(response).nextValue();
            String trainName = jsonObject.getString("train_name");
            String from_station = jsonObject.getJSONObject("from_station").getString("name");
            String upto_station = jsonObject.getJSONObject("reservation_upto").getString("name");
            Log.i("test", trainName + " " + from_station + " " + upto_station);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String respose) {
        super.onPostExecute(respose);
    }
}
