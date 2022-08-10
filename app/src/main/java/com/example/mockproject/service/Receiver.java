package com.example.mockproject.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Receiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, Services.class);
        switch (intent.getAction()) {
            case Notification.PREVIOUS: {
                serviceIntent.setAction(Notification.PREVIOUS);
                context.startService(serviceIntent);
                break;
            }
            case Notification.PLAY_PAUSE: {
                serviceIntent.setAction(Notification.PLAY_PAUSE);
                context.startService(serviceIntent);
                break;
            }
            case Notification.NEXT: {
                serviceIntent.setAction(Notification.NEXT);
                context.startService(serviceIntent);
                break;
            }
            case Notification.CLEAR: {
                serviceIntent.setAction(Notification.CLEAR);
                context.startService(serviceIntent);
                break;
            }
        }
    }
}
