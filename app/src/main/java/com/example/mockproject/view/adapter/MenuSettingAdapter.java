package com.example.mockproject.view.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject.databinding.MenuItemBinding;
import com.example.mockproject.databinding.MenuSettingItemBinding;
import com.example.mockproject.model.MenuSetting;

import java.util.List;

public class MenuSettingAdapter extends RecyclerView.Adapter<MenuSettingAdapter.ViewHolder>{
    private List<MenuSetting> menuSettingList;



    public MenuSettingAdapter(List<MenuSetting> menuSettingList) {
        this.menuSettingList = menuSettingList;
    }

    @NonNull
    @Override
    public MenuSettingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MenuSettingItemBinding mBinding = MenuSettingItemBinding.inflate(inflater,parent,false);
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuSettingAdapter.ViewHolder holder, int position) {
        MenuSetting menuSetting = menuSettingList.get(position);
        if(menuSetting!=null){
            holder.mBinding.setMenuSetting(menuSetting);
            holder.mBinding.executePendingBindings();
        }

    }

    @Override
    public int getItemCount() {
        if(menuSettingList!= null){
            return menuSettingList.size();
        }
       return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MenuSettingItemBinding mBinding;
        public ViewHolder(@NonNull MenuSettingItemBinding binding) {
            super(binding.getRoot());
            this.mBinding= binding;
        }
    }
    public interface onClickSettingItem{
        void onclickItem();
    }
}
