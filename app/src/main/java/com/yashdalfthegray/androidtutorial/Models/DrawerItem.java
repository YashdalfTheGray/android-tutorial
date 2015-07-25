package com.yashdalfthegray.androidtutorial.Models;

/**
 * Created by yash on 7/24/15.
 */

public class DrawerItem {
    String ItemName;
    int imgResID;
    public DrawerItem(String itemName, int imgResID) {
        super();
        ItemName = itemName;
        this.imgResID = imgResID;
    }
    public String getItemName() {
        return ItemName;
    }
    public void setItemName(String itemName) {
        ItemName = itemName;
    }
    public int getImgResID() {
        return imgResID;
    }
    public void setImgResID(int imgResID) {
        this.imgResID = imgResID;
    }
}
