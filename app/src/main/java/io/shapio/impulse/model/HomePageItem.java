package io.shapio.impulse.model;

/**
 * Created by TheOSka on 16/4/2016.
 */
public class HomePageItem {
    private String itemName;
    private Integer itemIcon;

    public void setItemIcon(Integer itemIcon) {
        this.itemIcon = itemIcon;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getItemIcon() {
        return itemIcon;
    }
}
