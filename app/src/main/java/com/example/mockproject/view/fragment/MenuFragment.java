package com.example.mockproject.view.fragment;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject.R;
import com.example.mockproject.databinding.FragmentMenuBinding;
import com.example.mockproject.model.MenuModel;
import com.example.mockproject.view.adapter.MenuAdapter;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {
    public static final String TAG = "MenuFragment";
    private FragmentMenuBinding mBinding;
    private List<MenuModel> menuModelList;


    public MenuFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentMenuBinding.inflate(inflater,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(getResources().getColor(R.color.purple_200));
//        initDataMenu();
        Log.i(TAG, "onViewCreated: ");
//        LinearLayoutManager layoutManager =
//                new LinearLayoutManager(requireActivity());
//
//        DividerItemDecoration dividerItemDecoration =
//                new DividerItemDecoration(getContext(),
//                layoutManager.VERTICAL);
//
//        mBinding.rcvMenu.addItemDecoration(dividerItemDecoration);
//        mBinding.rcvMenu.setLayoutManager(layoutManager);
//        MenuAdapter menuAdapter = new MenuAdapter(initDataMenu());
//        mBinding.rcvMenu.setAdapter(menuAdapter);
    }
//    private List<MenuModel> initDataMenu(){
//        menuModelList = new ArrayList<>();
//        menuModelList.add(new MenuModel(R.drawable.ic_theme,"Theme"));
//        menuModelList.add(new MenuModel(R.drawable.ic_cutter,"RingTone Cutter"));
//        menuModelList.add(new MenuModel(R.drawable.ic_sleep_timer,"Sleep Timer"));
//        menuModelList.add(new MenuModel(R.drawable.ic_quliser,"Equliser"));
//        menuModelList.add(new MenuModel(R.drawable.ic_drive_mode,"Drive Mode"));
//        menuModelList.add(new MenuModel(R.drawable.ic_hidden_folder,"Hidden Folder"));
//        menuModelList.add(new MenuModel(R.drawable.ic_scan_media,"Scan Media"));
//        Log.i(TAG, "initDataMenu: ");
//        return menuModelList;
//    }
}