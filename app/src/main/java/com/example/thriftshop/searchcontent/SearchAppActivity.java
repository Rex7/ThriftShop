package com.example.thriftshop.searchcontent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.thriftshop.R;

public class SearchAppActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewHistory;
    HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_app);
        Log.v("SearchActivity","called");
        toolbar=findViewById(R.id.toolbar_SearchApp);
        setSupportActionBar(toolbar);
        recyclerViewHistory=findViewById(R.id.recyclview_searchApp);
        historyAdapter = new HistoryAdapter();
        recyclerViewHistory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewHistory.setAdapter(historyAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem searchMenu=menu.findItem(R.id.action_search);

        SearchView searchView= (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.findViewById(androidx.appcompat.R.id.search_plate).setBackgroundColor(Color.TRANSPARENT);

        searchMenu.expandActionView();
        searchView.clearFocus();
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
      return true;
    }
}