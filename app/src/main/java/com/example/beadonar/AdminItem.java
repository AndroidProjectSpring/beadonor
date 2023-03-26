package com.example.beadonar;

public class AdminItem {
    private String itemName;
    private String address;


    public AdminItem(String itemName, String itemDescription) {
        this.itemName = itemName;
        this.address = itemDescription;

    }

    public String getItemName() {
        return itemName;
    }

    public String getAddress() {
        return address;
    }


}
