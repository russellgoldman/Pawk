package com.example.cp470_group_project;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.sample.ExploreCourseOffersQuery;
import com.sample.ExploreCoursesQuery;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class CourseReg extends AppCompatActivity {
    private static final String ACTIVITY_NAME = "CourseRegi";
    ListView chatList;
    ImageButton back;
    private ArrayList<CourseOffers> registeredList = new ArrayList<>();
    OfferingsAdapter messageAdapter;

    public class CourseOffers {
        protected String code;
        protected Object startTime;
        protected Object endTime;
        protected List<String> week_days;


        public CourseOffers(
                String code,
                Object startTime,
                Object endTime,
                List<String> week_days

        ) {
            this.code = code;
            this.startTime = startTime;
            this.endTime = endTime;
            this.week_days = week_days;
        }
    }
    public static ApolloClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_reg);

        back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseReg.this, Dashboard.class);
                startActivity(intent);
            }
        });



        client = new GraphQLClient().getClient();
        ExploreCourseOffersQuery exploreCoursesQuery = ExploreCourseOffersQuery.builder().build();
        client.query(exploreCoursesQuery).enqueue(new ApolloCall.Callback<ExploreCourseOffersQuery.Data>() {
            @Override public void onResponse(@NotNull Response<ExploreCourseOffersQuery.Data> response) {
                Log.i(ACTIVITY_NAME, response.data().toString());
                for(ExploreCourseOffersQuery.Node node: response.data().allCourseOfferings().nodes()){
                    CourseOffers crs = new CourseOffers(
                            node.courseCode(),
                            node.startTime(),
                            node.endTime(),
                            node.weekDays()
                    );
                    registeredList.add(crs);
                }
            CourseReg.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.e(ACTIVITY_NAME,"GraphQl Fetched");
                    messageAdapter.notifyDataSetChanged();
                }
            });
            }


            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });

        chatList = findViewById(R.id.ongoingCourses);
        messageAdapter = new OfferingsAdapter(this);
        chatList.setAdapter(messageAdapter);


    }



    public class OfferingsAdapter extends ArrayAdapter<CourseOffers>{
        public OfferingsAdapter(Context ctx){
            super(ctx,0);
        }

        public int getCount(){
            return registeredList.size();
        }

        public String getCourseCode(int position){
            return registeredList.get(position).code;
        }
        public String getStartTime(int position){
            return registeredList.get(position).startTime.toString();
        }
        public String getEndTime(int position){
            return registeredList.get(position).endTime.toString();
        }
        public List<String> getDays(int position){
            return registeredList.get(position).week_days;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            View result = null;
            LayoutInflater inflater = CourseReg.this.getLayoutInflater();
            result = inflater.inflate(R.layout.registrationcourses,null);
            TextView cc = result.findViewById(R.id.regi_course_code);
            cc.setText(getCourseCode(position));
            TextView ts = result.findViewById(R.id.cp470);
            ts.setText(getStartTime(position)+ "-"+ getEndTime(position));
            TextView ds = result.findViewById(R.id.days);
            String dds = "";
            List days = getDays(position);
            for (int i = 0; i < days.size(); i++) {
                String day = days.get(i).toString();
                if(day.substring(0,1) == "TH"){
                    dds = dds + "TR";
                }else{
                    dds = dds + day.charAt(0);
                }

            }
            ds.setText(dds);
            return(result);
        }
    }

}
