package com.example.mockproject.view.main.fragmentelement.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject.databinding.ItemRecentlyPlayedBinding;

import java.util.List;

public class RecentlyPlayedAdapter extends RecyclerView.Adapter<RecentlyPlayedAdapter.ViewHolder> {
    List<RecentlyPlayed> recentlyPlayedList;

    public void setData(List<RecentlyPlayed> recentlyPlayedList) {
        this.recentlyPlayedList = recentlyPlayedList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecentlyPlayedBinding mBinding =
                ItemRecentlyPlayedBinding
                        .inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecentlyPlayed recentlyPlayed = recentlyPlayedList.get(position);
        if(recentlyPlayed==null){
            return;
        }
        holder.mBinding.txtRPSinger.setText(recentlyPlayed.getSinger());
        holder.mBinding.txtRPSong.setText(recentlyPlayed.getSong());

    }

    @Override
    public int getItemCount() {
        if(recentlyPlayedList!=null){
            return recentlyPlayedList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemRecentlyPlayedBinding mBinding;
        public ViewHolder(@NonNull ItemRecentlyPlayedBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }
}
