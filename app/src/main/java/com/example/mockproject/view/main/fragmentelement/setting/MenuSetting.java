package com.example.mockproject.view.main.fragmentelement.setting;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class MenuSetting {
    private String menuSetting;
    private int img;

    public MenuSetting( int img, String menuSetting) {
        this.img = img;
        this.menuSetting = menuSetting;

    }

    public String getMenuSetting() {
        return menuSetting;
    }

    public void setMenuSetting(String menuSetting) {
        this.menuSetting = menuSetting;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
    @BindingAdapter("src")
    public static void setImageResource(ImageView imageView, int img){
        imageView.setImageResource(img);
    }
}
