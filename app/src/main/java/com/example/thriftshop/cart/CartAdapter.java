package com.example.thriftshop.cart;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.thriftshop.R;
import com.example.thriftshop.searchcontent.model.Product;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Viewholder> {
    ArrayList<Product> productArrayList;
    Context context;

    public CartAdapter(Context context, ArrayList<Product> productArrayList) {
        this.productArrayList=productArrayList;
        this.context=context;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_single, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.productName.setText(productArrayList.get(position).getProductName());
        holder.price.setText(""+productArrayList.get(position).getPrice());
        Glide.with(context).load(productArrayList.get(position).getImage())
                .transition(withCrossFade())
                .into(holder.cartImage);


    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {
        TextView productName,price;
        ImageView cartImage;

         public Viewholder(@NonNull View itemView) {
             super(itemView);
             productName=itemView.findViewById(R.id.titleTextCart);
             price=itemView.findViewById(R.id.cart_price);
             cartImage=itemView.findViewById(R.id.cart_image);

         }
     }
}
