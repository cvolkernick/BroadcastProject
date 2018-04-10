package com.example.cvolk.broadcastproject.receivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class MyResultReceiver extends ResultReceiver {

    private ResultReceiverCallBack mResultReceiver;

    public MyResultReceiver(Handler handler) {
        super(handler);
    }

    public void setReceiver(ResultReceiverCallBack receiver) {
        mResultReceiver = receiver;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mResultReceiver != null) {
            if (resultCode == Activity.RESULT_OK) {
                mResultReceiver.onSuccess(resultCode, resultData);
            }
        }
    }

    public interface ResultReceiverCallBack {
        public void onSuccess(int resultCode, Bundle resultData);
    }
}