package com.example.thriftshop.searchcontent.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product implements Parcelable {
    private String productName;
    private float price;
    private String genderCategory;
    private int image;
    private int type;
    private String brandName;
    private String itemDescription;
    private int  size;
    @PrimaryKey(autoGenerate = true)
    public int productId;

    public Product(String productName, float price, String genderCategory, int image, int type) {
        this.productName = productName;
        this.price = price;
        this.genderCategory = genderCategory;
        this.image = image;
        this.type = type;
    }

    public Product() {
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

    @Override
    public int describeContents() {
        return 0;
    }
    protected Product(Parcel in) {
        productName = in.readString();
        price = in.readFloat();
        genderCategory = in.readString();
        image = in.readInt();
        type = in.readInt();
        productId = in.readInt();
        brandName=in.readString();
        itemDescription=in.readString();
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.productName);
        parcel.writeFloat(this.price);
        parcel.writeString(this.genderCategory);
        parcel.writeInt(this.image);
        parcel.writeInt(this.type);
        parcel.writeInt(this.productId);
        parcel.writeString(this.brandName);
        parcel.writeString(this.itemDescription);


    }
    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
