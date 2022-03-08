package com.example.popcornandsodaonline.ui;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popcornandsodaonline.adapters.PutData;
import com.example.popcornandsodaonline.R;
import com.example.popcornandsodaonline.database.ConnectionDb;
import com.google.android.material.textfield.TextInputEditText;

public class SignUp extends AppCompatActivity {

    private TextInputEditText textInputName;
    private TextInputEditText textInputUsername;
    private TextInputEditText textInputPassword;
    private TextInputEditText textInputEmail;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textInputPassword = findViewById(R.id.textInputEditText_Password_SignUp);
        textInputEmail = findViewById(R.id.textInputEditText_Email_SignUp);
        textInputName = findViewById(R.id.textInputEditText_Name_SignUp);
        textInputUsername = findViewById(R.id.textInputEditText_Username_SignUp);
        TextView textViewSignUpMessage = findViewById(R.id.signup_message);
        progressBar = findViewById(R.id.progress);
        Button buttonSignup = findViewById(R.id.button_signup);

        textViewSignUpMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, username, password, email;
                name = String.valueOf(textInputName.getText());
                username = String.valueOf(textInputUsername.getText());
                password = String.valueOf(textInputPassword.getText());
                email = String.valueOf(textInputEmail.getText());

                if(!name.equals("") && !username.equals("") && !password.equals("") && !email.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "name";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = name;
                            data[1] = username;
                            data[2] = password;
                            data[3] = email;
                            PutData putData = new PutData(ConnectionDb.CONECTIONIP + ConnectionDb.PHP_DIRECTORY + ConnectionDb.PHP_SIGNUP_FILE, "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                } else{
                    Toast.makeText(getApplicationContext(), "All Fields Are Required!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}