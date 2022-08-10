package com.example.mockproject.service;


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
import androidx.fragment.app.Fragment;

import com.example.mockproject.R;
import com.example.mockproject.view.main.fragmentelement.song.element.allsong.AllSongsFragment;
import com.example.mockproject.view.main.fragmentelement.song.element.allsong.Song;

public class Services extends Service {
    private static final String TAG = "Services";
    private AllSongsFragment allSongsFragment;
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
        Log.d(TAG, "onStartCommand: "+ AllSongsFragment.songList );
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

        android.app.Notification notification =
                new NotificationCompat.Builder(this, Notification.NOTIFICATION_CHANNEL_ID)
                        .setContentTitle(song.getSongs())
                        .setContentText(song.getSinger())
                        .setSmallIcon(R.drawable.ic_flag)
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
            mediaPlayer = MediaPlayer.create(allSongsFragment.requireContext(), Uri.parse(song.getResourceMusic()));
        } else {
            mediaPlayer.release();
            mediaPlayer = null;
            mediaPlayer = MediaPlayer.create(allSongsFragment.requireContext(), Uri.parse(song.getResourceMusic()));
        }
        mediaPlayer.setOnCompletionListener(mp -> next());
        mediaPlayer.start();
        createNotification(song, R.drawable.ic_baseline_pause_circle_outline_24_theme, 1);

        if (allSongsFragment != null) {
            allSongsFragment.onRunMediaPlayer.runMedia(true);
        }
    }
    public void previous() {
        int songListSize = AllSongsFragment.songList.size();
        if (AllSongsFragment.currentSong == 0) {
            AllSongsFragment.currentSong = songListSize - 1;
        } else {
            AllSongsFragment.currentSong--;
        }
        Song song = AllSongsFragment.songList.get(AllSongsFragment.currentSong);
        updateSong(song);
    }
    public void play_pause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            Song song = AllSongsFragment.songList.get(AllSongsFragment.currentSong);
            createNotification(song, R.drawable.ic_baseline_play_circle_outline_24_theme, 0);
        } else {
            mediaPlayer.start();
            Song song = AllSongsFragment.songList.get(AllSongsFragment.currentSong);
            createNotification(song, R.drawable.ic_baseline_pause_circle_outline_24_theme, 1);
        }
        if (allSongsFragment != null) {
            allSongsFragment.onRunMediaPlayer.runMedia(true);
        }
    }

    public void next() {
        int songListSize = AllSongsFragment.songList.size();
        if (AllSongsFragment.currentSong == songListSize - 1) {
            AllSongsFragment.currentSong = 0;
        } else {
            AllSongsFragment.currentSong++;
        }
        Song song = AllSongsFragment.songList.get(AllSongsFragment.currentSong);
        updateSong(song);
    }

    public void clear() {
        stopSelf();
        mediaPlayer.release();
        mediaPlayer = null;
        if (allSongsFragment != null) {
            allSongsFragment.onClearMediaPlayer.onClearMediaPlayer(true);
        }
    }
    public void disconnect() {
        allSongsFragment = null;
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
    public MediaPlayer getMediaPlayer(){
        return mediaPlayer;
    }
    public void connectToUI(Fragment fragment) {
        this.allSongsFragment = (AllSongsFragment) fragment;
    }
}
