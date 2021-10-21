package com.example.apptracnghiem.gridviewcustom;

public class GridviewMenu {
    private int imgMenu;
    private String nameMenu;

    public GridviewMenu() {
    }

    public GridviewMenu(int imgMenu, String nameMenu) {
        this.imgMenu = imgMenu;
        this.nameMenu = nameMenu;
    }

    public int getImgMenu() {
        return imgMenu;
    }

    public void setImgMenu(int imgMenu) {
        this.imgMenu = imgMenu;
    }

    public String getNameMenu() {
        return nameMenu;
    }

    public void setNameMenu(String nameMenu) {
        this.nameMenu = nameMenu;
    }
}
