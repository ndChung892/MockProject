package com.example.mockproject.view.main.fragmentelement.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject.databinding.ItemRecommendedBinding;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {
    List<Recommended> recommendedList;

    public void setData(List<Recommended> recommendedList) {
        this.recommendedList = recommendedList;
    }

    @NonNull
    @Override
    public RecommendedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRecommendedBinding mBinding = ItemRecommendedBinding.inflate(inflater, parent, false);
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedAdapter.ViewHolder holder, int position) {
        Recommended recommended = recommendedList.get(position);
        if (recommended == null) {
            return;
        }
        holder.mBinding.imgRecommended.setImageResource(recommended.getImg());
        holder.mBinding.txtReSinger.setText(recommended.getSinger());
        holder.mBinding.txtReSong.setText(recommended.getSong());

    }

    @Override
    public int getItemCount() {
        if (recommendedList != null) {
            return recommendedList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemRecommendedBinding mBinding;

        public ViewHolder(@NonNull ItemRecommendedBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }
}
