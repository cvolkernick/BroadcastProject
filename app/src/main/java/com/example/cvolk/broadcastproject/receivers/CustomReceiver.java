package com.example.cvolk.broadcastproject.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    public static final String TAG = "CustomReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        String message;
        switch (intent.getAction()) {
            case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                boolean status = intent.getBooleanExtra("state", false);
                message = "Airplane mode :" + status;
                Log.d(TAG, "onReceive: " + message);
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                break;
            case Intent.ACTION_TIME_TICK:
                message = "The current time has changed";
                Log.d(TAG, "onReceive: " + message);
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                break;
            case Intent.ACTION_HEADSET_PLUG:
                int state = intent.getIntExtra("state",0);
                message = state==1? "Wired Headset plugged in" : "Wired Headset unplugged" ;
                Log.d(TAG, "onReceive: " + message);
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                break;
            case Intent.ACTION_POWER_CONNECTED:
                message = "Power connected to device";
                Log.d(TAG, "onReceive: " + message);
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                message = "Power disconnected from device";
                Log.d(TAG, "onReceive: " + message);
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
