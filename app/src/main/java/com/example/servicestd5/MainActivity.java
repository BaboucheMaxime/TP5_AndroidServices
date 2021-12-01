package com.example.servicestd5;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    int compteur = 0;

    Handler objHandler =  new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle objBundle = msg.getData();
            int myMessage = objBundle.getInt("MSG_KEY");

            compteur = myMessage;
            /*TextView textview = findViewById(R.id.tv_one);
            textview.setText(String.valueOf(myMessage));*/
        }
    };


    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void lancerCompteur(View v) {

        if (compteur >= 1){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Le service est déja démarré",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            TextView textview = findViewById(R.id.tv_one);
            textview.setText("Le compteur tourne");
            for (int a = 1; a <= 1000; a++) {

                int finalA = a;
                objHandler.postDelayed(new Runnable() {
                    Message objMessage = objHandler.obtainMessage();
                    Bundle objBundle = new Bundle();

                    @Override
                    public void run() {
                        objBundle.putInt("MSG_KEY", finalA);
                        objMessage.setData(objBundle);
                        objHandler.sendMessage(objMessage);
                    }
                }, 1000 * a);
            }
        }
    }

    public void afficherCompteur(View v) {

        TextView textview = findViewById(R.id.tv_one);
        textview.setText(String.valueOf(compteur));

    }

    public void stopCompteur(View v) {
        objHandler.removeCallbacksAndMessages(null);
        if (compteur == 0) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Le service n'est pas démarré",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        compteur = 0;
    }
}