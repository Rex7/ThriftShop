package com.example.thriftshop.homecontent;

public class ChildModel {
    private String productName;
    private  String price;
    private int icon;
    private  String genderType;
    private int productForSale;

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
        this.productName=name;

        this.icon=icon;
        this.price=price;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String name) {
        this.productName = name;
    }

    /*
    number 1 for used number 2 for unused , number 3 for rent
     */
    public int getProductForSale() {
        return productForSale;
    }
    /*
      number 1 for used number 2 for unused , number 3 for rent
       */
    public void setProductForSale(int productForSale) {
        this.productForSale = productForSale;
    }
/*
Gender category for men women kids
 */
    public String getGenderType() {
        return genderType;
    }
    /*
    Gender category for men women kids
     */
    public void setGenderType(String genderType) {
        this.genderType = genderType;
    }
}

