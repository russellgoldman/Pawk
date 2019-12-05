package com.example.cp470_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**<h1>Register Page 4</h1>
 * <p>
 *     <b>Description: </b>User's Winter Term
 * </p>
 * <p>
 *     <b>Use Case: </b>User can enter up to 5 courses they are taking in that term which will be inserted into a JSON object for personalization.
 * </p>
 **/
public class registerPage4 extends AppCompatActivity {

    Button nextButton;
    EditText F1;
    EditText F2;
    EditText F3;
    EditText F4;
    EditText F5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page4);

        nextButton= findViewById(R.id.nextbttn);
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast toast = Toast.makeText(registerPage4.this, "Success!", Toast.LENGTH_LONG);
                toast.show();
                Intent intent = new Intent(registerPage4.this,registerPage5.class);
                startActivity(intent);
            }
        });
    }

}
