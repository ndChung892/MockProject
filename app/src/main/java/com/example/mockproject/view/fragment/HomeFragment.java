package com.example.mockproject.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject.OpenNavListener;
import com.example.mockproject.R;
import com.example.mockproject.databinding.FragmentHomeBinding;
import com.example.mockproject.model.HomeModel;
import com.example.mockproject.model.Playlist;
import com.example.mockproject.model.RecentlyPlayed;
import com.example.mockproject.model.Recommended;
import com.example.mockproject.view.adapter.MenuHomeAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding mBinding;
    private OpenNavListener openNavListener;
    private List<HomeModel> homeModelList;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRcvMenu();
        setUpToolBar();
    }

    private void setUpRcvMenu() {
        createListHomeMenu();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        MenuHomeAdapter menuHomeAdapter = new MenuHomeAdapter(getContext(), homeModelList);
        mBinding.rcvHome.setLayoutManager(layoutManager);
        mBinding.rcvHome.setAdapter(menuHomeAdapter);
    }

    private void createListHomeMenu(){
        homeModelList = new ArrayList<>();

        List<Recommended> recommendedList = new ArrayList<>();
        recommendedList.add(new Recommended(R.drawable.img_recommened1,"Sound of Sky", "Dilon Bruce"));
        recommendedList.add(new Recommended(R.drawable.img_recommened1,"Sound of Sky", "Dilon Bruce"));
        recommendedList.add(new Recommended(R.drawable.img_recommened1,"Sound of Sky", "Dilon Bruce"));
        recommendedList.add(new Recommended(R.drawable.img_recommened1,"Sound of Sky", "Dilon Bruce"));

        List<Playlist> playlistList = new ArrayList<>();
        playlistList.add(new Playlist(R.drawable.img_playlist1, "Classic Playlist","Piano guys"));
        playlistList.add(new Playlist(R.drawable.img_playlist1, "Classic Playlist","Piano guys"));
        playlistList.add(new Playlist(R.drawable.img_playlist1, "Classic Playlist","Piano guys"));
        playlistList.add(new Playlist(R.drawable.img_playlist1, "Classic Playlist","Piano guys"));

        List<RecentlyPlayed> recentlyPlayedList = new ArrayList<>();
        recentlyPlayedList.add(new RecentlyPlayed("Billie Jean", "Micheal Jackson"));


        homeModelList.add(new HomeModel(0,"Recommended", "View All",recommendedList, null, null));
        homeModelList.add(new HomeModel(0,"Recommended", "View All",null, playlistList, null));
    }

    private void setUpToolBar() {
        openNavListener = (OpenNavListener) requireActivity();
        mBinding.toolbarHome.setNavigationOnClickListener(v -> {
            openNavListener.navListener();
        });
    }
}