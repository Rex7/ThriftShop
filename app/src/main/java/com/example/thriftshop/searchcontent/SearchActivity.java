package com.example.thriftshop.searchcontent;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thriftshop.homecontent.ChildModel;
import com.example.thriftshop.R;
import com.example.thriftshop.searchcontent.model.Product;
import com.example.thriftshop.searchcontent.model.ProductImpl;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements  TransferData{
    RecyclerView recyclerView;
    SearchAdapter searchAdapter;
    ArrayList<Product> filterModel;
    BottomSheetBehavior bottomSheetBehavior;
    FilterBottomSheet filterBottomSheet;
    ProductImpl productimpl;
    ImageView filter;
    Toolbar toolbar;
    String query;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_able);
        recyclerView = findViewById(R.id.recycleView);
        toolbar = findViewById(R.id.toolbar_Search);
        setSupportActionBar(toolbar);
        productimpl=ProductImpl.getDatabase(getApplicationContext());
        filter=findViewById(R.id.filterIcon);
        filterBottomSheet =new FilterBottomSheet(getApplicationContext());
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                filterBottomSheet.setQuery(query);
                filterBottomSheet.show(getSupportFragmentManager(),"showing");
            }
        });

        filterModel = populateParentArray();
        searchAdapter = new SearchAdapter(filterModel, getApplicationContext(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(searchAdapter);
        if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {
             query = getIntent().getStringExtra(SearchManager.QUERY);
            Log.d("TAG", "onCreate: " + query);

           HandleSearchQuery(query);
        }
    }

    public int HandleSearchQuery(String query) {
        searchAdapter.getFilter().filter(query);
        return  size;
    }
    public void  handleSearchQueryFilter(String productName,String category,int type){

       ArrayList<Product> productArrayList= (ArrayList<Product>) productimpl.productDao().getProductAfterFilter(query,category,type);
       Log.v("SearchActivity",""+productArrayList.size());
       filterBottomSheet.updateButtonCount(productArrayList.size());
        Log.v("SearchActivity","handleSearchQueryFilter");
         searchAdapter=new SearchAdapter(productArrayList,getApplicationContext(),this);
         recyclerView.setAdapter(searchAdapter);
         searchAdapter.notifyDataSetChanged();

    }
    public  void handleSearchByCategory(String productName,String category){
        ArrayList<Product> productArrayList= (ArrayList<Product>) productimpl.productDao().getProductByCategoryFilter(query,category);
        Log.v("SearchActivity","handleSearchByCategory"+productArrayList.size());
        filterBottomSheet.updateButtonCount(productArrayList.size());
        searchAdapter=new SearchAdapter(productArrayList,getApplicationContext(),this);
        recyclerView.setAdapter(searchAdapter);
        searchAdapter.notifyDataSetChanged();
    }
    public void handleSearchByType(String productName,int type){
        ArrayList<Product> productArrayList= (ArrayList<Product>) productimpl.productDao().getProductByTypeFilter(query,type);
        Log.v("SearchActivity","handleSearchByType"+productArrayList.size());
        filterBottomSheet.updateButtonCount(productArrayList.size());
        searchAdapter=new SearchAdapter(productArrayList,getApplicationContext(),this);
        recyclerView.setAdapter(searchAdapter);
        searchAdapter.notifyDataSetChanged();
    }


    public ArrayList<Product> populateParentArray() {

  ArrayList<Product> productArrayList= (ArrayList<Product>) productimpl.productDao().getAllProduct();
  return productArrayList;



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.searchmenu, menu);
        MenuItem searchMenu = menu.findItem(R.id.action_search);
        searchMenu.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                onBackPressed();
                return false;
            }
        });
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
       searchView.findViewById(androidx.appcompat.R.id.search_plate).setBackgroundColor(Color.TRANSPARENT);
        searchView.setQueryHint("who");
        searchMenu.expandActionView();
        searchView.clearFocus();
        searchView.setQuery(query, false);


        return true;
    }

    @Override
    public void setData(int res) {
   Log.v("setData","called"+res);
filterBottomSheet.updateButtonCount(res);
    }
}
