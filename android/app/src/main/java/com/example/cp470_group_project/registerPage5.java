package com.example.cp470_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class registerPage5 extends AppCompatActivity {

    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page5);

        nextButton= findViewById(R.id.nextbttn);
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast toast = Toast.makeText(registerPage5.this, "Success!", Toast.LENGTH_LONG);
                toast.show();
                Intent intent = new Intent(registerPage5.this, Dashboard.class);
                startActivity(intent);
            }
        });

    }
}
