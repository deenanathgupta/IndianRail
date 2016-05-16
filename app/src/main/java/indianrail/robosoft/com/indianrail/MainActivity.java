package indianrail.robosoft.com.indianrail;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import indianrail.robosoft.com.indianrail.constants.AppData;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnPnrStatus, btnSearchTrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolBarLayout = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(toolBarLayout);

        btnPnrStatus = (Button) findViewById(R.id.btnPnrStatus);
        btnPnrStatus.setOnClickListener(this);
        btnSearchTrain = (Button) findViewById(R.id.btnSearchTrain);
        btnSearchTrain.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPnrStatus:
                startActivity(new Intent(this, PnrStatusActivity.class));
                break;
            case R.id.btnSearchTrain:
                startActivity(new Intent(this, TrainStatusActivity.class));
                break;
        }
    }
}
