/**
 * A MainActivity é a tela principal do aplicativo.
 *
 * Esta atividade demonstra o registro e o cancelamento do registro de um {@link MyReceiver}
 * para ouvir as ações de ligar e desligar a tela ({@link Intent#ACTION_SCREEN_ON} and
 * {@link Intent#ACTION_SCREEN_OFF}).
 *
 * O receptor é registrado no método {@link #onStart()} e o registro é cancelado no
 * método {@link #onDestroy()} para evitar vazamentos de memória.
 */
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

    /**
     * Chamado quando a atividade está se tornando visível para o usuário.
     *
     * Neste método, registramos o {@link MyReceiver} para receber intents com as ações
     * {@link Intent#ACTION_SCREEN_OFF} e {@link Intent#ACTION_SCREEN_ON}.
     * Um sinalizador {@code receiverRegistrado} é usado para garantir que o receptor seja
     * registrado apenas uma vez.
     */
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

    /**
     * Chamado antes de a atividade ser destruída.
     *
     * Neste método, cancelamos o registro do {@link MyReceiver} para evitar vazamentos de
     * memória se o receptor foi registrado.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiverRegistrado) {
            Log.i(TAG, "Removendo o receiver");
            unregisterReceiver(receiver);
            receiverRegistrado = false;
        }
    }

    /**
     * Chamado quando a atividade é criada pela primeira vez.
     *
     * Este método é responsável por configurar a interface do usuário da atividade.
     *
     * @param savedInstanceState Se a atividade estiver sendo reiniciada após ter sido
     *                           desligada anteriormente, este Pacote contém os dados que ela
     *                           forneceu mais recentemente em {@link #onSaveInstanceState}.
     *                           <b><i>Observação: Caso contrário, ele será nulo.</i></b>
     */
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
