package com.example.mockproject.view.main.fragmentelement.song.element.allsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mockproject.databinding.FragmentAllSongsBinding;


public class AllSongsFragment extends Fragment {
    FragmentAllSongsBinding mBinding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentAllSongsBinding.inflate(getLayoutInflater(), container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.rcvAllSong.setLayoutManager(layoutManager);
        SongViewModel songViewModel = new ViewModelProvider(ViewModelStore::new).get(SongViewModel.class);

        SongsAdapter songsAdapter = new SongsAdapter(songViewModel.getSongList(requireActivity()));
        mBinding.rcvAllSong.setAdapter(songsAdapter);

    }
}