package com.example.mockproject.view.main.fragmentelement.song.element.allsong;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mockproject.databinding.FragmentAllSongsBinding;
import com.example.mockproject.view.main.MainActivity;


public class AllSongsFragment extends Fragment {
    private static final String TAG = "AllSongsFragment";
    FragmentAllSongsBinding mBinding;

    SongsAdapter songsAdapter;

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
        songsAdapter = new SongsAdapter(Song.diffCallback, ((MainActivity) getActivity()).songOnClickListener);

        Log.d(TAG, "onViewCreated: ");
        mBinding.rcvAllSong.setAdapter(songsAdapter);
        songsAdapter.submitList(MainActivity.songList);
        ((MainActivity) getActivity()).restoreMusic();
    }


}

