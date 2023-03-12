package com.example.bound1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MusicBound musicBound;
    private Boolean isServiceConnect;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicBound.MyBinder myBinder = (MusicBound.MyBinder) iBinder;
            musicBound = myBinder.getMusicBoundService();
            musicBound.StartMusic();
            isServiceConnect = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isServiceConnect = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStartService = findViewById(R.id.btn_start_service);
        Button btnStopService = findViewById(R.id.btn_stop_service);

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickStartService();
            }
        });

        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickStopService();
            }
        });
    }

    public void onClickStartService(){
        Intent intent = new Intent(this,MusicBound.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void onClickStopService(){
        if(isServiceConnect){
            unbindService(serviceConnection);
            isServiceConnect = false;
        }
    }
}