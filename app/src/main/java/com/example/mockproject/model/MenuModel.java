package com.example.mockproject.model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class MenuModel {
    private Drawable img;
    private String menuItem;

    public MenuModel(Drawable img, String menuItem) {
        this.img = img;
        this.menuItem = menuItem;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

//@BindingAdapter()
}

