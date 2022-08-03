package com.example.mockproject.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject.R;
import com.example.mockproject.model.MenuModel;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    List<MenuModel> menuList;

    public MenuAdapter(List<MenuModel> menuList) {
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.menu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuModel menuModel = menuList.get(position);
        if (menuModel != null) {
            holder.imgMenu.setImageResource(menuModel.getImg());
            holder.txtMenu.setText(menuModel.getMenuItem());
        }
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtMenu;
        private ImageView imgMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMenu = itemView.findViewById(R.id.txtMenu);
            imgMenu = itemView.findViewById(R.id.imgMenu);
        }
    }
}
