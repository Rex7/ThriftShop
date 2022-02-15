package com.example.thriftshop.homecontent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thriftshop.R;
import com.example.thriftshop.searchcontent.SearchActivity;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder>  {

    Context ctx;
    String[] categoryList={"All","men","rent","jewels","women","electronics","books","home"};
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
       if(position==0){
           holder.title.setText(categoryList[position]);
           holder.title.setTextColor(ctx.getResources().getColor(R.color.white));
           Drawable buttonDrawable = holder.categoryBox.getBackground();
           buttonDrawable = DrawableCompat.wrap(buttonDrawable);
           //the color is a direct color int and not a color resource
           DrawableCompat.setTint(buttonDrawable, ctx.getResources().getColor(R.color.button_color));
           holder.categoryBox.setBackground(buttonDrawable);
       }
       else {
           holder.title.setText(categoryList[position]);
       }
    }

    @Override
    public int getItemCount() {
        return categoryList.length;
    }

     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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

            Intent intent=new Intent(ctx.getApplicationContext(), SearchActivity.class);
            String category=categoryList[getAdapterPosition()];
            Log.v("CategoryDapter",""+getAdapterPosition());

            intent.putExtra("query",category);
            Log.v("CategoryDapter",""+category);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(intent);
           // title.setTextColor(Color.CYAN);
           // categoryBox.setBackgroundColor(Color.RED);
        }
    }
}
