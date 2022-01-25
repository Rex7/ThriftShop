package com.example.thriftshop.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.thriftshop.ContentPage;
import com.example.thriftshop.R;

public class LoginActivity extends AppCompatActivity {
    Button otp;
    EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phoneNumber=findViewById(R.id.phoneNumberEdittext);
        otp=findViewById(R.id.otp);
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(), ContentPage.class);
                startActivity(intent);
                clearData();

            }
        });
    }
   void clearData(){
       phoneNumber.setText(" ");
    }
}