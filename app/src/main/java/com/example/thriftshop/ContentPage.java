package com.example.thriftshop;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thriftshop.homecontent.HomeFragment;
import com.example.thriftshop.searchcontent.SearchAppActivity;
import com.example.thriftshop.searchcontent.SearchFragment;
import com.example.thriftshop.searchcontent.model.Product;
import com.example.thriftshop.searchcontent.model.ProductImpl;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ContentPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    ProductImpl productIml;
    ArrayList<Product> productArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_page);
        bottomNavigationView = findViewById(R.id.bottomview);

        //dummyData();


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                productIml = ProductImpl.getDatabase(getApplicationContext());
                productArrayList = (ArrayList<Product>) productIml.productDao().getAllProduct();
                Log.v("ContentPage",""+productArrayList.size());
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment(getApplicationContext(), productArrayList)).commit();

            }
        });


        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.search_drawer:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchFragment(getApplicationContext())).commit();
                    return true;
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment(getApplicationContext(), productArrayList)).commit();


                default:
                    return true;
            }
        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search_drawer) {
            startActivity(new Intent(getApplicationContext(), SearchAppActivity.class));
        }

        return true;
    }

    public void goBackToHomeFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment(getApplicationContext(), productArrayList)).commit();
        bottomNavigationView.setSelectedItemId(R.id.home);


    }

    public ArrayList<Product> dummyData() {
        ArrayList<Product> productArrayList = new ArrayList<>();
        productArrayList.add(new Product("Stiletto high heel", 80, "women", R.drawable.highheels, 3));
        productArrayList.add(new Product("White tshirt", 80, "men", R.drawable.tshirt, 1));
        productArrayList.add(new Product("casual denim", 80, "men", R.drawable.jacketdenim, 1));
        productArrayList.add(new Product("Gold plated", 80, "women", R.drawable.necklace, 2));
        productArrayList.add(new Product("South Indian Necklace", 80, "women", R.drawable.necklace, 2));
        productArrayList.add(new Product("Ring", 80, "women", R.drawable.ring, 3));
        productArrayList.add(new Product("gold earring", 80, "women", R.drawable.earring, 3));
        productArrayList.add(new Product("Crystal Bracelet", 1500, "women", R.drawable.bracelrt, 1));
        productArrayList.add(new Product("denim jacket", 5000, "men", R.drawable.jacketdenim, 1));
        productArrayList.add(new Product("Nike Sports special", 5000, "men", R.drawable.shoelouis, 1));
        productIml.productDao().addProduct(productArrayList);
        return productArrayList;

    }

}