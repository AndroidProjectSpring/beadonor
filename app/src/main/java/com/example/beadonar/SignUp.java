package com.example.beadonar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SignUp extends AppCompatActivity {
    EditText name,age,mobile,gender,email,password,confirmPassword;
    TextView loginRedirectTextView;
    Button signupButton;

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        loginRedirectTextView=findViewById(R.id.loginText);
        signupButton =findViewById(R.id.signUp_btn);


        loginRedirectTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Login.class);
                startActivity(i);
                finish();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}