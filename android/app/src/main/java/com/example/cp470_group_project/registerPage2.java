package com.example.cp470_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class registerPage2 extends AppCompatActivity {

    Button nextButton;
    EditText F1;
    EditText F2;
    EditText F4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page2);

        nextButton= findViewById(R.id.nextbttn);
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(validateFields()){
                    Intent intent = new Intent(registerPage2.this,registerPage3.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateFields(){
        F1 = findViewById(R.id.F1);
        F2 = findViewById(R.id.F2);
        F4 = findViewById(R.id.F4);

        String inputF1 = F1.getText().toString();
        String inputF2 = F2.getText().toString();
        String inputF4 = F4.getText().toString();


        if (inputF1.matches("")){
            F1.setError("Field cannot be empty");
            return false;
        }
        if (inputF2.matches("")){
            F2.setError("Field cannot be empty");
            return false;
        }
        if (inputF4.matches("")){
            F4.setError("Field cannot be empty");
            return false;
        }
        else{
            F1.setError(null);
            F2.setError(null);
            F4.setError(null);
            return true;
        }
    }
}
