package com.example.thriftshop;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.thriftshop.searchcontent.model.Product;

public class DetailActivity extends AppCompatActivity {
    TextView title,brand,shoeSize,condition,itemDesc,price;
    ImageView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Product product=getIntent().getParcelableExtra("detalArray");
        String image= (getIntent().getStringExtra("image"));
        Log.v("SearchAdapterDetailA",""+image);
        int imageId=Integer.parseInt(image);

        title=findViewById(R.id.productName);
        picture=findViewById(R.id.productImage);
        brand=findViewById(R.id.brandName);
        shoeSize=findViewById(R.id.shoeSize);
        condition=findViewById(R.id.conditionOfProduct);
        itemDesc=findViewById(R.id.itemDescription);
        price=findViewById(R.id.priceData);

        //setting the data
        title.setText(product.getProductName());
        if (product.getBrandName()==null) {
            brand.setText("Unknown");
        } else {
            brand.setText(product.getBrandName());
        }
        itemDesc.setText(product.getItemDescription());
        price.setText(getResources().getString(R.string.priceData,product.getPrice()));

        Log.v("SearchAdapterDetailA",getCondition(product.getType()));
       condition.setText(getCondition(product.getType()));

        Glide.with(getApplicationContext()).load(imageId)
                .transition(withCrossFade())
                .into(picture);
    }
    String getCondition(int type){
        String condition = "";
        switch (type){
            case 1:
                condition="Pre-Owned";
                break;
            case 2:
                condition="New";
                break;
            case 3:
                condition="Rent";
                break;

        }
        return condition;
    }



}