package com.example.mockproject.view.main;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.mockproject.databinding.ActivityMainBinding;
import com.example.mockproject.R;
import com.example.mockproject.databinding.MenuItemBinding;
import com.example.mockproject.service.ClearMediaPlayerListener;
import com.example.mockproject.service.Notification;
import com.example.mockproject.service.RunMedia;
import com.example.mockproject.service.Services;
import com.example.mockproject.view.main.fragmentelement.song.element.allsong.Song;
import com.example.mockproject.view.main.fragmentelement.song.element.allsong.SongOnClickListener;
import com.example.mockproject.view.main.fragmentelement.song.element.allsong.SongViewModel;
import com.example.mockproject.view.main.fragmentelement.song.element.allsong.SongsAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements OpenNavListener {
    private static final String TAG = "MainActivity";
    static SongsAdapter songsAdapter;
    public static boolean inService = false;
    public static Handler handler;
    public static int currentSong = -1;
    SongViewModel songViewModel = new ViewModelProvider(ViewModelStore::new).get(SongViewModel.class);
    public static List<Song> songList;
    private Services services;
    private ActivityMainBinding mBinding;
    private MainActivityViewModel mainActivityViewModel;
    NavHostFragment navHostFragment;
    private NavController navController;

    private final ServiceConnection mNewConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            services = ((Services.MusicBinder) service).getMusicService();
            services.connectToUI(MainActivity.this);
            inService = true;
            if (services.getMediaPlayer() != null) {
                MediaPlayerUI();
            } else {
                Song song = songList.get(currentSong);
                Log.d(TAG, "onServiceConnected: "+ currentSong);
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
            unbindService(mNewConnection);
            inService = false;
            currentSong = -1;
            handler = null;
        }
    };
    public final SongOnClickListener songOnClickListener = position -> {
        if (inService) {
            currentSong = position;
            Song song = songList.get(currentSong);
            Log.d(TAG, ": onClick"+ currentSong);
            services.updateSong(song);
        } else {
            Intent intent = new Intent(this, Services.class);
            startService(intent);
            bindService(intent, mNewConnection, BIND_AUTO_CREATE);
            currentSong = position;
            Log.d(TAG, ": onClick"+ currentSong);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        songList = songViewModel.getSongList(this);
        Log.d(TAG, "onCreate: " + songList);
        //Setup ViewPager
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        mBinding.viewPager.setAdapter(viewPagerAdapter);

        //layout bottom control
        mBinding.playPause.setOnClickListener(v -> services.play_pause());
        mBinding.previous.setOnClickListener(v -> services.previous());
        mBinding.next.setOnClickListener(v -> services.next());
        songsAdapter = new SongsAdapter(Song.diffCallback, songOnClickListener);
        songsAdapter.submitList(songList);
        mBinding.layoutBottomControl.setVisibility(View.GONE);

        //Create navHost and add BottomNav to navController
        navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView);
        navController = Objects.requireNonNull(navHostFragment).getNavController();
        setUpBottomNav(navController);

        //check permission
        boolean hasPermission = hasPermission();
        if (hasPermission) {
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
        setUpViewPager();
        setUpRcvNav();
        openNowPlaying();
    }
    private boolean hasPermission() {
        return (ContextCompat
                .checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED);
    }

    private void setUpRcvNav() {

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, layoutManager.VERTICAL);
        mBinding.rcvMenu.addItemDecoration(dividerItemDecoration);
        mainActivityViewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        mainActivityViewModel
                .getListMenuModelLiveData()
                .observe(this, new Observer<List<MenuModel>>() {
                    @Override
                    public void onChanged(List<MenuModel> menuModelList) {
                        Log.d(TAG, "onChanged: " + menuModelList);

                        mBinding.rcvMenu.setLayoutManager(layoutManager);
                        MenuAdapter menuAdapter = new MenuAdapter(menuModelList);
                        mBinding.rcvMenu.setAdapter(menuAdapter);
                    }
                });

    }
    private void MediaPlayerUI() {
        songList.get(currentSong).setPlaying(true);
        boolean statusCurrentSong = songList.get(currentSong).isPlaying();
        Log.d(TAG, "MediaPlayerUI: "+ statusCurrentSong);
        if(statusCurrentSong == true){
            mBinding.layoutBottomControl.setVisibility(View.VISIBLE);
        }

        songsAdapter.List(songList, currentSong);
        Song currentSong = songList.get(songsAdapter.getCurrentSong());
        Log.d(TAG, "MediaPlayerUI: " + currentSong);
        if (services.getMediaPlayer().isPlaying()) {
            mBinding.playPause
                    .setImageResource(R.drawable.ic_baseline_pause_circle_outline_24_white);
        } else {
            mBinding.playPause
                    .setImageResource(R.drawable.ic_baseline_play_circle_outline_24_white);
        }
        mBinding.imgBottomControl.setImageBitmap(currentSong.getImg());
        handler = new Handler();
    }

    public void restoreMusic() {
        if (Services.serviceRunning) {
            Intent intent = new Intent(MainActivity.this, Services.class);
            startService(intent);
            bindService(intent, mNewConnection, BIND_AUTO_CREATE);
        }
    }

    private void setUpBottomNav(NavController navController) {
        NavigationUI.setupWithNavController(mBinding.bottomNav, navController);
        mBinding.bottomNav
                .setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        mBinding.viewPager.setCurrentItem(0);
                        break;
                    case R.id.action_song:
                        mBinding.viewPager.setCurrentItem(1);
                        break;
                    case R.id.action_setting:
                        mBinding.viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (mBinding.drawerlayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerlayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    //set Item in ViewPager2
    private void setUpViewPager() {
        mBinding.viewPager.setUserInputEnabled(false);
        mBinding.viewPager
                .registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        mBinding.bottomNav.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        mBinding.bottomNav.getMenu().findItem(R.id.action_song).setChecked(true);
                        break;
                    case 2:
                        mBinding.bottomNav.getMenu().findItem(R.id.action_setting).setChecked(true);
                        break;
                }
            }
        });
    }

    private void openNowPlaying(){
//        navController = Navigation.findNavController(MainActivity.this, R.id.fragmentContainerView);
        mBinding.layoutBottomControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.viewPager.setCurrentItem(R.id.nowPlayingFragment);
                mBinding.bottomNav.setSelectedItemId(R.id.nowPlayingFragment);
//                NavigationUI.onNavDestinationSelected(menuItem,navController);
//                navController = Navigation.findNavController(MainActivity.this, R.id.nowPlayingFragment);
//                navController.navigateUp(); // to clear previous navigation history
//                navController.navigate(R.id.nowPlayingFragment);
                mBinding.layoutBottomControl.setVisibility(View.GONE);
            }
        });
    }
    @Override
    public void navListener() {
        if (mBinding.drawerlayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerlayout.close();
        } else {
            mBinding.drawerlayout.openDrawer(GravityCompat.START);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        services.disconnect();
        songsAdapter.clear();
        songsAdapter = null;
    }
}