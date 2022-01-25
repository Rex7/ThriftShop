package com.example.thriftshop.searchcontent;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;

import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.example.thriftshop.R;
import com.example.thriftshop.homecontent.ChildModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class FilterBottomSheet extends BottomSheetDialogFragment implements View.OnClickListener {

    private BottomSheetBehavior bottomSheetBehavior;
    Button men,women,kids,used,unused,rent,filter;
    ArrayList<ChildModel> childModels;
    ArrayList<String> filterOptions=new ArrayList<>();
    Context ctx;
    private static  String TAG="FilterBottomSheet";

    public FilterBottomSheet(Context ctx) {
        this.ctx=ctx;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View v = View.inflate(getContext(), R.layout.bottomsheet, null);
        //all category button initialized
        men=v.findViewById(R.id.men);
        women=v.findViewById(R.id.women);
        kids=v.findViewById(R.id.kids);
        used=v.findViewById(R.id.used);
        unused=v.findViewById(R.id.unused);
        rent=v.findViewById(R.id.rent);
        filter=v.findViewById(R.id.filterButton);



        men.setOnClickListener(this);
        women.setOnClickListener(this);
        kids.setOnClickListener(this);
        unused.setOnClickListener(this);
        used.setOnClickListener(this);
        rent.setOnClickListener(this);

        dialog.setContentView(v);
    }




    @Override
    public void onClick(View view) {
    filter.setText("searching...");
    SearchActivity searchActivity= (SearchActivity) getActivity();
    searchActivity.HandleSearchQuery("j");
    }



    public void setData(ArrayList<ChildModel> populateParentArray) {
        this.childModels=populateParentArray;
    }
    public void updateButtonCount(int count){
       if(filter!=null){
           filter.setText(getResources().getString(R.string.filtered_text,count));
           Log.v(TAG, "updateButtonCount: "+count);
       }
    }


}

