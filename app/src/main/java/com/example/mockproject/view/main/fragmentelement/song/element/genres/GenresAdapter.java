package com.example.mockproject.view.main.fragmentelement.song.element.genres;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject.R;
import com.example.mockproject.databinding.ItemArtistsBinding;
import com.example.mockproject.databinding.ItemGenresBinding;

import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> {
    ItemGenresBinding mBinding;
    List<GenresModel> genresModelList;
    Context mContext;

    public GenresAdapter(List<GenresModel> genresModelList, Context mContext) {
        this.genresModelList = genresModelList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mBinding = ItemGenresBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GenresModel genresModel = genresModelList.get(position);
        holder.mBinding.setGenres(genresModel);
        holder.mBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if(genresModelList!=null) {
            return genresModelList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        ItemGenresBinding mBinding;
        public ViewHolder(@NonNull ItemGenresBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

    }
}
