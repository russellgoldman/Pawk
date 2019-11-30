package com.example.cp470_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;
import android.widget.RatingBar;
import android.widget.Toast;

import org.w3c.dom.Text;

public class loginActivity extends AppCompatActivity {

    private String ACTIVITY_NAME = "loginActivity";

    Button loginButton;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // REMOVE THIS FROM LOGIN AFTER COURSE PAGES + RATING STUFF ARE IMPLEMENTED
        /* just to check if my dialog stuff works for RATING COURSE DIALOG*/
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(ACTIVITY_NAME,"Clicked Log In Button");
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.rate_course_dialog);
                TextView text = (TextView)dialog.findViewById(R.id.rateCourseTextView);
                Button submitCourseRating = (Button)dialog.findViewById(R.id.submitCourseRatingButton);
                final RatingBar ratingBar = (RatingBar)dialog.findViewById(R.id.ratingBar);
                submitCourseRating.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String totalStars = "Rating: " + ratingBar.getRating();
                        Log.i(ACTIVITY_NAME,"Total Stars: " + totalStars);

                        // THIS LINE MAKES A TOAST..  FOR RATINGS - TOAST.PNG
                        Toast.makeText(getApplicationContext(),totalStars, Toast.LENGTH_LONG).show();

                        // Closes the Dialog when submit is pressed
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }
}
