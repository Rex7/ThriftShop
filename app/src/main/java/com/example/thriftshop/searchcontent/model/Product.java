package com.example.thriftshop.searchcontent.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    private String productName;
    private float price;
    private  String genderCategory;
    private int image;
    private  int type;
    @PrimaryKey(autoGenerate = true)
    public int productId;

    public Product(String productName, float price, String genderCategory, int image, int type) {
        this.productName = productName;
        this.price = price;
        this.genderCategory = genderCategory;
        this.image = image;
        this.type = type;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    /*
    @returns genderCategory as men,women or kids
Gender category for men women kids
 */
    public String getGenderCategory() {
        return genderCategory;
    }
    /*
    Gender category for men women kids
     */
    public void setGenderCategory(String genderCategory) {
        this.genderCategory = genderCategory;
    }
    /*
    @return number 1 for used number 2 for unused , number 3 for rent
        */
    public int getType() {
        return type;
    }
    /*
       set int for type of product
       number 1 for used number 2 for unused , number 3 for rent
        */
    public void setType(int type) {
        this.type = type;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
