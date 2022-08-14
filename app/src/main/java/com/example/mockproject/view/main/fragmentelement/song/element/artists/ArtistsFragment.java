package com.example.mockproject.view.main.fragmentelement.song.element.artists;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject.R;
import com.example.mockproject.databinding.FragmentArtistsBinding;


public class ArtistsFragment extends Fragment {
    FragmentArtistsBinding mBinding;
    ArtistViewModel artistViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentArtistsBinding.inflate(getLayoutInflater(), container,false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        artistViewModel = new ViewModelProvider(ViewModelStore::new).get(ArtistViewModel.class);
        ArtistsAdapter artistsAdapter = new ArtistsAdapter(artistViewModel.initArtists(),getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.rcvArtists.setLayoutManager(layoutManager);
        mBinding.rcvArtists.setAdapter(artistsAdapter);

    }
}