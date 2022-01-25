package com.example.thriftshop.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thriftshop.R;

public class VerificationActivity extends AppCompatActivity implements TextWatcher {
    TextView resendOtp;
    ImageView backArrow;
    EditText first,second,third,four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        resendOtp=findViewById(R.id.resendOtp);
        backArrow=findViewById(R.id.backArrow);
        first=findViewById(R.id.optnumber1);
        second=findViewById(R.id.optnumber2);
        third=findViewById(R.id.optnumber3);
        four=findViewById(R.id.optnumber4);
        first.addTextChangedListener(this);
        second.addTextChangedListener(this);
        third.addTextChangedListener(this);
        four.addTextChangedListener(this);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });
        resendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext()," resending otp ",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        TextView text = (TextView)getCurrentFocus();

        if (text != null && text.length() > 0)
        {
            View next = text.focusSearch(View.FOCUS_RIGHT); // or FOCUS_FORWARD
            if (next != null)
                next.requestFocus();


        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}