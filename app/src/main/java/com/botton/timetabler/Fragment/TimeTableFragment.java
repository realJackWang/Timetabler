package com.botton.timetabler.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.botton.timetabler.R;
import com.botton.timetabler.Util.ACache;
import com.botton.timetabler.Util.Course;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by bzdell on 2018/7/21.
 */

public class TimeTableFragment extends Fragment {

    private RelativeLayout day;
    private RelativeLayout day_1;
    private RelativeLayout day_2;
    private RelativeLayout day_3;
    private RelativeLayout day_4;
    private RelativeLayout day_5;
    private RelativeLayout day_6;
    private RelativeLayout day_7;


    Button button;
    LinearLayout leftViewLayout;
    int currentCoursesNumber = 0;
    int maxCoursesNumber = 0;

    ACache aCache;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);

        initview(view);
        aCache = ACache.get(getActivity());
        ArrayList<Course> coursesList = (ArrayList<Course>) aCache.getAsObject("tablelist"); //将Course对象添加到Arraylist中，再存入aCache缓冲器中，用于保存课程，这里从aCache中读取保存的课程表

        if (coursesList != null) {//如果课程表不为空，则动态生成界面
            for (Course course : coursesList) {//从ArrayList中一个个读取course并显示出来
                createLeftView(course);//创建课程节数视图
                createCourseView(course);//创建课程视图
            }
        }
        return view;
    }

    private void initview(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        button = view.findViewById(R.id.button2);
        leftViewLayout = view.findViewById(R.id.left_view_layout);

        day_1 = view.findViewById(R.id.monday);
        day_2 = view.findViewById(R.id.tuesday);
        day_3 = view.findViewById(R.id.wednesday);
        day_4 = view.findViewById(R.id.thursday);
        day_5 = view.findViewById(R.id.friday);
        day_6 = view.findViewById(R.id.saturday);
        day_7 = view.findViewById(R.id.weekday);


        button.setOnClickListener(v -> {
            Course course = new Course("1", "2", "3",1, 1, 2);
            saveData(course);
        });
    }



    //保存课程或者待办
    private void saveData(Course course) {
        ArrayList<Course> arrayList_1 =  (ArrayList<Course>) aCache.getAsObject("tablelist");
        if (arrayList_1 != null){
            arrayList_1.add(course);
            aCache.put("tablelist", arrayList_1);
        }
        else{
            ArrayList<Course> arrayList_2 = new ArrayList<>();
            arrayList_2.add(course);
            aCache.put("tablelist", arrayList_2);
        }

    }



    //创建课程节数视图
    private void createLeftView(Course course) {
        int len = course.getEnd();
        if (len > maxCoursesNumber) {
            for (int i = 0; i < len - maxCoursesNumber; i++) {
                View view_2 = getLayoutInflater().inflate(R.layout.left_view, null);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(110, 180);
                view_2.setLayoutParams(params);

                TextView text = view_2.findViewById(R.id.class_number_text);
                text.setText(String.valueOf(++currentCoursesNumber));

                leftViewLayout.addView(view_2);
            }
        }
        maxCoursesNumber = len;
    }

    //创建课程视图
    private void createCourseView(final Course course) {
        int height = 180;
        int getDay = course.getDay();
        if ((getDay < 1 || getDay > 7) || course.getStart() > course.getEnd())
            Toast.makeText(getActivity(), "星期几没写对,或课程结束时间比开始时间还早~~", Toast.LENGTH_LONG).show();
        else {
            switch (getDay) {
                case 1:
                    day = day_1;
                    break;
                case 2:
                    day = day_2;
                    break;
                case 3:
                    day = day_3;
                    break;
                case 4:
                    day = day_4;
                    break;
                case 5:
                    day = day_5;
                    break;
                case 6:
                    day = day_6;
                    break;
                case 7:
                    day = day_7;
                    break;
            }

            final View v = LayoutInflater.from(getActivity()).inflate(R.layout.course_card, null); //加载单个课程布局
            v.setY(height * (course.getStart() - 1)); //设置开始高度,即第几节课开始
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, (course.getEnd() - course.getStart() + 1) * height - 8); //设置布局高度,即跨多少节课
            v.setLayoutParams(params);
            TextView text = v.findViewById(R.id.text_view);
            text.setText(course.getCourseName() + "\n" + course.getTeacher() + "\n" + course.getClassRoom()); //显示课程名
            day.addView(v);
            //长按删除课程
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    v.setVisibility(View.GONE);//先隐藏
                    day.removeView(v);//再移除课程视图
                    /*这里写删除科课程的代码*/
                    ArrayList<Course> coursesList = (ArrayList<Course>) aCache.getAsObject("tablelist");
                    coursesList.remove(course);
                    return true;
                }
            });
        }
    }



/*
    public static TimeTableFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        TimeTableFragment fragment = new TimeTableFragment();
        fragment.setArguments(args);
        return fragment;
    }
    */
}
