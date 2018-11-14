package com.botton.timetabler.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.botton.timetabler.R;
import com.botton.timetabler.Util.Course;

public class AddCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        setFinishOnTouchOutside(false);

        final EditText inputCourseName = findViewById(R.id.course_name);
        final EditText inputTeacher = findViewById(R.id.teacher_name);
        final EditText inputClassRoom = findViewById(R.id.class_room);
        final EditText inputDay = findViewById(R.id.week);
        final EditText inputStart = findViewById(R.id.classes_begin);
        final EditText inputEnd = findViewById(R.id.classes_ends);

        Button okButton = findViewById(R.id.button);
        okButton.setOnClickListener(v -> {
            String courseName = inputCourseName.getText().toString();
            String teacher = inputTeacher.getText().toString();
            String classRoom = inputClassRoom.getText().toString();
            String day = inputDay.getText().toString();
            String start = inputStart.getText().toString();
            String end = inputEnd.getText().toString();

            if (courseName.equals("") || day.equals("") || start.equals("") || end.equals("")) {
                Toast.makeText(AddCourseActivity.this, "基本课程信息未填写", Toast.LENGTH_SHORT).show();
            }
            else {
                Course course = new Course(courseName, teacher, classRoom,
                        Integer.valueOf(day), Integer.valueOf(start), Integer.valueOf(end));
                Intent intent = new Intent(AddCourseActivity.this, BtmBarActivity.class);
                intent.putExtra("course", course);

                setResult(0, intent);
                finish();
            }
        });
    }
}
