package com.example.mockproject.view.main.fragmentelement.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject.databinding.FragmentHomeBinding;
import com.example.mockproject.view.main.MainActivityViewModel;
import com.example.mockproject.view.main.OpenNavListener;

import java.util.List;

public class HomeFragment extends Fragment {
    public static final String TAG = "HomeFragment";

    private FragmentHomeBinding mBinding;
    private OpenNavListener openNavListener;
    MainActivityViewModel mainActivityViewModel;

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
        mainActivityViewModel = new ViewModelProvider(ViewModelStore::new).get(MainActivityViewModel.class);

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

    @Override
    public void onStart() {
        super.onStart();
    }
}