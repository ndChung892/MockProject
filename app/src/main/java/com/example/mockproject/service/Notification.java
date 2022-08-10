package com.example.mockproject.service;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Notification extends Application {
    public static final String NOTIFICATION_CHANNEL_ID = "Music Channel";
    public static final String PREVIOUS = "PREV";
    public static final String PLAY_PAUSE = "PLAY_PAUSE";
    public static final String NEXT = "NEXT";
    public static final String CLEAR = "CLEAR";
    public static final String SWIPE = "SWIPE";
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "Notification Service",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setSound(null, null);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

        }
    }
}
