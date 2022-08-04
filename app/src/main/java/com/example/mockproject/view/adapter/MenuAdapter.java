package com.example.mockproject.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject.R;
import com.example.mockproject.databinding.MenuItemBinding;
import com.example.mockproject.model.MenuModel;

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

//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view = inflater.inflate(R.layout.menu_item, parent, false);
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
//            holder.imgMenu.setImageResource(menuModel.getImg());
//            holder.txtMenu.setText(menuModel.getMenuItem());
        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+ menuModelList);
        return menuModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        private TextView txtMenu;
//        private ImageView imgMenu;

        private MenuItemBinding mBinding;

        public ViewHolder(@NonNull MenuItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
//            txtMenu = itemView.findViewById(R.id.txtMenu);
//            imgMenu = itemView.findViewById(R.id.imgMenu);
        }
    }
}
