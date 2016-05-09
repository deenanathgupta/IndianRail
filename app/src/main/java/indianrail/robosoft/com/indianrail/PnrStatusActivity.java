package indianrail.robosoft.com.indianrail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import indianrail.robosoft.com.indianrail.asyncTask.PnrResonseAsyncTask;
import indianrail.robosoft.com.indianrail.communicator.ResonceCommunicator;


public class PnrStatusActivity extends AppCompatActivity implements ResonceCommunicator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pnr_status);
        setSupportActionBar((Toolbar) findViewById(R.id.actionbar));
        getSupportActionBar().setTitle("Check PNR Status");
        String url = "http://api.railwayapi.com/pnr_status/pnr/4418570587/apikey/jxqbq3625/";
        new PnrResonseAsyncTask(this).execute(url);
    }

    @Override
    public void responseData(String response) {

    }
}
