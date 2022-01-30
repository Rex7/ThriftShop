package com.example.thriftshop.homecontent;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thriftshop.R;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder>  {

    Context ctx;
    String[] categoryList={"men","rent","jewels","women","electronics","books","home"};
    public  CategoryRecyclerAdapter(Context ctx){
        this.ctx=ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(categoryList[position]);
    }

    @Override
    public int getItemCount() {
        return categoryList.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        View categoryBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
          title=  itemView.findViewById(R.id.title_row);
          categoryBox=itemView.findViewById(R.id.categoryBox);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            Log.v("CategoryDapter",""+getAdapterPosition());
           // title.setTextColor(Color.CYAN);
           // categoryBox.setBackgroundColor(Color.RED);
        }
    }
}
