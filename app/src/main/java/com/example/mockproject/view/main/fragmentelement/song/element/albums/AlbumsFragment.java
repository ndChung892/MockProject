package com.example.mockproject.view.main.fragmentelement.song.element.albums;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mockproject.R;
import com.example.mockproject.databinding.FragmentAlbumsBinding;

public class AlbumsFragment extends Fragment {
    FragmentAlbumsBinding mBinding;
    AlbumsViewModel albumsViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentAlbumsBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        albumsViewModel = new ViewModelProvider(ViewModelStore::new).get(AlbumsViewModel.class);
        AlbumsAdapter albumsAdapter = new AlbumsAdapter(albumsViewModel.initAlbums(), getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mBinding.rcvAlbums.setLayoutManager(gridLayoutManager);
        mBinding.rcvAlbums.setAdapter(albumsAdapter);
    }
}