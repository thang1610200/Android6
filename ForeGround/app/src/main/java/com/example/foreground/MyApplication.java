package com.example.foreground;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MyApplication extends Application {
    public static final String CHANEL_ID = "chanel_service";
    @Override
    public void onCreate(){
        super.onCreate();
        createChanelNotification();
    }

    private void createChanelNotification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANEL_ID,"Chanel Service Example", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            channel.setSound(null,null);
            if(manager != null){
                manager.createNotificationChannel(channel);
            }
        }
    }
}
