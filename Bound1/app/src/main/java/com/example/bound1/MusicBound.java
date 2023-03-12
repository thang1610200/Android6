package com.example.bound1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MusicBound extends Service {
    public MusicBound() {
    }

    private MyBinder mBinder = new MyBinder();
    private MediaPlayer mediaPlayer;

    public class MyBinder extends Binder{
        MusicBound getMusicBoundService(){
            return MusicBound.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Music Bound Service","onCreated");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.e("Music Bound Service","onBlind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("Music Bound Service","onUnBlind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Music Bound Service","onDestroyed");
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void StartMusic(){
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.music);
        }
        mediaPlayer.start();
    }
}