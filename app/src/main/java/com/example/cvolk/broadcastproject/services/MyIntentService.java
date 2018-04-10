package com.example.cvolk.broadcastproject.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;


public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            // get the receiver that was passed to this service
            ResultReceiver receiver = intent.getParcelableExtra("receiver");

            // get extras from intent
            String value = intent.getStringExtra("data");

            // create a new bundle to pass results back to the activity
            Bundle bundle = new Bundle();
            bundle.putString("resultValue", "Result: " + value);

            // call the reciever's send passing the result code depending
            // on succeed/failure (we can use that in the activity to
            // react accordingly)
            receiver.send(Activity.RESULT_OK, bundle);
        }
    }
}
