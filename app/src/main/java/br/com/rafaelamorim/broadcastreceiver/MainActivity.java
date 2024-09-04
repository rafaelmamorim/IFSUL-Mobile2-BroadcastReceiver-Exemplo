package br.com.rafaelamorim.broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "BroadcastReceiver.MainActivity";
    private MyReceiver receiver;
    private boolean receiverRegistrado = false;

    @Override
    protected void onStart() {
        super.onStart();

        if (!receiverRegistrado) {
            receiver = new MyReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            filter.addAction(Intent.ACTION_SCREEN_ON);
            registerReceiver(receiver, filter);
            Log.i(TAG, "Registrando o receiver");
            receiverRegistrado = true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiverRegistrado) {
            Log.i(TAG, "Removendo o receiver");
            unregisterReceiver(receiver);
            receiverRegistrado = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.aboutTextView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}