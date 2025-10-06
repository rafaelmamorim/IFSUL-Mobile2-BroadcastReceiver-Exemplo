package br.com.rafaelamorim.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Objects;


/**
 * MyReceiver é um BroadcastReceiver que escuta por ações de tela ligada e desligada.
 * Ele registra uma mensagem de log quando a tela é desligada ou ligada.
 */
public class MyReceiver extends BroadcastReceiver {
    String TAG = "BroadcastReceiver.MyReceiver";
    public MyReceiver() {
        super();
    }

    /**
     * Este método é chamado quando o BroadcastReceiver está recebendo um Intent broadcast.
     * @param context O Contexto no qual o receptor está sendo executado.
     * @param intent O Intent que está sendo recebido.
     */
    @Override
    public void onReceive(Context context, Intent intent) {

        if (Objects.equals(intent.getAction(), Intent.ACTION_SCREEN_OFF)) {
            Log.i(TAG, "Screen OFF");
        } else if (Objects.equals(intent.getAction(), Intent.ACTION_SCREEN_ON)) {
            Log.i(TAG, "Screen  ON");
        }
    }
}
