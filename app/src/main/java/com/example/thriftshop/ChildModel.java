package com.example.thriftshop;

public class ChildModel {
    private String name;
    private  String price;
    private int icon;

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }



    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }


    public ChildModel(String name,String price,int icon){
        this.name=name;

        this.icon=icon;
        this.price=price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

