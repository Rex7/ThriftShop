package com.example.thriftshop.searchcontent;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thriftshop.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.Objects;

public class FilterBottomSheet extends BottomSheetDialogFragment implements MaterialButtonToggleGroup.OnButtonCheckedListener {

    Button filter;
    String category = "";
    int type = 0;
    Context ctx;
    SearchActivity searchActivity;
    MaterialButtonToggleGroup categooryToggle;
    MaterialButtonToggleGroup typeToggle;
    private final static String TAG = "FilterBottomSheet";
    private String query;
    int size;

    public FilterBottomSheet(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View v = View.inflate(getContext(), R.layout.bottomsheet, null);
        categooryToggle = v.findViewById(R.id.toggleCategory);
        typeToggle = v.findViewById(R.id.typetoggle);
        filter = v.findViewById(R.id.filterButton);
        if (filter!=null){

            filter.setText(getResources().getString(R.string.filtered_text, size));
            Log.v(TAG, "setUpdialog: " + size);
        }
        Objects.requireNonNull(filter).setOnClickListener(view -> dismiss());


        //all category button initialized
        categooryToggle.addOnButtonCheckedListener(this);
        typeToggle.addOnButtonCheckedListener(this);
        dialog.setContentView(v);
    }


    public void setQuery(String query) {
        this.query = query;
    }


    public void updateButtonCount(int count) {
        size = count;
        Log.v("UpdateButton", "" + count);
        if (filter != null) {
            Log.v("UpdateButton", "inside if" + count);
            filter.setText(ctx.getResources().getString(R.string.filtered_text, count));
            Log.v(TAG, "updateButtonCount: " + count);
        } else {
            Log.v("UpdateButton", "inside else" + count);
        }
    }

    @Override
    public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
        searchActivity = (SearchActivity) getActivity();
        Log.v("HandleSearchCategory","old"+query);
        Log.v("HandleSearchCategory","updated"+searchActivity.searchView.getQuery());
        String queryupdated=searchActivity.searchView.getQuery().toString();

        switch (group.getId()) {
            case R.id.toggleCategory:
                if (checkedId == R.id.men) {
                    category = "men";
                    if (type == 0) {
                        searchActivity.handleSearchByCategory(queryupdated, category);
                    } else {
                        searchActivity.handleSearchQueryFilter(queryupdated, category, type);
                        Log.v("FilterBottomSheet", "" + type);
                    }

                } else if (checkedId == R.id.women) {
                    Log.v("FilterBottom", "Category women");
                    category = "women";
                    if (type == 0) {
                        searchActivity.handleSearchByCategory(queryupdated, category);
                    } else {
                        searchActivity.handleSearchQueryFilter(queryupdated, category, type);
                    }

                } else if (checkedId == R.id.kids) {
                    category = "kids";
                    if (type == 0) {
                        searchActivity.handleSearchByCategory(queryupdated, category);
                    } else {
                        searchActivity.handleSearchQueryFilter(queryupdated, category, type);
                    }

                }

                break;
            case R.id.typetoggle:
                if (checkedId == R.id.used) {
                    type = 1;
                    if (category.isEmpty()) {
                        searchActivity.handleSearchByType(queryupdated, type);
                        Log.v("FilterBottomSheet", "Called");
                    } else {
                        searchActivity.handleSearchQueryFilter(queryupdated, category, type);
                        Log.v("FilterBottomSheet", "FullFilterCalled");

                    }
                } else if (checkedId == R.id.unused) {
                    type = 2;
                    if (category.isEmpty()) {
                        searchActivity.handleSearchByType(queryupdated, type);
                    } else {
                        searchActivity.handleSearchQueryFilter(queryupdated, category, type);
                    }
                } else if (checkedId == R.id.rent) {
                    type = 3;
                    if (category.isEmpty()) {
                        searchActivity.handleSearchByType(queryupdated, type);
                    } else {
                        searchActivity.handleSearchQueryFilter(queryupdated, category, type);
                    }

                }
                break;
        }
    }

}

