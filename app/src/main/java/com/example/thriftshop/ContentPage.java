package com.example.thriftshop;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thriftshop.cart.CartFragment;
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
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_page);
        bottomNavigationView = findViewById(R.id.bottomview);
        progressBar = findViewById(R.id.progressBar);

        AsyncTask.execute(() -> {
            productIml = ProductImpl.getDatabase(getApplicationContext());
            productArrayList = (ArrayList<Product>) productIml.productDao().getAllProduct();
            if (productArrayList.size() == 0) {
                productArrayList = dummyData();
                productIml.productDao().addProduct(productArrayList);
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment(getApplicationContext(), productArrayList)).commit();
            Log.v("ContentPage", "" + productArrayList.size());
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.search_drawer:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchFragment(getApplicationContext())).commit();
                    break;

                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment(getApplicationContext(), productArrayList)).commit();
                    break;
                case R.id.cart:
                    startActivity(new Intent(this,CartFragment.class));
                    break;


            }
            return true;
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

        productArrayList.get(0).setItemDescription("Stiletto high heels bought 6 months baack worn just once and best for party and formal events");
        productArrayList.get(1).setItemDescription("It a perfect wwhite colored t shirt to go with any event");
        productArrayList.get(2).setItemDescription("Blue denim bought back an year ago still in fresh condition");
        productArrayList.get(3).setItemDescription("Gold plated in perfect condition worn once");
        productArrayList.get(4).setItemDescription("Necklace made with south indian style with new design");
        productArrayList.get(5).setItemDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Vivamus vitae scelerisque ipsum. Proin id turpis ante. In neque risus, rutrum eu auctor id, luctus vel nulla. C");
        productArrayList.get(6).setItemDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Vivamus vitae scelerisque ipsum. Proin id turpis ante. In neque risus, rutrum eu auctor id, luctus vel nulla. C");
        productArrayList.get(7).setItemDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Vivamus vitae scelerisque ipsum. Proin id turpis ante. In neque risus, rutrum eu auctor id, luctus vel nulla. C");
        productArrayList.get(8).setItemDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Vivamus vitae scelerisque ipsum. Proin id turpis ante. In neque risus, rutrum eu auctor id, luctus vel nulla. C");
        productArrayList.get(9).setItemDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Vivamus vitae scelerisque ipsum. Proin id turpis ante. In neque risus, rutrum eu auctor id, luctus vel nulla. C");

        productArrayList.get(0).setBrandName("Clarke Heels");
        productArrayList.get(1).setBrandName("Peter England");
        productArrayList.get(2).setBrandName("denim");
        productArrayList.get(3).setBrandName("Titan");
        productArrayList.get(4).setBrandName("Tanishq");
        productArrayList.get(5).setBrandName("Tanishq");
        productArrayList.get(6).setBrandName("Tanishq");
        productArrayList.get(7).setBrandName("Swarovski");
        productArrayList.get(8).setBrandName("Tanishq");
        productArrayList.get(9).setBrandName("Nike");

        return productArrayList;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}