package com.example.cp470_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.app.Dialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RatingBar;
import android.widget.Toast;

import org.w3c.dom.Text;

public class loginActivity extends AppCompatActivity {

    private String ACTIVITY_NAME = "loginActivity";

    Button loginButton;
    TextView register;

    EditText email;
    EditText password;

    String user = "Mawl4700@mylaurier.ca";
    String pass = "pawkCP470*";



    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateUser()){
                    Intent intent = new Intent(loginActivity.this,Dashboard.class);
                    startActivity(intent);
                }
//                Log.i(ACTIVITY_NAME,"Clicked Log In Button");
//                final Dialog dialog = new Dialog(context);
//                dialog.setContentView(R.layout.rate_course_dialog);
//                TextView text = (TextView)dialog.findViewById(R.id.rateCourseTextView);
//                Button submitCourseRating = (Button)dialog.findViewById(R.id.submitCourseRatingButton);
//                final RatingBar ratingBar = (RatingBar)dialog.findViewById(R.id.ratingBar);
//                submitCourseRating.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String totalStars = "Rating: " + ratingBar.getRating();
//                        Log.i(ACTIVITY_NAME,"Total Stars: " + totalStars);
//
//                        // THIS LINE MAKES A TOAST..  FOR RATINGS - TOAST.PNG
//                        Toast.makeText(getApplicationContext(),totalStars, Toast.LENGTH_LONG).show();
//
//                        // Closes the Dialog when submit is pressed
//                        dialog.dismiss();
//                    }
//                });
//                dialog.show();
            }
        });

        register = findViewById(R.id.reg_bttn);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(loginActivity.this,registerPage1.class);
                startActivity(intent);
            }
        });

    }

    private boolean validateUser(){
        email = findViewById(R.id.username);
        password = findViewById(R.id.password);

        String userInput = String.valueOf(email.getText());
        String passwordInput = String.valueOf(password.getText());


        if((!userInput.equals(user)) || (!passwordInput.equals(pass))){
            email.setError("Wrong Email");
            password.setError("Incorrect Password");
            return false;
        }
        else{
            email.setError(null);
            password.setError(null);
            return true;
        }
    }

}
