package com.example.popcornandsodaonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    private TextInputEditText textInputPassword;
    private TextInputEditText textInputUsername;
    private Button buttonLogin;
    private ProgressBar progressBar;
    private TextView textViewLoginMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputPassword = findViewById(R.id.textInputEditText_Password_Login);
        textInputUsername = findViewById(R.id.textInputEditText_Username_Login_);
        textViewLoginMessage = findViewById(R.id.loginText);
        progressBar = findViewById(R.id.progress);
        buttonLogin = findViewById(R.id.buttonLogin);

        textViewLoginMessage.setOnClickListener(view -> {
            //Intent intent = new Intent(getApplicationContext(), SignUp.class);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        });

        buttonLogin.setOnClickListener(view -> {
            String username, password;
            username = String.valueOf(textInputUsername.getText());
            password = String.valueOf(textInputPassword.getText());

            if(!username.equals("") && !password.equals("")) {
                progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.post(() -> {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[2];
                    field[0] = "username";
                    field[1] = "password";
                    //Creating array for data
                    String[] data = new String[2];
                    data[0] = username;
                    data[1] = password;

                    PutData putData = new PutData("http://192.168.85.37/loginRegister/login.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.setVisibility(View.GONE);
                            String result = putData.getResult();
                            if(result.equals("Login Success")){
                                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    //End Write and Read data with URL
                });
            } else{
                Toast.makeText(getApplicationContext(), "All Fields Are Required!", Toast.LENGTH_LONG).show();
            }
        });

    }
}