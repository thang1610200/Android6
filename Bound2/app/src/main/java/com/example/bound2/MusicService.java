package com.example.bound2;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import org.jetbrains.annotations.NotNull;

public class MusicService extends Service {
    public MusicService() {
    }

    public static final int MSG_PLAY_MUSIC = 1;
    private MediaPlayer mediaPlayer;
    private Messenger messenger;

    public class MyHandler extends Handler{
        private Context applicationContext;

        public MyHandler(@NotNull Context context){
            this.applicationContext = context.getApplicationContext();
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case MSG_PLAY_MUSIC:
                    StartMusic();
                    break;

                default:
                    super.handleMessage(msg);
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Message","onCreated");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        messenger = new Messenger(new MyHandler(this));
        return messenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("Message","onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Message","onDestroyed");
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void StartMusic(){
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.music);
        }
        mediaPlayer.start();
    }
}