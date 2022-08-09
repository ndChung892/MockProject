package com.example.mockproject.view.main;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject.databinding.MenuItemBinding;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private static final String TAG = "MenuAdapter";
    List<MenuModel> menuModelList;

    public MenuAdapter(List<MenuModel> menuModelList) {
        this.menuModelList = menuModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext()) ;
        MenuItemBinding mBinding = MenuItemBinding.inflate(inflater,parent,false);
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuModel menuModel = menuModelList.get(position);
        if (menuModel != null) {
            holder.mBinding.setMenuItem(menuModel);
            holder.mBinding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+ menuModelList);
        return menuModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private MenuItemBinding mBinding;

        public ViewHolder(@NonNull MenuItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }
}
