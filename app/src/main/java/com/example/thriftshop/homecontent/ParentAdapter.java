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

public class ParentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    Context ctx;
    RecyclerView.RecycledViewPool recycledViewPool;
    ArrayList<ParentModel> parentModel;
    ArrayList<ParentModel> filterModel;
    public ParentAdapter(Context ctx,ArrayList<ParentModel> parentModel){

        this.ctx=ctx;
        this.parentModel=parentModel;
        recycledViewPool=new RecyclerView.RecycledViewPool();
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
           ParentViewHolder viewHolders1=(ParentViewHolder)view;
           viewHolders1.title.setText(parentModel.get(i).getTitle());
           ChildAdapter childAdapter=new ChildAdapter(parentModel.get(i).getChilds(),ctx.getApplicationContext());
           viewHolders1.childRecycler.setRecycledViewPool(recycledViewPool);
           viewHolders1.childRecycler.setLayoutManager(new LinearLayoutManager(ctx.getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
           viewHolders1.childRecycler.setItemAnimator(new DefaultItemAnimator());
           viewHolders1.childRecycler.setAdapter(childAdapter);
       }

    }

    @Override
    public int getItemCount() {
        return parentModel.size();
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
                    ArrayList<ParentModel> filteredList = new ArrayList<>();
                    for (ParentModel row : parentModel) {

                        for (int i=0;i<row.getChilds().size();i++){
                            Log.v("SelectedVal",""+row.getChilds().get(i).getProductName()+" charString "+charString);
                            if (row.getChilds().get(i).getProductName().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(row);
                                Log.v("SelectedVal",""+filteredList.size());
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

                filterModel = (ArrayList<ParentModel>) filterResults.values;

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

}

