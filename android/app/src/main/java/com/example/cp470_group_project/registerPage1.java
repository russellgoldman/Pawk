package com.example.cp470_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class registerPage1 extends AppCompatActivity {

    Button nextButton;
    EditText F1;
    EditText F2;
    EditText F3;
    EditText F4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page1);

        nextButton= findViewById(R.id.nextbttn);
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                validateStringInput();
                validateEmail();
                validatePass();
                confirmPass();
                if (validateStringInput() && validateEmail() && validatePass() && confirmPass()){
                    Intent intent = new Intent(registerPage1.this,registerPage2.class);
                    startActivity(intent);

                }
            }
        });

    }

    private boolean validateStringInput(){
        F1 = findViewById(R.id.F1);
        String inputF1 = F1.getText().toString();
        if (inputF1.matches("")){
            F1.setError("Field Cannot Be Empty");
            return false;
        }
        else{
            F1.setError(null);
            return true;
        }
    }

    private boolean validateEmail(){
        F2 = findViewById(R.id.F2);
        String inputF2 =  F2.getText().toString();
        if (!inputF2.matches("[A-Za-z]{4}[0-9]{4}(@mylaurier.ca)$")){
            F2.setError("Please use a valid Laurier email address");
            return false;
        }
        else{
            F2.setError(null);
            return true;
        }
    }

    private boolean validatePass(){
        F3 = findViewById(R.id.F3);
        String inputF3 = F3.getText().toString();

        if(!inputF3.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")){
            F3.setError("Password too weak");
            return false;
        }
        else{
            F3.setError(null);
            return true;
        }
    }

    private boolean confirmPass(){
        F4 = findViewById(R.id.F4);
        String inputF4 = F4.getText().toString();

        F3 = findViewById(R.id.F3);
        String inputF3 = F3.getText().toString();

        if(!(inputF4 != inputF3)){
            F4.setError("Password does not match");
            return false;
        }
        else{
            F4.setError(null);
            return true;
        }
    }


}
