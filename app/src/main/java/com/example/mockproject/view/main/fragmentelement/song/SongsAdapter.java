package com.example.mockproject.view.main.fragmentelement.song;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject.databinding.ItemSongsBinding;

import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder> {
    ItemSongsBinding mBinding;
    List<Song> songList;

    public SongsAdapter(List<Song> songList) {
        this.songList = songList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mBinding = ItemSongsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.mBinding.txtSongItem.setText(song.getSongs());
        holder.mBinding.txtSingerItem.setText(song.getSinger());

    }

    @Override
    public int getItemCount() {
//        if()
        return songList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemSongsBinding mBinding;
        public ViewHolder(@NonNull ItemSongsBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }
}
