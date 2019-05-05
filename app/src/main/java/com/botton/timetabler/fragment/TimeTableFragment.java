package com.botton.timetabler.fragment;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.botton.timetabler.activity.AddCourseActivity;
import com.botton.timetabler.R;
import com.botton.timetabler.util.ACache;
import com.botton.timetabler.util.Course;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

import java.lang.Math;

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

    ImageView addcourse;
    LinearLayout leftViewLayout;
    int currentCoursesNumber = 0;
    int maxCoursesNumber = 0;

    private ACache aCache;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
//        Log.e(TAG, "onCreateView");
        initview(view);
        inittable();
        createInitCouresView(view);
        return view;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        Log.e(TAG, "onCreate");
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        Log.e(TAG, "onActivityCreated");
//    }
//
//
//    @Override
//    public void onStart() {
//        Log.e(TAG, "onStart");
//        super.onStart();
//    }
//
//    @Override
//    public void onResume() {
//        Log.e(TAG, "onResume");
//        super.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        Log.e(TAG, "onPause");
//        super.onPause();
//    }
//
//    @Override
//    public void onStop() {
//        Log.e(TAG, "onStop");
//        super.onStop();
//    }
//
//    @Override
//    public void onDestroyView() {
//        Log.e(TAG, "onDestroyView");
//        super.onDestroyView();
//    }
//
//    @Override
//    public void onDestroy() {
//        Log.e(TAG, "onDestroy");
//        super.onDestroy();
//    }
//
//    @Override
//    public void onDetach() {
//        Log.e(TAG, "onDetach");
//        super.onDetach();
//    }

    // 初始化控件
    private void initview(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        addcourse = view.findViewById(R.id.button2);
        leftViewLayout = view.findViewById(R.id.left_view_layout);
        day_1 = view.findViewById(R.id.monday);
        day_2 = view.findViewById(R.id.tuesday);
        day_3 = view.findViewById(R.id.wednesday);
        day_4 = view.findViewById(R.id.thursday);
        day_5 = view.findViewById(R.id.friday);
        day_6 = view.findViewById(R.id.saturday);
        day_7 = view.findViewById(R.id.weekday);


        addcourse.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddCourseActivity.class);
            this.startActivityForResult(intent, 0);
        });
    }

    // 动态生成界面
    private void inittable() {
        createLeftView(); //创建课程节数视图
        aCache = ACache.get(getActivity());
        ArrayList<Course> coursesList = (ArrayList<Course>) aCache.getAsObject("tablelist"); //将Course对象添加到Arraylist中，再存入aCache缓冲器中，用于保存课程，这里从aCache中读取保存的课程表
        if (coursesList != null) { //如果课程表不为空，则动态生成界面
            for (Course course : coursesList) { //从ArrayList中一个个读取course并显示出来
                createCourseView(course); //创建课程视图
            }
        }
    }

    //保存课程或者待办
    private void saveData(Course course) {
        ArrayList<Course> arrayList_1 = (ArrayList<Course>) aCache.getAsObject("tablelist");
        if (arrayList_1 != null) {
            arrayList_1.add(course);
            aCache.put("tablelist", arrayList_1);
        } else {
            ArrayList<Course> arrayList_2 = new ArrayList<>();
            arrayList_2.add(course);
            aCache.put("tablelist", arrayList_2);
        }

    }

    //创建课程节数视图
    private void createLeftView() {
        int len = 10;
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

    @SuppressLint("ClickableViewAccessibility")
    private void createInitCouresView(View mainview) {
        int height = 180;

        for (int i = 1; i < 8; i++) {
            switch (i) {
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
            for (int j = 0; j < 10; j++) {
                final View v = LayoutInflater.from(getActivity()).inflate(R.layout.course_card, null); //加载单个课程布局
                v.setY(height * (j)); //设置开始高度
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                        (ViewGroup.LayoutParams.MATCH_PARENT, height - 8); //设置布局高度,即跨多少节课
                v.setLayoutParams(params);


                final View v_2 = LayoutInflater.from(getActivity()).inflate(R.layout.course_card, null); //加载单个课程布局
                v_2.setY(height * (j)); //设置开始高度
                LinearLayout.LayoutParams params_2 = new LinearLayout.LayoutParams
                        (ViewGroup.LayoutParams.MATCH_PARENT, height - 8); //设置布局高度,即跨多少节课
                v_2.setLayoutParams(params_2);

                v_2.setId(View.generateViewId());
                Log.e(TAG, "V_2: " + j + ":" + v_2.getId());


                CardView background = v.findViewById(R.id.background);
                background.setBackgroundColor(Color.WHITE);

                day.addView(v);
                day.addView(v_2);
                v_2.setVisibility(View.GONE);


                v.setOnClickListener(v1 -> {
                    int v_3id = ((((int) v_2.getId() - 1) / 10) / 2) * 20 + 11;
                    int v_2id = (int) v_2.getId() - 20 * ((((int) v_2.getId() - 1) / 10) / 2);
                    CardView v_3 = mainview.findViewById(v_3id);
                    v_3.setVisibility(View.VISIBLE);
                    v_3.setTextAlignment(v_2id-1);
                    v_2.setVisibility(View.VISIBLE);
                    v_2.setBackgroundResource(R.drawable.courseadd);
                });

//
//
//                if((int)v_2.getId()>1 && (int)v_2.getId()<11) {
//                    int id = (int) v_2.getId() - 1 ;
//                    CardView cardView = mainview.findViewById(id);
//
//                        v.setOnTouchListener((v1, event) -> {
//                            if (cardView.getVisibility() == View.VISIBLE) {
//
//                                switch (event.getAction()) {
//                                    case MotionEvent.ACTION_DOWN:
//                                        v_2.setVisibility(View.VISIBLE);
//                                        break;
//                                }
//                            }
//                            return false;
//                        });
//                    }
//
//
//                v_2.setOnTouchListener((v1, event) -> {
//                    switch (event.getAction()) {
//                        case MotionEvent.ACTION_DOWN:
//                            v_2.setVisibility(View.VISIBLE);
//                            Toast.makeText(getActivity(), "down", Toast.LENGTH_LONG).show();
//                            break;
//                        case MotionEvent.ACTION_UP:
//                            Toast.makeText(getActivity(), "up", Toast.LENGTH_LONG).show();
//                            break;
//                        case MotionEvent.ACTION_MOVE:
//                            Toast.makeText(getActivity(), "move", Toast.LENGTH_LONG).show();
//                            break;
//                    }
//                    return false;
//                });


                v_2.setOnClickListener(v1 -> {
                    Toast.makeText(getActivity(), "啦啦啦啦", Toast.LENGTH_LONG).show();
                    v_2.setVisibility(View.GONE);
                });


            }


            final View v_3 = LayoutInflater.from(getActivity()).inflate(R.layout.course_card, null); //加载单个课程布局
            v_3.setY(0); //设置开始高度
            LinearLayout.LayoutParams params_3 = new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, (10 - 1 + 1) * height - 8); //设置布局高度,即跨多少节课
            v_3.setLayoutParams(params_3);

            CardView background_3 = v_3.findViewById(R.id.background);
            background_3.setBackgroundColor(Color.parseColor("#00000000"));

            v_3.setId(View.generateViewId());
            Log.e(TAG, "createInitCouresView: " + v_3.getId());

            for (int k = 0; k < 9; k++) {
                View.generateViewId();
            }

            v_3.setOnClickListener(v -> Log.e(TAG, "V_3: " + v_3.getId()));
            v_3.setVisibility(View.GONE);

            v_3.setOnTouchListener(new View.OnTouchListener() {
                int lastX = 0, lastY = 0;

                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:
                            lastX = (int) event.getRawX();
                            lastY = (int) event.getRawY();
                            break;

                        case MotionEvent.ACTION_MOVE:
                            int moveX = (int) (event.getRawX() - lastX);
                            int moveY = (int) (event.getRawY() - lastY);

                            int[] location = new int[2];
                            v_3.getLocationOnScreen(location); //获取控件的高度

                            lastX = (int) event.getRawX();
                            lastY = (int) event.getRawY();
                            Log.e("TAL", "MOVE_X:" + location[1]);
                            Log.e("TAL", "MOVE_Y:" + moveY);
                            Log.e("TAL", "你摸到了第:" + (((int) (event.getRawY() - location[1]) / 180) + 1) + "个");

                            int pressed = (int) ((event.getRawY() - location[1]) / 180) + 1;

                            for (int i = v_3.getTextAlignment(); i < 10; i++) {
                                if (i < pressed) {
                                    int id = ((((int) v_3.getId() - 1) / 10) / 2) * 20 + i + 1;
                                    CardView cardView = mainview.findViewById(id);
                                    cardView.setVisibility(View.VISIBLE);
                                } else {
                                    int id = ((((int) v_3.getId() - 1) / 10) / 2) * 20 + i + 1;
                                    CardView cardView = mainview.findViewById(id);
                                    cardView.setVisibility(View.GONE);
                                }
                            }

                            if (pressed-1 < v_3.getTextAlignment()){
                                v_3.setVisibility(View.GONE);
                            }

                            break;

                        case MotionEvent.ACTION_UP:
                            Log.e("TAL", "触摸到我了！ACTION_UP");
                            break;

                    }

                    return false;
                }
            });

            day.addView(v_3);


        }

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
            CardView background = v.findViewById(R.id.background);

            background.setBackgroundColor(Color.parseColor(course.getColor()));
            TextView text = v.findViewById(R.id.text_view);
            text.setText(course.getCourseName() + "\n" + course.getTeacher() + "\n" + course.getClassRoom()); //显示课程名
            day.addView(v);

            //长按删除课程
            v.setOnLongClickListener(v1 -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("温馨提示");
                builder.setMessage("是否删除日程？");
                builder.setPositiveButton("是", (dialog, which) -> {
                    v1.setVisibility(View.GONE);//先隐藏
                    day.removeView(v1);//再移除课程视图
                    /*这里写删除科课程的代码*/
                    ArrayList<Course> coursesList = (ArrayList<Course>) aCache.getAsObject("tablelist");
                    for (int i = 0; i < coursesList.size(); i++) {
                        if (coursesList.get(i).getCourseName().equals(course.getCourseName())
                                && coursesList.get(i).getClassRoom().equals(course.getClassRoom())
                                && coursesList.get(i).getDay() == course.getDay()
                                && coursesList.get(i).getTeacher().equals(course.getTeacher())
                                && coursesList.get(i).getStart() == course.getStart()) {
                            coursesList.remove(i);
                        }
                    }
                    aCache.put("tablelist", coursesList);
                });
                builder.setNegativeButton("否", null);
                builder.show();


                return true;
            });
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "onActivityResult++++++++++++++++++++++");
        if (requestCode == 0 && resultCode == 0 && data != null) {
            Course course = (Course) data.getSerializableExtra("course");
            //创建课程表左边视图(节数)
            createLeftView();
            //创建课程表视图
            createCourseView(course);
            //存储数据到数据库
            saveData(course);
        }
    }


    public static TimeTableFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        TimeTableFragment fragment = new TimeTableFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
