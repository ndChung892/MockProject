package com.example.mockproject.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject.databinding.ItemPlaylistBinding;
import com.example.mockproject.model.Playlist;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    List<Playlist> playlistList;

    public PlaylistAdapter(List<Playlist> playlistList) {
        this.playlistList = playlistList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPlaylistBinding mBinding = ItemPlaylistBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = playlistList.get(position);
        if (playlist==null){
            return;
        }
        holder.mBinding.imgPlaylist.setImageResource(playlist.getImg());
        holder.mBinding.txtPlSong.setText(playlist.getSong());
        holder.mBinding.txtPlSinger.setText(playlist.getSinger());

    }

    @Override
    public int getItemCount() {
        if(playlistList!=null){
            return playlistList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemPlaylistBinding mBinding;
        public ViewHolder(@NonNull ItemPlaylistBinding  binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }
}
