package com.example.thriftshop.homecontent;

import static android.content.Context.SEARCH_SERVICE;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thriftshop.ContentPage;
import com.example.thriftshop.R;
import com.example.thriftshop.searchcontent.SearchActivity;
import com.example.thriftshop.searchcontent.SearchAppActivity;
import com.example.thriftshop.searchcontent.model.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView parentRecycler;
    ParentAdapter parentAdapter;
    SearchView searchView;
    Context ctx;
    ArrayList<Product> productList;
    public HomeFragment(Context ctx,ArrayList<Product> productList){
        this.productList=productList;
        this.ctx=ctx;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home_content,container,false);
        searchView = view.findViewById(R.id.searchbar);

        parentRecycler = view.findViewById((R.id.main_recyclerview));
        parentAdapter = new ParentAdapter(ctx.getApplicationContext(),productList);
        parentRecycler.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false));
        parentRecycler.setAdapter(parentAdapter);
        ViewCompat.setNestedScrollingEnabled(parentRecycler, false);
        parentRecycler.setHasFixedSize(true);
        SearchManager searchManager = (SearchManager) ctx.getSystemService(SEARCH_SERVICE);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(ctx, SearchActivity.class)));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                parentAdapter.getFilter().filter(query);
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                parentAdapter.getFilter().filter(newText);
                return false;
            }
        });



return view;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       getActivity().findViewById(R.id.progressBar).setVisibility(View.GONE);
        getActivity().findViewById(R.id.bottomview).setVisibility(View.VISIBLE);
    }
}
