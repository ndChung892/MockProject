package com.example.mockproject.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
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
import com.example.mockproject.viewmodel.HomeFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    public static final String TAG = "HomeFragment";

    private FragmentHomeBinding mBinding;
    private OpenNavListener openNavListener;

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.rcvHome.setLayoutManager(layoutManager);

        HomeFragmentViewModel homeFragmentViewModel = new ViewModelProvider(ViewModelStore::new).get(HomeFragmentViewModel.class);
        homeFragmentViewModel.getHomeModelListMutable().observe(getViewLifecycleOwner(), new Observer<List<HomeModel>>() {
            @Override
            public void onChanged(List<HomeModel> homeModels) {
                MenuHomeAdapter menuHomeAdapter = new MenuHomeAdapter(getContext(), homeModels);
                mBinding.rcvHome.setAdapter(menuHomeAdapter);
            }
        });

    }

    private void setUpToolBar() {
        openNavListener = (OpenNavListener) requireActivity();
        mBinding.toolbarHome.setNavigationOnClickListener(v -> {
            openNavListener.navListener();
        });
    }
}