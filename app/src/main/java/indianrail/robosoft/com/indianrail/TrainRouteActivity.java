package indianrail.robosoft.com.indianrail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import indianrail.robosoft.com.indianrail.ModuleSchedule.DaysItem;
import indianrail.robosoft.com.indianrail.ModuleSchedule.ScheduleWrapper;
import indianrail.robosoft.com.indianrail.ModuleSchedule.Train;
import indianrail.robosoft.com.indianrail.adapter.DaysSpinnerAdapter;

public class TrainRouteActivity extends AppCompatActivity {

    private TextView mTxtTrainNo,mTxtTrainName,mTxtTrainSourceStation,mTxtDaysSpinner;
    private Spinner mDaysSpinner;
    private Intent mIntent ;
    private ScheduleWrapper mScheduleWrapper;
    private List<DaysItem> mDaysItemList = new ArrayList<>();
    private Train mTrainObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_route);
        initUi();
        mIntent = getIntent();
        if(mIntent!=null){
            mDaysItemList = (List<DaysItem>) mIntent.getSerializableExtra("DaysList");
            mTrainObj = (Train) mIntent.getSerializableExtra("Train");
        }
        setValueToUi();
    }

    private void initUi(){
        setSupportActionBar((Toolbar) findViewById(R.id.actionbar));
        getSupportActionBar().setTitle("Train Schedule");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTxtTrainNo = (TextView) findViewById(R.id.txtTrainNoVal);
        mTxtTrainName = (TextView) findViewById(R.id.txtTrainNameVal);
        mTxtTrainSourceStation = (TextView) findViewById(R.id.txtSourceStationVal);
        mDaysSpinner = (Spinner) findViewById(R.id.daysSpinner);
    }
    private void setValueToUi(){
       mTxtTrainNo.setText(mTrainObj.name);
       mTxtTrainName.setText(mTrainObj.number);
        //TODO FOR SOURCE STATION
       // mTxtTrainSourceStation.setText(mScheduleWrapper.);
        mDaysSpinner.setAdapter(new DaysSpinnerAdapter(this,R.layout.spinner_row, mDaysItemList));

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
