package com.botton.timetabler.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.botton.timetabler.R;
import com.botton.timetabler.util.ACache;
import com.botton.timetabler.util.Course;
import com.botton.timetabler.util.HomeWork;
import com.botton.timetabler.util.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddHomeWorkActivity extends AppCompatActivity {

    private ACache aCache;
    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private Calendar calendar;
    Course course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_home_work);
        aCache = ACache.get(this);

        textView = findViewById(R.id.course_name);
        textView1 = findViewById(R.id.course_time_text);
        textView2 = findViewById(R.id.HomeWorkText);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        course = (Course) bundle.getSerializable("course");

        textView.setText(course.getCourseName());
        add();

    }

    private void addtime(List<Course> lists) {
        Log.e("ContentValues", "V_2: "+lists);

        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        linearLayout.removeViewAt(1);
        for(int i =0;i<lists.size();i++){
            View v = LayoutInflater.from(this).inflate(R.layout.single_timeselect, null);
            Course course =  lists.get(i);
            TextView textView = v.findViewById(R.id.course_time_text);
            String day = StringUtil.dayToString(course.getDay()+1);
            textView.setText(day+"：第"+(course.getStart()+1)+"节"+"-第"+(course.getEnd()+1)+"节");

            linearLayout.addView(v,linearLayout.getChildCount()-1);
        }

    }

    public void timepicker(View view) {


//        String month = Integer.toHexString(calendar.get(Calendar.MONTH) + 1); //
//
//        String str = calendar.get(Calendar.YEAR) + "-" + month + "-" + calendar.get(Calendar.DATE);
//        Log.d("初试时间：", str);
//        String str2 = calendar.get(Calendar.YEAR) + 5 + "-" + month + "-" + calendar.get(Calendar.DATE);
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        Date date = null;
//        try {
//            date = sdf.parse(str);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        Date date2 = null;
//        try {
//            date2 = sdf.parse(str2);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        Calendar calendar = Calendar.getInstance();
//        Calendar calendar2 = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar2.setTime(date2);


        TimePickerView pvTime = new TimePickerBuilder(AddHomeWorkActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Toast.makeText(AddHomeWorkActivity.this, '1', Toast.LENGTH_SHORT).show();
            }
        }).build();

//        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
//            @Override
//            public void onTimeSelect(Date date2, View v) {//选中事件回调
//
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//                time.setText(sdf.format(date2));
//            }
//        })
//
//                .setType(TimePickerView.Type.YEAR_MONTH_DAY_HOUR_MIN)//默认全部显示
//                .setCancelText("取消")//取消按钮文字
//                .setSubmitText("确定")//确认按钮文字
//                .setContentSize(20)//滚轮文字大小
//                .setTitleSize(20)//标题文字大小
////                        .setTitleText("请选择时间")//标题文字
//                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
//                .isCyclic(false)//是否循环滚动
//                .setTextColorCenter(Color.BLACK)//设置选中项的颜色
//                .setTitleColor(Color.BLACK)//标题文字颜色
//                .setSubmitColor(Color.BLACK)//确定按钮文字颜色
//                .setCancelColor(Color.BLACK)//取消按钮文字颜色
//                .setRangDate(calendar, calendar2)//起始终止年月日设定
//
////                        .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
////                        .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
////                        .setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR) + 20)//默认是1900-2100年
////                        .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
////                        .setRangDate(startDate,endDate)//起始终止年月日设定
////                        .setLabel("年","月","日","时","分","秒")
//                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
////                        .isDialog(true)//是否显示为对话框样式
//                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();


    }



    public void add() {
        Button okButton = findViewById(R.id.submit);
        okButton.setOnClickListener(v -> {
            String Content = textView2.getText().toString();
            calendar = Calendar.getInstance();
            String year = String.valueOf(calendar.get(Calendar.YEAR));
            String month = String.valueOf(calendar.get(Calendar.MONTH)) + 1;
            String day = String.valueOf(calendar.get(Calendar.DATE));
            String hour = "";
            if (calendar.get(Calendar.AM_PM) == 0)
                hour = String.valueOf(calendar.get(Calendar.HOUR));
            else
                hour = String.valueOf(calendar.get(Calendar.HOUR) + 12);
            String minute = String.valueOf(calendar.get(Calendar.MINUTE));
            String second = String.valueOf(calendar.get(Calendar.SECOND));

            String deadline = textView1.getText().toString();
            HomeWork homeWork = new HomeWork(month + '/' + day, deadline, Content, course);
            ArrayList<HomeWork> arrayList_1 = (ArrayList<HomeWork>) aCache.getAsObject("homeworklist");

            if (arrayList_1 != null) {
                arrayList_1.add(homeWork);
                aCache.put("homeworklist", arrayList_1);
            }
            else{
                ArrayList<HomeWork> arrayList_2 = new ArrayList<>();
                arrayList_1.add(homeWork);
                aCache.put("homeworklist", arrayList_2);
            }

            Toast.makeText(this, "添加成功", Toast.LENGTH_LONG).show();

            finish();

        });

    }

}
