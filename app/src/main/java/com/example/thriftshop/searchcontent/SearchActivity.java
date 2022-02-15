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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thriftshop.R;
import com.example.thriftshop.searchcontent.model.Product;
import com.example.thriftshop.searchcontent.model.ProductImpl;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements TransferData, View.OnClickListener {
    RecyclerView recyclerView;
    SearchAdapter searchAdapter;
    ArrayList<Product> filterModel;
    ArrayList<Product> productArrayList;
    FilterBottomSheet filterBottomSheet;
    ProductImpl productimpl;
    ImageView filter;
    TextView filterText;
    Toolbar toolbar;
    String query;
    SearchView searchView;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initializing views
        setContentView(R.layout.activity_search_able);
        recyclerView = findViewById(R.id.recycleView);
        toolbar = findViewById(R.id.toolbar_Search);
        filter = findViewById(R.id.filterIcon);
        filterText = findViewById(R.id.filter);
        filterBottomSheet = new FilterBottomSheet(getApplicationContext());
        setSupportActionBar(toolbar);


        //setting listener
        filter.setOnClickListener(this);
        filterText.setOnClickListener(this);

        //getting database
        productimpl = ProductImpl.getDatabase(getApplicationContext());


        if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {
            query = getIntent().getStringExtra(SearchManager.QUERY);
            filterModel = populateParentArray();
            Log.d("TAG", "onCreateIF: " + query);
            searchAdapter = new SearchAdapter(filterModel, getApplicationContext(), this);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(searchAdapter);

            HandleSearchQuery(query);
        } else {
            query = getIntent().getStringExtra("query");
            filterModel = getProductByCategory(query);
            setDataWithQuery(filterModel);

        }

    }

    public void setDataWithQuery(ArrayList<Product> filterModel) {
        searchAdapter = new SearchAdapter(filterModel, getApplicationContext(), this);
        searchAdapter.setNumberOfProduct(filterModel.size());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(searchAdapter);
    }

    public void HandleSearchQuery(String query) {
        searchAdapter.getFilter().filter(query);

    }

    public void handleSearchQueryFilter(String productName, String category, int type) {

        productArrayList = (ArrayList<Product>) productimpl.productDao().getProductAfterFilter(productName, category, type);
        size = productArrayList.size();
        filterBottomSheet.updateButtonCount(productArrayList.size());
        searchAdapter = new SearchAdapter(productArrayList, getApplicationContext(), this);
        recyclerView.setAdapter(searchAdapter);
        searchAdapter.notifyDataSetChanged();

    }

    public void handleSearchByCategory(String productName, String category) {
        ArrayList<Product> productArrayList = (ArrayList<Product>) productimpl.productDao().getProductByCategoryFilter(productName, category);
        size = productArrayList.size();
        filterBottomSheet.updateButtonCount(productArrayList.size());
        searchAdapter = new SearchAdapter(productArrayList, getApplicationContext(), this);
        recyclerView.setAdapter(searchAdapter);
        searchAdapter.notifyDataSetChanged();
    }

    public void handleSearchByType(String productName, int type) {
        ArrayList<Product> productArrayList = (ArrayList<Product>) productimpl.productDao().getProductByTypeFilter(productName, type);
        size = productArrayList.size();
        filterBottomSheet.updateButtonCount(productArrayList.size());
        searchAdapter = new SearchAdapter(productArrayList, getApplicationContext(), this);
        recyclerView.setAdapter(searchAdapter);
        searchAdapter.notifyDataSetChanged();
    }

    public ArrayList<Product> populateParentArray() {
        return (ArrayList<Product>) productimpl.productDao().getAllProduct();
    }

    public ArrayList<Product> getProductByCategory(String category) {
        return (ArrayList<Product>) productimpl.productDao().getAllProduct(category);
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
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.findViewById(androidx.appcompat.R.id.search_plate).setBackgroundColor(Color.TRANSPARENT);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.v("SearchActivity", "" + query);
                ArrayList<Product> filterModel = (ArrayList<Product>) productimpl.productDao().getProductByName(query);
                setDataWithQuery(filterModel);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        // searchView.setQueryHint("who");
        searchMenu.expandActionView();
        searchView.clearFocus();
        searchView.setQuery(query, false);
        return true;
    }

    @Override
    public void setData(int res) {
        Log.v("setData", "called" + res);
        size = res;
        filterBottomSheet.updateButtonCount(res);
    }

    @Override
    public void onClick(View view) {
        int Id = view.getId();
        if (Id == R.id.filter || Id == R.id.filterIcon) {
            filterBottomSheet.setQuery(query);
            Log.v("SearchActivityLOG", "quick" + query);
            filterBottomSheet.show(getSupportFragmentManager(), "showing");
            filterBottomSheet.updateButtonCount(size);
        }
    }
}
