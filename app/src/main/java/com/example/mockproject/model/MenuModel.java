package com.example.mockproject.model;

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
}
