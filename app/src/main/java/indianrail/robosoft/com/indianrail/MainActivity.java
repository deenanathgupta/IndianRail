package indianrail.robosoft.com.indianrail;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolBarLayout = (Toolbar) findViewById(R.id.appBar);
        findViewById(R.id.btnPnrStatus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PnrStatusActivity.class));
            }
        });
        if (toolBarLayout != null)
            setSupportActionBar(toolBarLayout);
        String Url = "http://api.railwayapi.com/route/train/12295/apikey/jxqbq3625/";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("test", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("test", error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }
}
