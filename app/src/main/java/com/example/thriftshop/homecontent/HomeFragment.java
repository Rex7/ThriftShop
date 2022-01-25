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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thriftshop.R;
import com.example.thriftshop.searchcontent.SearchActivity;
import com.example.thriftshop.searchcontent.SearchAppActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView parentRecycler;
    ParentAdapter parentAdapter;
    SearchView searchView;
    Context ctx;
    public HomeFragment(Context ctx){
        this.ctx=ctx;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home_content,container,false);
        searchView = view.findViewById(R.id.searchbar);

        parentRecycler = view.findViewById((R.id.main_recyclerview));
        parentAdapter = new ParentAdapter(ctx.getApplicationContext(), populateParentArray());
        parentRecycler.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false));
        parentRecycler.setAdapter(parentAdapter);
        ViewCompat.setNestedScrollingEnabled(parentRecycler, false);
        parentRecycler.setHasFixedSize(true);
        SearchManager searchManager = (SearchManager) ctx.getSystemService(SEARCH_SERVICE);


        searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(ctx, SearchActivity.class)));
        searchView.setOnClickListener(val -> startActivity(new Intent(ctx.getApplicationContext(), SearchAppActivity.class)));
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
    public ArrayList<ParentModel> populateParentArray() {

        String[] featuredProducts = {"High heels ", "T Shirt ", "Jeans", "jewels "};
        String[] Explore = {"Necklace", "Ring", "Earring", "Bracelet"};
        String[] jewels = {"jacket", "shoe louis", "Saree", "black jacket"};

        int[] featuredProduct = {R.drawable.highheels, R.drawable.jeans, R.drawable.bag, R.drawable.tshirt};
        int[] explore = {R.drawable.necklace, R.drawable.ring, R.drawable.earring, R.drawable.bracelrt};
        int[] jewelsImage = {R.drawable.jacketdenim, R.drawable.shoelouis, R.drawable.saree, R.drawable.blackjacket};

        String[] title = {"Category","Featured ", "Explore", "Fresh In trends"};
        ArrayList<ParentModel> parentModel = new ArrayList<>();
        for (int i = 0; i <=3; i++) {
            ArrayList<ChildModel> childModels = new ArrayList<>();
            for (int j = 0; j <4; j++) {
                switch (i) {
                    case 1:
                        childModels.add(new ChildModel(featuredProducts[j], "$ 900", featuredProduct[j]));
                        break;
                    case 2:
                        childModels.add(new ChildModel(Explore[j], "$ 200", explore[j]));
                        break;
                    case 3:
                        childModels.add(new ChildModel(jewels[j], "$ 150", jewelsImage[j]));
                        break;

                }

            }
            parentModel.add(new ParentModel(title[i], childModels));
        }
        return parentModel;


    }
}
