package com.example.thriftshop;

import android.content.Context;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ViewHolders> implements Filterable {
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
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.parent_row,viewGroup,false);
        return new ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders viewHolders, int i) {
        viewHolders.title.setText(parentModel.get(i).getTitle());
        ChildAdapter childAdapter=new ChildAdapter(parentModel.get(i).getChilds(),ctx.getApplicationContext());
        viewHolders.childRecycler.setRecycledViewPool(recycledViewPool);
        viewHolders.childRecycler.setLayoutManager(new LinearLayoutManager(ctx.getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        viewHolders.childRecycler.setItemAnimator(new DefaultItemAnimator());
        viewHolders.childRecycler.setAdapter(childAdapter);

    }

    @Override
    public int getItemCount() {
        return parentModel.size();
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
                            Log.v("SelectedVal",""+row.getChilds().get(i).getName()+" charString "+charString);
                            if (row.getChilds().get(i).getName().toLowerCase().contains(charString.toLowerCase())) {
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

    class  ViewHolders extends RecyclerView.ViewHolder{
        RecyclerView childRecycler;
        TextView title;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            childRecycler=itemView.findViewById(R.id.childRecycler);
            title=itemView.findViewById(R.id.title_parent);
        }

    }

}

