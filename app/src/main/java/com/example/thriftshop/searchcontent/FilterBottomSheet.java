package com.example.thriftshop.searchcontent;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.thriftshop.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButtonToggleGroup;

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

    public FilterBottomSheet(Context ctx) {
        this.ctx = ctx;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View v = View.inflate(getContext(), R.layout.bottomsheet, null);
        categooryToggle = v.findViewById(R.id.toggleCategory);
        typeToggle = v.findViewById(R.id.typetoggle);
        filter = v.findViewById(R.id.filterButton);


        //all category button initialized
        categooryToggle.addOnButtonCheckedListener(this);
        typeToggle.addOnButtonCheckedListener(this);
        dialog.setContentView(v);
    }


    public void setQuery(String query) {
        this.query = query;
    }


    public void updateButtonCount(int count) {
        Log.v("UpdateButton", "" + count);
        if (filter != null) {
            Log.v("UpdateButton", "inside if" + count);
            filter.setText(getResources().getString(R.string.filtered_text, count));
            Log.v(TAG, "updateButtonCount: " + count);
        }
    }

    @Override
    public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
        searchActivity = (SearchActivity) getActivity();

        switch (group.getId()) {
            case R.id.toggleCategory:
                if (checkedId == R.id.men) {
                    category = "men";
                    if (type == 0) {
                        searchActivity.handleSearchByCategory(query, category);
                    } else {
                        searchActivity.handleSearchQueryFilter(query, category, type);
                        Log.v("FilterBottomSheet", "" + type);
                    }

                } else if (checkedId == R.id.women) {
                    Log.v("FilterBottom", "Category women");
                    category = "women";
                    if (type == 0) {
                        searchActivity.handleSearchByCategory(query, category);
                    } else {
                        searchActivity.handleSearchQueryFilter(query, category, type);
                    }

                } else if (checkedId == R.id.kids) {
                    category = "kids";
                    if (type == 0) {
                        searchActivity.handleSearchByCategory(query, category);
                    } else {
                        searchActivity.handleSearchQueryFilter(query, category, type);
                    }

                }

                break;
            case R.id.typetoggle:
                if (checkedId == R.id.used) {
                    type = 1;
                    if (category.isEmpty()) {
                        searchActivity.handleSearchByType(query, type);
                        Log.v("FilterBottomSheet", "Called");
                    } else {
                        searchActivity.handleSearchQueryFilter(query, category, type);
                        Log.v("FilterBottomSheet", "FullFilterCalled");

                    }
                } else if (checkedId == R.id.unused) {
                    type = 2;
                    if (category.isEmpty()) {
                        searchActivity.handleSearchByType(query, type);
                    } else {
                        searchActivity.handleSearchQueryFilter(query, category, type);
                    }
                } else if (checkedId == R.id.rent) {
                    type = 3;
                    if (category.isEmpty()) {
                        searchActivity.handleSearchByType(query, type);
                    } else {
                        searchActivity.handleSearchQueryFilter(query, category, type);
                    }

                }
                break;
        }
    }

}

