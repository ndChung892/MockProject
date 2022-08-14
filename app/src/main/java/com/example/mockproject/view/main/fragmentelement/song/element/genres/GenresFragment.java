package com.example.mockproject.view.main.fragmentelement.song.element.genres;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject.R;
import com.example.mockproject.databinding.FragmentGenresBinding;

public class GenresFragment extends Fragment {
    FragmentGenresBinding mBinding;
    GenresViewModel genresViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentGenresBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        genresViewModel = new ViewModelProvider(ViewModelStore::new).get(GenresViewModel.class);
        GenresAdapter genresAdapter = new GenresAdapter(genresViewModel.initGenres(),getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        mBinding.rcvGenres.setAdapter(genresAdapter);
        mBinding.rcvGenres.setLayoutManager(layoutManager);
    }
}