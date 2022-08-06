package com.example.mockproject.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject.databinding.MenuHomeItemBinding;
import com.example.mockproject.model.HomeModel;

import java.util.List;

public class MenuHomeAdapter extends RecyclerView.Adapter<MenuHomeAdapter.ViewHolder> {
    public static final int RECOMMENDED_TYPE =0;
    public static final int PLAYLIST_TYPE =1;
    public static final int RECENTLY_PLAYED_TYPE =2;
    List<HomeModel> homeModelList;
    Context context;

    public MenuHomeAdapter(Context context, List<HomeModel> homeModelList) {
        this.context = context;
        this.homeModelList = homeModelList;
    }

    @Override
    public int getItemViewType(int position) {
        return homeModelList.get(position).getType();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MenuHomeItemBinding mBinding = MenuHomeItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeModel homeModel = homeModelList.get(position);
        if(homeModel==null){
            return;
        }
        if (RECOMMENDED_TYPE == holder.getItemViewType()){
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
            holder.mBinding.rcvItemHome.setLayoutManager(layoutManager);
            RecommendedAdapter recommendedAdapter = new RecommendedAdapter();
            recommendedAdapter.setData(homeModel.getRecommendedList());

            holder.mBinding.rcvItemHome.setAdapter(recommendedAdapter);

        }else if(PLAYLIST_TYPE == holder.getItemViewType()){

        }else if(RECENTLY_PLAYED_TYPE == holder.getItemViewType())

        holder.mBinding.txtTitle.setText(homeModel.getTitle());
        holder.mBinding.txtViewAll.setText(homeModel.getViewAll());
    }

    @Override
    public int getItemCount() {
        if(homeModelList!= null){
            return homeModelList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private MenuHomeItemBinding mBinding;

        public ViewHolder(@NonNull MenuHomeItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }
}
