package com.example.mockproject.service;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import com.example.mockproject.R;
import com.example.mockproject.view.main.MainActivity;
import com.example.mockproject.view.main.MainActivityViewModel;
import com.example.mockproject.view.main.fragmentelement.song.element.allsong.Song;
import com.example.mockproject.view.main.fragmentelement.song.element.allsong.SongViewModel;

import java.util.List;

public class Services extends Service {
    private static final String TAG = "Services";
    //    private AllSongsFragment allSongsFragment;
    public android.app.Notification notification;
    private MainActivity mainActivity;
    private MediaPlayer mediaPlayer;
    private MediaSessionCompat mMediaSessionCompat;
    private final MusicBinder musicBinder = new MusicBinder();
    public static boolean serviceRunning = false;

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        mMediaSessionCompat = new MediaSessionCompat(this, "Chill and free");
        serviceRunning = true;
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: " + MainActivity.songList);
        if (intent != null && intent.getAction() != null) {
            handleAction(intent.getAction());
        }
        return START_REDELIVER_INTENT;
    }

    private void createNotification(Song song, int playPauseBtn, float playbackSpeed) {
        Intent prevIntent = new Intent(this, Receiver.class).setAction(Notification.PREVIOUS);
        PendingIntent prevPendingIntent = PendingIntent.getBroadcast(this, 0, prevIntent, PendingIntent.FLAG_IMMUTABLE);

        Intent playIntent = new Intent(this, Receiver.class).setAction(Notification.PLAY_PAUSE);
        PendingIntent playPendingIntent = PendingIntent.getBroadcast(this, 0, playIntent, PendingIntent.FLAG_IMMUTABLE);

        Intent nextIntent = new Intent(this, Receiver.class).setAction(Notification.NEXT);
        PendingIntent nextPendingIntent = PendingIntent.getBroadcast(this, 0, nextIntent, PendingIntent.FLAG_IMMUTABLE);

        Intent clearIntent = new Intent(this, Receiver.class).setAction(Notification.CLEAR);
        PendingIntent clearPendingIntent = PendingIntent.getBroadcast(this, 0, clearIntent, PendingIntent.FLAG_IMMUTABLE);

        notification =
                new NotificationCompat.Builder(this, Notification.NOTIFICATION_CHANNEL_ID)
                        .setContentTitle(song.getSongs())
                        .setContentText(song.getSinger())
                        .setSmallIcon(R.drawable.ic_flag)
                        .setLargeIcon(song.getImg())
                        .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                                .setMediaSession(mMediaSessionCompat.getSessionToken())
                        )
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                        .setOnlyAlertOnce(true)
                        .addAction(R.drawable.ic_previous, "Previous", prevPendingIntent)
                        .addAction(playPauseBtn, "PauseOrPlay", playPendingIntent)
                        .addAction(R.drawable.ic_next, "Next", nextPendingIntent)
                        .addAction(R.drawable.ic_clear, "Clear", clearPendingIntent)
                        .build();
        startForeground(1, notification);
    }

    private void handleAction(String action) {
        Log.d(TAG, "handleAction: ");
        switch (action) {
            case Notification.PREVIOUS:
                previous();
                break;
            case Notification.PLAY_PAUSE:
                play_pause();
                break;
            case Notification.NEXT:
                next();
                break;
            case Notification.CLEAR:
                clear();
                break;
        }
    }

    public void updateSong(Song song) {
        Log.d(TAG, "updateSong: ");
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(mainActivity, Uri.parse(song.getResourceMusic()));
        } else {
            mediaPlayer.release();
            mediaPlayer = null;
            mediaPlayer = MediaPlayer.create(mainActivity, Uri.parse(song.getResourceMusic()));
        }
        mediaPlayer.setOnCompletionListener(mp -> next());
        mediaPlayer.start();
        createNotification(song, R.drawable.ic_baseline_pause_circle_outline_24_theme, 1);

        if (mainActivity != null) {
            mainActivity.onRunMediaPlayer.runMedia(true);
        }
    }

    public void previous() {
        int songListSize = MainActivity.songList.size();
        if (MainActivity.currentSong == 0) {
            MainActivity.currentSong = songListSize - 1;
        } else {
            MainActivity.currentSong--;
        }
        Song song = MainActivity.songList.get(MainActivity.currentSong);
        updateSong(song);
    }

    public void play_pause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            Song song = MainActivity.songList.get(MainActivity.currentSong);
            createNotification(song, R.drawable.ic_baseline_play_circle_outline_24_theme, 0);
        } else {
            mediaPlayer.start();
            Song song = MainActivity.songList.get(MainActivity.currentSong);
            createNotification(song, R.drawable.ic_baseline_pause_circle_outline_24_theme, 1);
        }
        if (mainActivity != null) {
            mainActivity.onRunMediaPlayer.runMedia(true);
        }
    }

    public void next() {
        int songListSize = MainActivity.songList.size();
        if (MainActivity.currentSong == songListSize - 1) {
            MainActivity.currentSong = 0;
        } else {
            MainActivity.currentSong++;
        }
        Song song = MainActivity.songList.get(MainActivity.currentSong);
        updateSong(song);
    }

    public void clear() {
        stopSelf();
        mediaPlayer.release();
        mediaPlayer = null;
        if (mainActivity != null) {
            mainActivity.onClearMediaPlayer.onClearMediaPlayer(true);
        }
    }

    public void disconnect() {
        mainActivity = null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicBinder;
    }

    public class MusicBinder extends Binder {
        public Services getMusicService() {
            return Services.this;
        }

    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void connectToUI(Activity activity) {
        this.mainActivity = (MainActivity) activity;
    }
}
