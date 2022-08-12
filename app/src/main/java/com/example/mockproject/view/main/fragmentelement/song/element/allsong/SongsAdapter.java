package com.example.mockproject.view.main.fragmentelement.song.element.allsong;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject.R;
import com.example.mockproject.databinding.ItemSongsBinding;

import java.util.List;

public class SongsAdapter extends ListAdapter<Song, SongsAdapter.ViewHolder>  {
    private static final String TAG = "SongsAdapter";
    ItemSongsBinding mBinding;
    List<Song> songList;
    private int previousSong;
    private int currentSong;
    SongOnClickListener songOnClickListener;

    public SongsAdapter(@NonNull DiffUtil.ItemCallback<Song> diffCallback, SongOnClickListener songOnClickListener) {
        super(diffCallback);
        this.songOnClickListener = songOnClickListener;
        previousSong = -1;
        currentSong = -1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mBinding = ItemSongsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = getItem(position);
        if(song == null){
            return;
        }
        holder.mBinding.txtSongItem.setText(song.getSongs());
        holder.mBinding.txtSingerItem.setText(song.getSinger());
        holder.mBinding.imgCircleView.setImageBitmap(song.getImg());
        if (song.isPlaying()) {
            holder.mBinding.imgPlayAndPause.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24_theme);
        } else {
            holder.mBinding.imgPlayAndPause.setImageResource(R.drawable.ic_baseline_play_circle_outline_24_theme);
        }
        holder.mBinding.itemSong.setOnClickListener(view -> songOnClickListener.songOnClick(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemSongsBinding mBinding;
        public ViewHolder(@NonNull ItemSongsBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }

    public void List(@Nullable List<Song> list, int  newCurrent) {
        this.submitList(list);
        previousSong = currentSong;
        currentSong = newCurrent;
        if(previousSong != -1) {
            notifyItemChanged(previousSong);
        }
        notifyItemChanged(currentSong);
    }
    public void clear() {
        this.songOnClickListener = null;
    }
    public int getPreviousSong() {
        return previousSong;
    }
    public int getCurrentSong() {
        return currentSong;
    }


}
