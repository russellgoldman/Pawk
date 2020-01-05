package com.example.cp470_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
/**<h1>Register Page 5</h1>
 * <p>
 *     <b>Description: </b>User's Spring Term
 * </p>
 * <p>
 *     <b>Use Case: </b>User can enter up to 5 courses they are taking in that term which will be inserted into a JSON object for personalization.
 * </p>
 **/
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
/**<h1>Register Page 1</h1>
 * <p>
 *     <b>Description: </b>
 * </p>
 * <p>
 *     <b>Use Case: </b>
 * </p>
 **/