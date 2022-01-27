package com.example.thriftshop.searchcontent.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class},version = 13,exportSchema = false)
public abstract class ProductImpl  extends RoomDatabase {
    private static volatile ProductImpl productImpl;
    public abstract productDao productDao();
    public static ProductImpl getDatabase(final Context context){
        if(productImpl==null){
        synchronized (ProductImpl.class){
            if (productImpl==null){
                productImpl= Room.databaseBuilder(context.getApplicationContext(),ProductImpl.class,"thrift.db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        }
        return productImpl;
    }

}
