package com.example.mockproject.view.main.fragmentelement.song.element.allsong;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mockproject.R;
import com.example.mockproject.databinding.ActivityMainBinding;
import com.example.mockproject.databinding.FragmentNowPlayingBinding;
import com.example.mockproject.service.Services;
import com.example.mockproject.utils.Utils;
import com.example.mockproject.view.main.MainActivity;
import com.example.mockproject.view.main.MainActivityViewModel;
import com.example.mockproject.view.main.fragmentelement.song.SongFragment;

import java.io.Serializable;

public class NowPlayingFragment extends Fragment {
    private static final String TAG = "NowPlayingFragment" ;
    private FragmentNowPlayingBinding mBinding;
    private MainActivityViewModel mainActivityViewModel;
    SongViewModel songViewModel;
    private SongsAdapter songsAdapter;
    Services services;
    ActionBar actionBar;

    public NowPlayingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentNowPlayingBinding.inflate(getLayoutInflater(), container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Song currentSong = (Song) getArguments().getSerializable("CurrentSong");
//        Log.d(TAG, "onViewCreated: "+ currentSong);

        mBinding.playPauseNowPlaying.setOnClickListener(v -> MainActivity.services.play_pause());
        mBinding.previousNowPlaying.setOnClickListener(v -> MainActivity.services.previous());
        mBinding.nextNowPlaying.setOnClickListener(v -> MainActivity.services.next());

        songViewModel = new ViewModelProvider(requireActivity()).get(SongViewModel.class);

        mainActivityViewModel = new ViewModelProvider(ViewModelStore::new).get(MainActivityViewModel.class);
        mainActivityViewModel.getSong().observe(getViewLifecycleOwner(), new Observer<Song>() {
            @Override
            public void onChanged(Song song) {

                mBinding.imgCenterNowPlaying.setImageBitmap(song.getImg());
                mBinding.songNowPlaying.setText(song.getSongs());
                mBinding.singerNowPlaying.setText(song.getSinger());
                mBinding.albumNowPlaying.setText(song.getAlbum());
                mBinding.txtAllDurationCircleSeekbar.setText(Utils.formatDuration(requireActivity(), MainActivity.services.getMediaPlayer().getDuration()));
                Log.d(TAG, "onChanged: "+ song);
//                songsAdapter.submitList(songViewModel.getSongList(requireActivity()));
//                Song currentSong = MainActivity.songList.get(songsAdapter.getCurrentSong());
                if (MainActivity.services.getMediaPlayer().isPlaying()) {
                    mBinding
                            .playPauseNowPlaying
                            .setImageResource(R.drawable.ic_baseline_pause_circle_filled_24_nowplaying);
                } else {
                    mBinding.playPauseNowPlaying
                            .setImageResource(R.drawable.ic_baseline_play_circle_filled_24_nowplaying);
                }

            }
        });
//        mBinding.toolbarSettingNowPlaying.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(view).popBackStack();
//            }
//        });


        setUpBackPress();


    }



    private void setUpBackPress(){
        mBinding.toolbarSettingNowPlaying.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(services, "SOS", Toast.LENGTH_SHORT).show();
                AllSongsFragment allSongsFragment = new AllSongsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, allSongsFragment).commit();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getActivity().getMenuInflater();;
        inflater.inflate(R.menu.menu_nowplaying, menu);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}