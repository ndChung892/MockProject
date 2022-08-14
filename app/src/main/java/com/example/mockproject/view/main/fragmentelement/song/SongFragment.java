package com.example.mockproject.view.main.fragmentelement.song;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject.R;
import com.example.mockproject.databinding.FragmentSongBinding;
import com.example.mockproject.view.main.MainActivityViewModel;
import com.example.mockproject.view.main.OpenNavListener;
import com.example.mockproject.view.main.ViewPagerAdapter;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;


public class SongFragment extends Fragment {
    private FragmentSongBinding mBinding;
    private OpenNavListener openNavListener;
    private TabLayout mTabLayout;
    MainActivityViewModel mainActivityViewModel;
    private ViewPager2 mViewPager2;

    public SongFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentSongBinding.inflate(inflater, container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpToolBar();
        mainActivityViewModel = new ViewModelProvider(ViewModelStore::new).get(MainActivityViewModel.class);
        SongViewPagerAdapter songViewPagerAdapter
                = new SongViewPagerAdapter(this);
        mBinding.viewPagerSong.setAdapter(songViewPagerAdapter);
        new TabLayoutMediator(mBinding.tablayoutSong, mBinding.viewPagerSong,(tab, position) -> {
            switch (position){
                case 0:
                    tab.setText(R.string.all_song);
                    break;
                case 1:
                    tab.setText(R.string.playlists);
                    break;
                case 2:
                    tab.setText(R.string.albums);
                    break;
                case 3:
                    tab.setText(R.string.artists);
                    break;
                case 4:
                    tab.setText(R.string.genres);
                    break;
            }
        }).attach();

    }


    private void setUpToolBar() {
            openNavListener = (OpenNavListener) requireActivity();
            mBinding.toolbarSong.setNavigationOnClickListener(v -> {
                openNavListener.navListener();
            });

    }

    @Override
    public void onStart() {
        super.onStart();
    }
}