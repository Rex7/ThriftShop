package com.example.thriftshop;

import android.content.Context;

import java.util.ArrayList;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.Viewholders> implements Filterable {
        private  Context ctx;
        private  ArrayList<ChildModel>childModels;
        private ArrayList<ChildModel> filterModel;
        public ChildAdapter(ArrayList<ChildModel> childModels,Context ctx){
            this.ctx=ctx;
            this.childModels=childModels;
            this.filterModel=childModels;
        }


        @NonNull
        @Override
        public Viewholders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.iten_childrow,viewGroup,false);

            return new Viewholders(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Viewholders viewholders, int i) {
            viewholders.nameOfProduct.setText(childModels.get(i).getName());
            viewholders.price.setText(childModels.get(i).getPrice());
            Glide.with(ctx).load(childModels.get(i).getIcon()).into(viewholders.imageView);


        }

        @Override
        public int getItemCount() {
            return filterModel.size();
        }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filterModel = childModels;
                } else {
                    ArrayList<ChildModel> filteredList = new ArrayList<>();
                    for (ChildModel row : childModels) {


                            if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(row);
                                Log.v("SelectedVal",""+filteredList.size());
                                Log.v("TAG",""+row.getName());
                                Log.v("TAG",""+row.getPrice());
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

                filterModel = (ArrayList<ChildModel>) filterResults.values;
               for (int i=0;i<filterModel.size();i++){
                   Log.v("TAG",""+filterModel.get(i).getName());
               }

                notifyDataSetChanged();

            }
        };
    }


    class Viewholders extends RecyclerView.ViewHolder{
            TextView nameOfProduct,price;
            ImageView imageView;

            public Viewholders(@NonNull View itemView) {
                super(itemView);
                nameOfProduct=itemView.findViewById(R.id.nameOfProduct);
                price=itemView.findViewById(R.id.priceOfProduct);
                imageView=itemView.findViewById(R.id.ImageViewVertical);
            }
        }

    }



