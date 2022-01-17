package com.example.thriftshop;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.Viewholders>  {

    Context ctx;
    String[] categoryList={"men","rent","jewels","women","electronics","books","home"};
    public  CategoryRecyclerAdapter(Context ctx){

        this.ctx=ctx;
    }

    @NonNull
    @Override
    public Viewholders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category, parent, false);
        return  new Viewholders(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholders holder, int position) {
        Log.v("PostWho",""+position);
        holder.title.setText(categoryList[position]);
    }

    @Override
    public int getItemCount() {
        return categoryList.length;
    }

    class Viewholders extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        public Viewholders(@NonNull View itemView) {
            super(itemView);
          title=  itemView.findViewById(R.id.title_row);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {




        }
    }
}
