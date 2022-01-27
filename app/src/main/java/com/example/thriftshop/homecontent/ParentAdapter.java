package com.example.thriftshop.homecontent;

import android.content.Context;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;



import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thriftshop.R;
import com.example.thriftshop.searchcontent.model.Product;

public class ParentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    Context ctx;
    RecyclerView.RecycledViewPool recycledViewPool;
    ArrayList<Product> parentModel;
    ArrayList<Product> filterModel;
    ArrayList<String> subTitle=new ArrayList<>();
    public ParentAdapter(Context ctx,ArrayList<Product> parentModel){

        this.ctx=ctx;
        this.parentModel=parentModel;
        recycledViewPool=new RecyclerView.RecycledViewPool();
        setSubTitle();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if(i==101){
        view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_cat_layout,viewGroup,false);
            return  new CategoryViewholder( view);
        }
        else {
             view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.parent_row,viewGroup,false);
            return new ParentViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder view, int i) {
       if(i==0){
           CategoryViewholder viewHolders2=(CategoryViewholder)view;
           CategoryRecyclerAdapter childAdapter=new CategoryRecyclerAdapter(ctx.getApplicationContext());
           viewHolders2.title.setText(ctx.getResources().getString(R.string.categories));
           viewHolders2.categoryRecycle.setRecycledViewPool(recycledViewPool);
           viewHolders2.categoryRecycle.setLayoutManager(new LinearLayoutManager(ctx.getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
           viewHolders2.categoryRecycle.setItemAnimator(new DefaultItemAnimator());
           viewHolders2.categoryRecycle.setAdapter(childAdapter);

       }else {
           Log.v("onBindViewHolder",""+i);
           ParentViewHolder viewHolders1=(ParentViewHolder)view;
           viewHolders1.title.setText(subTitle.get(i-1));
           ChildAdapter childAdapter=new ChildAdapter(parentModel,ctx.getApplicationContext());
           viewHolders1.childRecycler.setRecycledViewPool(recycledViewPool);
           viewHolders1.childRecycler.setLayoutManager(new LinearLayoutManager(ctx.getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
           viewHolders1.childRecycler.setItemAnimator(new DefaultItemAnimator());
           viewHolders1.childRecycler.setAdapter(childAdapter);
       }

    }

    @Override
    public int getItemCount() {
        return subTitle.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        Log.v("ParentAdapter",""+position);
        if(position==0){
            return 101;
        }
        else{
            return position;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filterModel = parentModel;
                } else {
                    ArrayList<Product> filteredList = new ArrayList<>();
                    for (Product product : parentModel) {

                        for (int i=0;i<parentModel.size();i++){
                            if (product.getProductName().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(product);
                            }
                        }

                    }

                    filterModel = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterModel;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                filterModel = (ArrayList<Product>) filterResults.values;

            }
        };
    }

    class  ParentViewHolder extends RecyclerView.ViewHolder{
        RecyclerView childRecycler;
        TextView title;

        public ParentViewHolder(@NonNull View itemView) {
            super(itemView);
            childRecycler=itemView.findViewById(R.id.childRecycler);
            title=itemView.findViewById(R.id.title_parent);
        }

    }
    class  CategoryViewholder extends  RecyclerView.ViewHolder{
RecyclerView categoryRecycle;
        TextView title;

        public CategoryViewholder(@NonNull View itemView) {
            super(itemView);
            categoryRecycle=itemView.findViewById(R.id.categoryRecycle);
            title=itemView.findViewById(R.id.title_parent);
        }
    }
    public void setSubTitle(){
        subTitle.add("Category");
        subTitle.add("Featured");
        subTitle.add("Fresh In trends");
    }

}

