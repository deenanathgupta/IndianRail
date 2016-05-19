package indianrail.robosoft.com.indianrail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnPnrStatus, btnSearchTrain,mBtnTrainByNoOrName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();



    }

    private void initUi(){
        setSupportActionBar((Toolbar) findViewById(R.id.actionbar));
        btnPnrStatus = (Button) findViewById(R.id.btnPnrStatus);
        btnPnrStatus.setOnClickListener(this);
        btnSearchTrain = (Button) findViewById(R.id.btnSearchTrain);
        btnSearchTrain.setOnClickListener(this);
        mBtnTrainByNoOrName = (Button) findViewById(R.id.btnTrainByNo);
        mBtnTrainByNoOrName.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPnrStatus:
                startActivity(new Intent(this, PnrStatusActivity.class));
                break;
            case R.id.btnSearchTrain:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                break;
            case R.id.btnTrainByNo:
                 startActivity(new Intent(this,TrainByNoActivity.class));
                 break;
        }
    }

}
