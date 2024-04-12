package br.com.rafaelamorim.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Objects;


public class MyReceiver extends BroadcastReceiver {
    String TAG = "BroadcastReceiver.MyReceiver";
    public MyReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (Objects.equals(intent.getAction(), Intent.ACTION_SCREEN_OFF)) {
            Log.i(TAG, "Screen OFF");
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Log.i(TAG, "Screen  ON");
        }
    }
}

