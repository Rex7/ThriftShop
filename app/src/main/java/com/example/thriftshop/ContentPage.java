package com.example.thriftshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ContentPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    RecyclerView recyclerView;
    CategoryRecyclerAdapter recyclerAdapter;
    RecyclerView parentRecycler;
    ParentAdapter parentAdapter;
    SearchView searchView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView nav;
    ImageView hamburgerIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_page);
        hamburgerIcon=findViewById(R.id.hamburger);
        hamburgerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        recyclerView=findViewById(R.id.recycle);
        searchView=findViewById(R.id.searchbar);
        recyclerAdapter=new CategoryRecyclerAdapter(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));
        recyclerView.setAdapter(recyclerAdapter);

        parentRecycler=findViewById(R.id.main_recyclerview);
         parentAdapter=new ParentAdapter(getApplicationContext(),populateParentArray());
        parentRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
       parentRecycler.setAdapter(parentAdapter);
        ViewCompat.setNestedScrollingEnabled(parentRecycler,false);
        parentRecycler.setHasFixedSize(true);
        //drawer
        nav = findViewById(R.id.nav);
        nav.setNavigationItemSelectedListener(this);
        drawerLayout =  findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, null,
                R.string.open_navigation, R.string.close_navigation);


        drawerLayout.addDrawerListener(actionBarDrawerToggle);


        SearchManager searchManager = (SearchManager) getSystemService(getApplicationContext().SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(this,SearchAbleActivity.class)));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                parentAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                parentAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }
    public ArrayList<ParentModel> populateParentArray(){

        String[] featuredProducts={"High heels ","T Shirt ","Jeans","jewels "};
        String[] Explore={"Necklace","Ring","Earring","Bracelet"};
        String[] jewels={"jacket","shoe louis","Saree","black jacket"};

        int[] featuredProduct={R.drawable.highheels,R.drawable.jeans,R.drawable.bag,R.drawable.tshirt};
        int[] explore={R.drawable.necklace,R.drawable.ring,R.drawable.earring,R.drawable.bracelrt};
        int[] jewelsImage={R.drawable.jacketdenim,R.drawable.shoelouis,R.drawable.saree,R.drawable.blackjacket};

        String[] title={"Featured ","Explore","Fresh In trends"};
        ArrayList<ParentModel> parentModel=new ArrayList<>();
        for (int i=0;i<3;i++){
            ArrayList<ChildModel> childModels=new ArrayList<>();
            for(int j=0;j<4;j++){
                switch(i){
                    case 0:
                        childModels.add(new ChildModel(featuredProducts[j],"$ 900",featuredProduct[j]));
                        break;
                    case 1:
                        childModels.add(new ChildModel(Explore[j],"$ 200",explore[j]));
                        break;
                    case 2:
                        childModels.add(new ChildModel(jewels[j],"$ 150",jewelsImage[j]));
                        break;

                }

            }
            parentModel.add(new ParentModel(title[i],childModels));
        }
        return parentModel;


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        DrawerLayout drawer =  findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}