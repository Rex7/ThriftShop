package com.example.thriftshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.thriftshop.homecontent.HomeFragment;
import com.example.thriftshop.searchcontent.SearchAppActivity;
import com.example.thriftshop.searchcontent.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class ContentPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView hamburgerIcon;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_page);
        bottomNavigationView=findViewById(R.id.bottomview);




        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment(getApplicationContext())).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search_drawer:
                      getSupportFragmentManager().beginTransaction().replace(R.id.container,new SearchFragment(getApplicationContext())).commit();
                        return true;
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment(getApplicationContext())).commit();



                    default:
                        return true;
                }
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
    public void goBackToHomeFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment(getApplicationContext())).commit();
        bottomNavigationView.setSelectedItemId(R.id.home);


    }

}