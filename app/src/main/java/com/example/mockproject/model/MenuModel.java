package com.example.mockproject.model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class MenuModel {
    private int img;
    private String menuItem;

    public MenuModel(int img, String menuItem) {
        this.img = img;
        this.menuItem = menuItem;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    @BindingAdapter("src")
    public static void setImageResource(ImageView imageView, int img){
        imageView.setImageResource(img);
    }
}

