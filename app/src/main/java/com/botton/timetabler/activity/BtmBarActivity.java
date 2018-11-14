package com.botton.timetabler.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.botton.timetabler.fragment.CollegeFragment;
import com.botton.timetabler.fragment.DiscoverFragment;
import com.botton.timetabler.fragment.TimeTableFragment;
import com.botton.timetabler.R;

public class BtmBarActivity extends AppCompatActivity {

    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btm_bar);
        //获取LoginActiity传来的userId
        Intent intent = getIntent();
        id = intent.getStringExtra("userId");
        //************************//


        initView();

    }

    private void initView() {


        RadioGroup radioGroup = findViewById(R.id.radio_group);
        RadioButton radioButton1 = findViewById(R.id.rb_kcb);
        RadioButton radioButton2 = findViewById(R.id.rb_fx);
        RadioButton radioButton3 = findViewById(R.id.rb_xy);

        TimeTableFragment timeTableFragment = new TimeTableFragment();
        DiscoverFragment discoverFragment = new DiscoverFragment();
        CollegeFragment collegeFragment = new CollegeFragment();

        //设置底部图片的大小啊什么的。。
        Drawable drawable_kcb = getResources().getDrawable(R.drawable.selector4kcb);
        Drawable drawable_fx = getResources().getDrawable(R.drawable.selector4fx);
        Drawable drawable_xy = getResources().getDrawable(R.drawable.selector4xy);

        drawable_kcb.setBounds(0, 0, 80, 80);
        drawable_fx.setBounds(0, 0, 80, 80);
        drawable_xy.setBounds(0, 0, 80, 80);

        radioButton1.setCompoundDrawables(null, drawable_kcb, null, null);
        radioButton2.setCompoundDrawables(null, drawable_fx, null, null);
        radioButton3.setCompoundDrawables(null, drawable_xy, null, null);


        //初始加载课程表fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, timeTableFragment).commit();

        //设置默认选中项
        RadioButton kcbButton = findViewById(R.id.rb_kcb);
        kcbButton.setChecked(true);

        //设置状态改变的事件
        radioGroup.setOnCheckedChangeListener((radioGroup1, i) -> {
            switch (radioGroup1.getCheckedRadioButtonId()) {
                case R.id.rb_kcb:
                    FragmentManager fragmentManager1 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                    fragmentTransaction1.replace(R.id.frameLayout, timeTableFragment).commit();
                    break;
                case R.id.rb_fx:
                    FragmentManager fragmentManager2 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                    fragmentTransaction2.replace(R.id.frameLayout, discoverFragment).commit();

                    break;
                case R.id.rb_xy:

                    FragmentManager fragmentManager3 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                    fragmentTransaction3.replace(R.id.frameLayout, collegeFragment).commit();
                    break;
            }
        });

    }


}
