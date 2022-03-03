package com.example.popcornandsodaonline;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SignUp extends AppCompatActivity {

    private EditText textInputName;
    EditText textInputUsername;
    EditText textInputPassword;
    EditText textInputEmail;

    Button buttonSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        

        textInputName.findViewById(R.id.textInputEditText_Name);
        textInputUsername.findViewById(R.id.textInputEditText_Username);
        textInputPassword.findViewById(R.id.textInputEditText_Password);
        textInputEmail.findViewById(R.id.textInputEditText_Email);


        buttonSignup.findViewById(R.id.button_signup);

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, username, password, email;
                name = String.valueOf(textInputName.getText());
                username = String.valueOf(textInputUsername.getText());
                password = String.valueOf(textInputPassword.getText());
                email = String.valueOf(textInputEmail.getText());

                if(!name.equals("") && !username.equals("") && !password.equals("") && !email.equals("")) {
                    //Start ProgressBar first (Set visibility VISIBLE)
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
                            PutData putData = new PutData("http://192.168.85.37/loginRegister/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                } else{
                    Toast.makeText(getApplicationContext(), "All Fields Are Required!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}