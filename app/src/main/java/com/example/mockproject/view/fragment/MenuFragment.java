package com.example.mockproject.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject.R;
import com.example.mockproject.databinding.FragmentMenuBinding;
import com.example.mockproject.model.MenuModel;
import com.example.mockproject.view.adapter.MenuAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentMenuBinding mBinding;
    private List<MenuModel> menuModelList;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuFragment() {
        // Required empty public constructor
    }

    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentMenuBinding.inflate(inflater,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        menuModelList = new ArrayList<>();
        initDataMenu();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mBinding.rcvMenu.getContext(),
                layoutManager.getOrientation());

        mBinding.rcvMenu.addItemDecoration(dividerItemDecoration);
        mBinding.rcvMenu.setLayoutManager(layoutManager);
        MenuAdapter menuAdapter = new MenuAdapter(menuModelList);
        mBinding.rcvMenu.setAdapter(menuAdapter);


    }
    private void initDataMenu(){
        menuModelList.add(new MenuModel(R.drawable.ic_theme,"Theme"));
        menuModelList.add(new MenuModel(R.drawable.ic_cutter,"RingTone Cutter"));
        menuModelList.add(new MenuModel(R.drawable.ic_sleep_timer,"Sleep Timer"));
        menuModelList.add(new MenuModel(R.drawable.ic_quliser,"Equliser"));
        menuModelList.add(new MenuModel(R.drawable.ic_drive_mode,"Drive Mode"));
        menuModelList.add(new MenuModel(R.drawable.ic_hidden_folder,"Hidden Folder"));
        menuModelList.add(new MenuModel(R.drawable.ic_scan_media,"Scan Media"));
    }
}