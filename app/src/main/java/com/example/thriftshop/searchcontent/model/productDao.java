package com.example.thriftshop.searchcontent.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface productDao {
    @Insert
     void addProduct(ArrayList<Product> product);

    @Query("select * from product  where genderCategory =:category")
     List<Product> getAllProduct(String category);

    @Query("select count(*) from product")
    int getTableSize();
    @Query("select * from product")
    List<Product> getAllProduct();
    @Query("select * from product where productName LIKE  '%'|| :name || '%' ")
    List<Product> getProductByName(String name);

    @Query("SELECT * from product where (:genderCategory IS NOT NULL AND genderCategory IS :genderCategory ) " +
            " AND (:productName IS NOT NULL AND productName LIKE '%' || :productName ||'%' )")
    List<Product> getProductAfterFilter(String productName,String genderCategory);

}
