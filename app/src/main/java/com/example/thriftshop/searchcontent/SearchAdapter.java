package com.example.thriftshop.searchcontent;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.thriftshop.DetailActivity;
import com.example.thriftshop.R;
import com.example.thriftshop.searchcontent.model.Product;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.Viewholders> implements Filterable {
    private final Context ctx;
    private final ArrayList<Product> childModels;
    private ArrayList<Product> filterModel;
    private final TransferData transferData;


    public SearchAdapter(ArrayList<Product> childModels, Context ctx, TransferData data) {
        this.ctx = ctx;
        this.childModels = childModels;
        this.filterModel = childModels;
        this.transferData = data;
    }

    @NonNull
    @Override
    public SearchAdapter.Viewholders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_childrow, parent, false);
        return new Viewholders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.Viewholders holder, int position) {
        holder.nameOfProduct.setText(filterModel.get(position).getProductName());
        holder.price.setText(ctx.getResources().getString(R.string.priceData, filterModel.get(position).getPrice()));
        Log.v("SearchAdapterA", "" + filterModel.get(position).getImage());
        Glide.with(ctx).load(filterModel.get(position)
                .getImage())
                .transition(withCrossFade())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_background)
                        .error(R.drawable.ic_background)
                )
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return filterModel.size();
    }


    class Viewholders extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameOfProduct, price;
        ImageView imageView;

        public Viewholders(@NonNull View itemView) {
            super(itemView);
            nameOfProduct = itemView.findViewById(R.id.nameOfProduct);
            price = itemView.findViewById(R.id.priceOfProduct);
            imageView = itemView.findViewById(R.id.ImageViewVertical);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent detailIntent = new Intent(ctx.getApplicationContext(), DetailActivity.class);
            int position = getAdapterPosition();
            Product product = filterModel.get(position);

            detailIntent.putExtra("detalArray", product);
            detailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            detailIntent.putExtra("image", String.valueOf(product.getImage()));
            SearchActivity searchActivity = (SearchActivity) ctx;
            ActivityOptionsCompat optionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(searchActivity, imageView, "imageTransition");
            ctx.startActivity(detailIntent, optionsCompat.toBundle());
            Log.v("SearchAdapter", "onClick" + product.getProductName());
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
                    ArrayList<Product> filteredList = new ArrayList<>();
                    if (charSequence.equals("men")) {
                        for (Product productRow : childModels) {

                            if (productRow.getGenderCategory().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(productRow);

                            }
                        }
                    } else {
                        for (Product productRow : childModels) {


                            if (productRow.getProductName().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(productRow);

                            }
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
                filterModel = (ArrayList<Product>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void setNumberOfProduct(int numberOfProduct) {
        transferData.setData(numberOfProduct);
    }

}

