package com.example.thriftshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class SearchAbleActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SearchAdapter searchAdapter;
    ArrayList<ChildModel> filterModel;

    private static final Object TAG ="Search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_able);
        recyclerView=findViewById(R.id.recycleView);

       filterModel=populateParentArray();
        searchAdapter=new SearchAdapter(filterModel,getApplicationContext());
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(searchAdapter);
        if(Intent.ACTION_SEARCH.equals(getIntent().getAction())) {
            String query = getIntent().getStringExtra(SearchManager.QUERY);
            Log.d("TAG", "onCreate: " + query);

            HandleSearchQuery(query);
        }
    }

    private void HandleSearchQuery(String query) {
        searchAdapter.getFilter().filter(query);
    }
    public ArrayList<ChildModel> populateParentArray(){
        ArrayList<ChildModel> childModels=new ArrayList<>();

        childModels.add(new ChildModel("High heels","$100",R.drawable.highheels));
        childModels.add(new ChildModel("T Shirt ","$250",R.drawable.tshirt));
        childModels.add(new ChildModel("jeans","$80",R.drawable.jeans));
        childModels.add(new ChildModel("jewels","$180",R.drawable.jewels));
        childModels.add(new ChildModel("Necklace","$100",R.drawable.necklace));
        childModels.add(new ChildModel("Ring","$100",R.drawable.ring));
        childModels.add(new ChildModel("Earring","$100",R.drawable.earring));
        childModels.add(new ChildModel("Bracelet","$100",R.drawable.bracelrt));
        childModels.add(new ChildModel("jacket denim","$100",R.drawable.jacketdenim));
        childModels.add(new ChildModel("Shoe louis","$100",R.drawable.shoelouis));
        childModels.add(new ChildModel("Saree","$100",R.drawable.saree));
        childModels.add(new ChildModel("black jacket","$100",R.drawable.blackjacket));



return childModels;

        }



    }
