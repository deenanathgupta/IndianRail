package indianrail.robosoft.com.indianrail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import indianrail.robosoft.com.indianrail.adapter.SearchTrainRecyclerViewAdapter;
import indianrail.robosoft.com.indianrail.constants.AppData;
import indianrail.robosoft.com.indianrail.fragment.DatePickerFragment;
import indianrail.robosoft.com.indianrail.model.pnrstatus.trainstatus.TrainItem;
import indianrail.robosoft.com.indianrail.model.pnrstatus.trainstatus.Trainstatus;
import indianrail.robosoft.com.indianrail.network.GsonRequest;
import indianrail.robosoft.com.indianrail.network.VolleyHelper;

public class TrainStatusActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener {
    private static final String TAG = "test";
    private AutoCompleteTextView mFromStation;
    private AutoCompleteTextView mToStation;
    private EditText mDateEditTextView;
    private ImageView mDatePicker;
    private String[] mStationCode;
    private List<String> mStaionCodeArrayList = null;
    private List<String> mStationCodeList;
    private StationCodeArrayAdapter adapter;
    private Toolbar mToolBar;
    private Button mTrainSearchButton;
    private List<Trainstatus> mTrainStatusArrayList;
    private Trainstatus mTrainstatus;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_status);
        initializationOfView();
    }

    private void initializationOfView() {
        mTrainStatusArrayList = new ArrayList<>();
        mDateEditTextView = (EditText) findViewById(R.id.edtDate);
        mStationCode = getResources().getStringArray(R.array.station_code);
        mStaionCodeArrayList = Arrays.asList(mStationCode);
        mStationCodeList = new ArrayList<>();
        mStationCodeList.addAll(mStaionCodeArrayList);
        mFromStation = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        mToStation = (AutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView1);
        mDatePicker = (ImageView) findViewById(R.id.calDatePicker);
        mToolBar = (Toolbar) findViewById(R.id.actionbar);
        mTrainSearchButton = (Button) findViewById(R.id.trainSearchButton);
        mRecyclerView = (RecyclerView) findViewById(R.id.searchTrainRecyclerView);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Search Train");
        mDatePicker.setOnClickListener(this);
        mTrainSearchButton.setOnClickListener(this);

        adapter = new StationCodeArrayAdapter();
        mFromStation.addTextChangedListener(this);
        mToStation.addTextChangedListener(this);
        mFromStation.setAdapter(adapter);
        mFromStation.setThreshold(2);
        mToStation.setAdapter(adapter);
        mToStation.setThreshold(2);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        adapter.getFilter().filter(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calDatePicker:
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
                break;
            case R.id.trainSearchButton:
                if (validateRequest()) {
                    if (!mFromStation.getText().toString().equals(mToStation.getText().toString())) {
                        Intent intent = new Intent(this, SearchResultTrain.class);
                        intent.putExtra("searchTrainUrl", getUrlForTrainBetweenStation());
                        Log.i(TAG, "onClick: "+getUrlForTrainBetweenStation());
                        startActivity(intent);
                    } else {
                        Toast.makeText(TrainStatusActivity.this, "Change Destination..!!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(TrainStatusActivity.this, "Plz Enter valid Station..!!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private boolean validateRequest() {
        if ((mFromStation.getText().toString().length() != 0) && (mToStation.getText().toString().length() != 0)) {
            return true;
        }
        return false;
    }


    private String getUrlForTrainBetweenStation() {
        String[] date = mDateEditTextView.getText().toString().split("/");
        String from[] = mFromStation.getText().toString().split(" ");
        String to[] = mToStation.getText().toString().split(" ");
        int month = Integer.parseInt(date[1]) + 1;
        if (Integer.parseInt(date[0]) < 10) {
            date[0] = "0" + date[0];
        }
        if (month < 10) {
            date[1] = "0" + month;
        }
        String url = AppData.INDIANRAILAPI + "/between/source/" + from[from.length - 1] + "/dest/" + to[to.length - 1] + "/date/" + date[0] + "-" + date[1] + "/apikey/" + AppData.APIKEY + "/";
        return url;
    }

    class StationCodeArrayAdapter extends BaseAdapter implements Filterable {
        LayoutInflater inflater;
        MYfilter mYfilter = null;

        public StationCodeArrayAdapter() {
            inflater = LayoutInflater.from(TrainStatusActivity.this);
        }

        @Override
        public int getCount() {
            return mStaionCodeArrayList == null ? 0 : mStaionCodeArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return mStaionCodeArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.staion_name_suggestion, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            String stationName = mStaionCodeArrayList.get(position);
            viewHolder.txtStationName.setText(stationName);
            return convertView;
        }

        @Override
        public Filter getFilter() {
            if (mYfilter == null) {
                mYfilter = new MYfilter();
            }
            return mYfilter;
        }

        private class ViewHolder {
            TextView txtStationName;

            public ViewHolder(View view) {
                txtStationName = (TextView) view.findViewById(R.id.txtStationCode);
            }
        }
    }

    class MYfilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null) {
                results.values = mStationCodeList;
                results.count = mStationCodeList.size();
            } else {
                ArrayList<String> stationCodeList = new ArrayList<>();
                for (String s : mStationCodeList) {
                    if (s.toLowerCase().contains(constraint.toString().toLowerCase())) {
                        stationCodeList.add(s);
                    }
                }
                results.values = stationCodeList;
                results.count = stationCodeList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mStaionCodeArrayList = (List<String>) results.values;
            adapter.notifyDataSetChanged();
        }
    }

}
