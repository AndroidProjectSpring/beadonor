package com.example.beadonar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



public class Login extends AppCompatActivity {

    EditText username,password;
    Button login;
    TextView signUp;
    ProgressBar progressBar;



    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.username_edit_text);
        password=findViewById(R.id.password_edit_text);
        login=findViewById(R.id.login_btn);
        signUp=findViewById(R.id.signupText);
        progressBar=findViewById(R.id.progress_bar);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailStr = username.getText().toString();
                String passwordStr = password.getText().toString();

                if( emailStr.length() ==0 || passwordStr.length() ==0){
                    Toast.makeText(Login.this, "Fill All Field's", Toast.LENGTH_SHORT).show();
                    return;
                }

                //check user  exist or not authentication
                progressBar.setVisibility(View.VISIBLE);

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SignUp.class);
                startActivity(i);
            }
        });
    }

    public void goToHomePage(){

    }
}