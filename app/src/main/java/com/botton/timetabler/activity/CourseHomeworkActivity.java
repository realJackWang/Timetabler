package com.botton.timetabler.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.botton.timetabler.R;

public class CourseHomeworkActivity extends AppCompatActivity {

    Button hw_sta = findViewById(R.id.hw_new);
    Button hw_all = findViewById(R.id.hw_all);
    ListView lw = findViewById(R.id.listView);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_homework);




    }





}
