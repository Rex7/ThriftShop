package com.example.thriftshop.homecontent;

import android.content.Context;

import java.util.ArrayList;


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
import com.example.thriftshop.R;
import com.example.thriftshop.searchcontent.model.Product;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.Viewholders> implements Filterable {
        private final Context ctx;
        private final ArrayList<Product>childModels;
        private ArrayList<Product> filterModel;
        public ChildAdapter(ArrayList<Product> childModels, Context ctx){
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
            viewholders.nameOfProduct.setText(childModels.get(i).getProductName());
            viewholders.price.setText(""+childModels.get(i).getPrice());
            Glide.with(ctx).load(childModels.get(i).getImage()).into(viewholders.imageView);


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
                    ArrayList<Product> filteredList = new ArrayList<>();
                    for (Product product : childModels) {


                            if (product.getProductName().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(product);
                                Log.v("SelectedVal",""+filteredList.size());
                                Log.v("TAG",""+product.getProductName());
                                Log.v("TAG",""+product.getPrice());
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
               for (int i=0;i<filterModel.size();i++){
                   Log.v("TAG",""+filterModel.get(i).getProductName());
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



