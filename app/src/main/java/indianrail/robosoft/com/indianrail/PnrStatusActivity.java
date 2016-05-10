package indianrail.robosoft.com.indianrail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import indianrail.robosoft.com.indianrail.adapter.PnrStatusRecyclerviewAdapter;
import indianrail.robosoft.com.indianrail.constants.AppData;
import indianrail.robosoft.com.indianrail.model.pnrstatus.pnrstatus.PassengersItem;
import indianrail.robosoft.com.indianrail.model.pnrstatus.pnrstatus.PnrStatus;
import indianrail.robosoft.com.indianrail.network.GsonRequest;
import indianrail.robosoft.com.indianrail.network.VolleyHelper;


public class PnrStatusActivity extends AppCompatActivity {
    private EditText mEditText;
    private TextView mtxtTrainName, mtxtFrom, mtxtTo, mtxtJournyDate, mtxtChartStatus;
    private RecyclerView mRecyclerView;
    private FrameLayout mFrameLayout;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pnr_status);
        setSupportActionBar((Toolbar) findViewById(R.id.actionbar));
        getSupportActionBar().setTitle("Check PNR Status");
        mEditText = (EditText) findViewById(R.id.edtPnrNo);
        mtxtChartStatus = (TextView) findViewById(R.id.txtChartStatus);
        mtxtJournyDate = (TextView) findViewById(R.id.txtdateOfJourny);
        mtxtTo = (TextView) findViewById(R.id.txtToStation);
        mtxtFrom = (TextView) findViewById(R.id.txtFromStation);
        mtxtTrainName = (TextView) findViewById(R.id.txtTrainName);
        mButton = (Button) findViewById(R.id.btnok);
        mFrameLayout = (FrameLayout) findViewById(R.id.PnrsStatusrow2);

        mRecyclerView = (RecyclerView) findViewById(R.id.pnrStatusRecyclerView);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = AppData.INDIANRAILAPI + "/pnr_status/pnr/" + mEditText.getText().toString() + "/apikey/jxqbq3625/";
                GsonRequest gsonRequest = new GsonRequest(url, PnrStatus.class, new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {

                        PnrStatus pnrstatus = (PnrStatus) response;
                        ArrayList<PassengersItem> passengersItems = (ArrayList<PassengersItem>) pnrstatus.passengers;
                        if (pnrstatus.train_name != null) {
                            mFrameLayout.setVisibility(View.VISIBLE);
                            mtxtTrainName.setText(pnrstatus.train_name);
                            mtxtFrom.setText(pnrstatus.fromStation.name);
                            mtxtTo.setText(pnrstatus.reservationUpto.name);
                            mtxtJournyDate.setText(pnrstatus.doj);
                            mtxtChartStatus.setText(pnrstatus.chart_prepared);
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(PnrStatusActivity.this));
                            mRecyclerView.setAdapter(new PnrStatusRecyclerviewAdapter(passengersItems));
                        } else {
                            Log.i("test", "Error");
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PnrStatusActivity.this, "Oops Something Wrong..!!!", Toast.LENGTH_SHORT).show();
                    }
                });
                VolleyHelper.getInstance(PnrStatusActivity.this).addToRequestQueue(gsonRequest);
            }
        });


    }


}
