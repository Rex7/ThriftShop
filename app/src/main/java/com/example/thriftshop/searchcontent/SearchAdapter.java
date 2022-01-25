package com.example.thriftshop.searchcontent;

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
import com.example.thriftshop.homecontent.ChildModel;
import com.example.thriftshop.R;

import java.util.ArrayList;

public class SearchAdapter  extends  RecyclerView.Adapter<SearchAdapter.Viewholders> implements Filterable {
    private Context ctx;
    private ArrayList<ChildModel> childModels;
    private ArrayList<ChildModel> filterModel;
    private TransferData transferData;
    FilterBottomSheet filterBottomSheet;
    int count=0;
    public  SearchAdapter(ArrayList<ChildModel> childModels,Context ctx,TransferData data){
        this.ctx=ctx;
        this.childModels=childModels;
        this.filterModel=childModels;
        this.transferData=data;
    }

    @NonNull
    @Override
    public SearchAdapter.Viewholders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.iten_childrow,parent,false);

       return new Viewholders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.Viewholders holder, int position) {
        holder.nameOfProduct.setText(filterModel.get(position).getProductName());
        holder.price.setText(filterModel.get(position).getPrice());
        Glide.with(ctx).load(filterModel.get(position).getIcon()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return filterModel.size();
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


                        if (row.getProductName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                            Log.v("SelectedVal",""+filteredList.size());
                            Log.v("WhoIsMe",""+row.getProductName());
                            Log.v("WhoIsMe",""+row.getPrice());

                        }
                    }



                    filterModel = filteredList;

                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filterModel;
                transferData.setData(filterModel.size());
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                filterModel = (ArrayList<ChildModel>) filterResults.values;
                for (int i=0;i<filterModel.size();i++){
                    Log.v("TAG",""+filterModel.get(i).getProductName());
                }
                notifyDataSetChanged();

            }
        };
    }


}