package com.example.thriftshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                clearData();
                Intent intent=new Intent(getApplicationContext(),ContentPage.class);
                startActivity(intent);

            }
        });
    }
   void clearData(){
       phoneNumber.setText(" ");
    }
}