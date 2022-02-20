package com.example.thriftshop.cart;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thriftshop.R;
import com.example.thriftshop.searchcontent.model.Product;
import com.example.thriftshop.searchcontent.model.ProductImpl;

import java.util.ArrayList;

public class CartFragment  extends AppCompatActivity {
    Context context;
    ProductImpl productIml;
    RecyclerView recyclerView;
    ArrayList<Product> productArrayList;
    CartAdapter cartAdapter;
    ImageView backArrowCart;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_cart_section);
        backArrowCart=findViewById(R.id.backArrowCart);
        AsyncTask.execute(() -> {
            productIml = ProductImpl.getDatabase(getApplicationContext());
            productArrayList = (ArrayList<Product>) productIml.productDao().getAllProduct();
            cartAdapter=new CartAdapter(getApplicationContext(),productArrayList);
            recyclerView=findViewById(R.id.cartList);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setAdapter(cartAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        });

        backArrowCart.setOnClickListener((windows)->{
           onBackPressed();
        });
    }
}
