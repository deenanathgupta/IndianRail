package indianrail.robosoft.com.indianrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

import indianrail.robosoft.com.indianrail.adapter.SearchTrainRecyclerViewAdapter;
import indianrail.robosoft.com.indianrail.model.pnrstatus.trainstatus.TrainItem;
import indianrail.robosoft.com.indianrail.model.pnrstatus.trainstatus.Trainstatus;
import indianrail.robosoft.com.indianrail.network.GsonRequest;
import indianrail.robosoft.com.indianrail.network.VolleyHelper;

public class SearchResultTrain extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Trainstatus mTrainstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_train);
        mRecyclerView = (RecyclerView) findViewById(R.id.searchTrainRecyclerView);
        Intent intent = getIntent();
        String url = intent.getStringExtra("searchTrainUrl");
        Log.i("test", "onCreate: "+url);
        GsonRequest gsonRequest = new GsonRequest(url, Trainstatus.class, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                mTrainstatus = (Trainstatus) response;
                Log.i("test", "Total " + mTrainstatus.total);
                List<TrainItem> trainItems = mTrainstatus.train;
                mRecyclerView.setLayoutManager(new LinearLayoutManager(SearchResultTrain.this));
                mRecyclerView.setAdapter(new SearchTrainRecyclerViewAdapter(trainItems));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("test", "Error..");
            }
        });
        VolleyHelper.getInstance(this).addToRequestQueue(gsonRequest);
    }
}
