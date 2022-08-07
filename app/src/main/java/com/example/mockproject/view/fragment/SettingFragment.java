package com.example.mockproject.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mockproject.OpenNavListener;
import com.example.mockproject.R;
import com.example.mockproject.databinding.FragmentSettingBinding;
import com.example.mockproject.model.MenuSetting;
import com.example.mockproject.view.adapter.MenuSettingAdapter;
import com.example.mockproject.viewmodel.SettingFragmentViewModel;

import java.util.List;

public class SettingFragment extends Fragment {
    private FragmentSettingBinding mBinding;
    private OpenNavListener openNavListener;
    SettingFragmentViewModel viewModel;
    public SettingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentSettingBinding.inflate(inflater,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpToolBar();
        setUpRcv();
    }

    private void setUpRcv() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        mBinding.rcvSetting.setLayoutManager(layoutManager);
        mBinding.rcvSetting.addItemDecoration(itemDecoration);
        viewModel = new ViewModelProvider(ViewModelStore::new).get(SettingFragmentViewModel.class);

        viewModel.getMenuListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<MenuSetting>>() {
            @Override
            public void onChanged(List<MenuSetting> menuSettings) {
                MenuSettingAdapter adapter = new MenuSettingAdapter(menuSettings);
                mBinding.rcvSetting.setAdapter(adapter);
            }
        });

    }

    private void setUpToolBar() {
        openNavListener = (OpenNavListener) requireActivity();
        mBinding.toolbarSetting.setNavigationOnClickListener(v -> {
            openNavListener.navListener();
        });
    }
}