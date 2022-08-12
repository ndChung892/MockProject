package com.example.mockproject.view.main.fragmentelement.song;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mockproject.view.main.fragmentelement.home.HomeFragment;
import com.example.mockproject.view.main.fragmentelement.setting.SettingFragment;
import com.example.mockproject.view.main.fragmentelement.song.element.AlbumsFragment;
import com.example.mockproject.view.main.fragmentelement.song.element.ArtistsFragment;
import com.example.mockproject.view.main.fragmentelement.song.element.GenresFragment;
import com.example.mockproject.view.main.fragmentelement.song.element.PlaylistFragment;
import com.example.mockproject.view.main.fragmentelement.song.element.allsong.AllSongsFragment;

public class SongViewPagerAdapter extends FragmentStateAdapter {


    public SongViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new AllSongsFragment();
            case 1:
                return new PlaylistFragment();
            case 2:
                return new AlbumsFragment();
            case 3:
                return new ArtistsFragment();
            case 4: return new GenresFragment();
            default: return new AllSongsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

}
