package com.example.cp470_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.content.Intent;

import android.os.Bundle;

public class launchActivity extends AppCompatActivity {

    private Button loginButton;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When login button is clicked, the next activity (Login Screen) should start
                Intent intent = new Intent(launchActivity.this,loginActivity.class);
                startActivity(intent);
            }
        });

        signupButton = findViewById(R.id.signUpButton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When login button is clicked, the next activity (Login Screen) should start
                Intent intent = new Intent(launchActivity.this,registerPage1.class);
                startActivity(intent);
            }
        });
    }
}
