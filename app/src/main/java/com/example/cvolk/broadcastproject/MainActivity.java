package com.example.cvolk.broadcastproject;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cvolk.broadcastproject.receivers.CustomReceiver;
import com.example.cvolk.broadcastproject.receivers.MyResultReceiver;
import com.example.cvolk.broadcastproject.services.MyIntentService;

public class MainActivity extends AppCompatActivity {

    private EditText etMain;
    private CustomReceiver customReceiver;
    private MyResultReceiver myResultReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMain = findViewById(R.id.etMain);

        myResultReceiver = new MyResultReceiver(new Handler());

        // specify what happens when data is received from service
        myResultReceiver.setReceiver(new MyResultReceiver.ResultReceiverCallBack() {
            @Override
            public void onSuccess(int resultCode, Bundle resultData) {
                if (resultCode == RESULT_OK) {
                    String resultValue = resultData.getString("resultValue");
                    Toast.makeText(MainActivity.this, resultValue, Toast.LENGTH_SHORT).show();
                }
                else {
                    // do something else...logging?
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        customReceiver = new CustomReceiver();

        IntentFilter intentFilter = new IntentFilter();

        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        intentFilter.addAction(Intent.ACTION_HEADSET_PLUG);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        intentFilter.addAction("myAction");

        registerReceiver(customReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(customReceiver);
    }

    public void sendBroadcast(View view) {
        Intent intent = new Intent();
        intent.setAction("myAction");
        intent.putExtra("data", etMain.getText().toString());
        sendOrderedBroadcast(intent, null);
    }

    public void onServiceResultReceiver(View v) {
        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("data", etMain.getText().toString());
        intent.putExtra("receiver", myResultReceiver);
        startService(intent);
    }
}
