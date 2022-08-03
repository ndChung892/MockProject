package com.example.mockproject.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mockproject.view.fragment.HomeFragment;
import com.example.mockproject.view.fragment.SettingFragment;
import com.example.mockproject.view.fragment.SongFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:return new HomeFragment();
            case 1:
                return new SongFragment();
            case 2:
                return new SettingFragment();
            default:
                return new HomeFragment();
        }
//        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
