package com.example.thriftshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SearchApp extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_app);
        toolbar=findViewById(R.id.toolbar_SearchApp);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem searchMenu=menu.findItem(R.id.action_search);
        searchMenu.expandActionView();
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