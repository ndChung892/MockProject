package com.example.mockproject.view.main.fragmentelement.song.element.allsong;

import static android.content.Context.BIND_AUTO_CREATE;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mockproject.R;
import com.example.mockproject.service.RunMedia;
import com.example.mockproject.databinding.FragmentAllSongsBinding;
import com.example.mockproject.service.ClearMediaPlayerListener;
//import com.example.mockproject.service.RunMedia;
import com.example.mockproject.service.Services;
import com.example.mockproject.view.main.MainActivity;

import java.util.List;


public class AllSongsFragment extends Fragment {
    private static final String TAG = "AllSongsFragment";
    FragmentAllSongsBinding mBinding;

    private Services services;
    private Handler handler;
    private boolean inService = false;
    public static int currentSong = -1;
    SongViewModel songViewModel = new ViewModelProvider(ViewModelStore::new).get(SongViewModel.class);
    public static List<Song> songList ;

//        private MediaPlayer mediaPlayer;
    private SongsAdapter songsAdapter;

    private final ServiceConnection mNewConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            services = ((Services.MusicBinder) service).getMusicService();
            services.connectToUI(AllSongsFragment.this);
            inService = true;
            if (services.getMediaPlayer() != null) {
                MediaPlayerUI();
            } else {
                Song song = songList.get(currentSong);
                services.updateSong(song);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            services = null;
            handler = null;
        }
    };
    public final ClearMediaPlayerListener onClearMediaPlayer = isSuccess -> {
        if (isSuccess) {
            if (songsAdapter.getPreviousSong() != -1) {
                songList.get(songsAdapter.getPreviousSong()).setPlaying(false);
            }
            songList.get(currentSong).setPlaying(false);
//            songsAdapter.List(songList, currentSong);
//            mBinding.control.setVisibility(View.GONE);

            requireContext().unbindService(mNewConnection);
            inService = false;
            currentSong = -1;
            handler = null;
        }
    };
    public final SongOnClickListener songOnClickListener = position -> {
        if (inService) {
            Log.d(TAG, "inService: ");
            currentSong = position;
            Song song = songList.get(currentSong);
            services.updateSong(song);
        } else {
            Intent intent = new Intent(requireContext(), Services.class);
            requireContext().startService(intent);
            requireContext().bindService(intent, mNewConnection, BIND_AUTO_CREATE);
            currentSong = position;
            Log.d(TAG, "onClick: " + currentSong);
        }
    };
    public final RunMedia onRunMediaPlayer = isSuccess -> {
        if (isSuccess) {
            if (songsAdapter.getCurrentSong() != -1) {
                songList.get(songsAdapter.getCurrentSong()).setPlaying(false);
            }
            MediaPlayerUI();
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentAllSongsBinding.inflate(getLayoutInflater(), container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.rcvAllSong.setLayoutManager(layoutManager);


        mBinding.playPause.setOnClickListener(v -> services.play_pause());
        mBinding.previous.setOnClickListener(v -> services.previous());
        mBinding.next.setOnClickListener(v -> services.next());
        songList = songViewModel.getSongList(requireActivity());
        songsAdapter = new SongsAdapter(Song.diffCallback, songOnClickListener);

        Log.d(TAG, "onViewCreated: "+ songsAdapter);
        mBinding.rcvAllSong.setAdapter(songsAdapter);
        songsAdapter.submitList(songList);
        restoreMusic();
    }

    private void restoreMusic() {
        if (Services.serviceRunning) {
            Intent intent = new Intent(requireContext(), Services.class);
            requireContext().startService(intent);
            requireContext().bindService(intent, mNewConnection, BIND_AUTO_CREATE);
        } else{
            Log.d(TAG, "service not running: ");
        }
    }

    private void MediaPlayerUI() {
        songList.get(currentSong).setPlaying(true);
        songsAdapter.List(songList, currentSong);
        Song currentSong = songList.get(songsAdapter.getCurrentSong());
        Log.d(TAG, "MediaPlayerUI: "+ currentSong);
//        binding.txtnameSong.setText(currentSong.getTitle());
        if (services.getMediaPlayer().isPlaying()) {
            mBinding.playPause.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24_theme);
        } else {
            mBinding.playPause.setImageResource(R.drawable.ic_baseline_play_circle_outline_24_theme);
        }
        handler = new Handler();
//        binding.control.setVisibility(View.VISIBLE);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        services.disconnect();
        mBinding = null;
        handler = null;
        songsAdapter.clear();
        songsAdapter = null;
    }
}

