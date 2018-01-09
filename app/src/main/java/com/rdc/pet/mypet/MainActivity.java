package com.rdc.pet.mypet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rdc.pet.mypet.chat.controller.ChatActivity;
import com.rdc.pet.mypet.service.PetViewService;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button chatButton;
    //private PetBroadcast petBroadcast;

    public class PetBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            update();
        }
    }

    @Override
    protected void onStart() {
        /*petBroadcast = new PetBroadcast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("UPDATE_ACTION");
        registerReceiver(petBroadcast, intentFilter);*/
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(MainActivity.this, PetViewService.class));
        super.onDestroy();
    }

    private void init() {
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PetViewService.class);
                startService(intent);
                finish();
            }
        });

        chatButton = (Button) findViewById(R.id.chat_button);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void update() {
        //Toast.makeText(this, "update", Toast.LENGTH_LONG).show();
    }


}
