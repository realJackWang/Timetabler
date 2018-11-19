package com.botton.timetabler.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.botton.timetabler.fragment.CollegeFragment;
import com.botton.timetabler.fragment.DiscoverFragment;
import com.botton.timetabler.fragment.TimeTableFragment;
import com.botton.timetabler.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author JackWang
 * @fileName BottomBarActivity
 * @date on 2018/11/14 下午 1:03
 * @email 544907049@qq.com
 **/
public class BottomBarActivity extends FragmentActivity {
    public static BottomBarActivity test_a = null;

    private ArrayList<Fragment> fragments;

    private int clickTime=0;
    private int times=-1;
    private Timer timer=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottombar);
        test_a =this;


        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar       //定义下面图标及名称及按压颜色
                .addItem(new BottomNavigationItem(R.drawable.selector4kcb, "日程表").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.selector4fx, "发现").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.selector4xy, "校园").setActiveColorResource(R.color.blue))
                .setFirstSelectedPosition(0)
                .initialise();

        fragments = getFragments();
        setDefaultFragment();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                if (fragments != null) {


                    if (position < fragments.size()) {
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        Fragment thisfragment = fm.findFragmentById(R.id.layFrame);   //获取当前的fragment
                        Fragment fragment = fragments.get(position);


                        if (fm.getFragments() != null && fm.getFragments().size() > 0) {
                            for (Fragment cf : fm.getFragments()) {
                                ft.hide(cf);
                            }
                        }


                        if (fragment.isAdded()) {
                            ft.show(fragment);
                        } else {
                            ft.add(R.id.layFrame, fragment);
                        }
                        ft.commitAllowingStateLoss();
                    }
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

    }


    private void setDefaultFragment() {     //设定默认的主页
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, TimeTableFragment.newInstance("通知"));
        transaction.commit();
    }


    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(TimeTableFragment.newInstance("通知"));
        fragments.add(DiscoverFragment.newInstance("发现"));
        fragments.add(CollegeFragment.newInstance("个人"));
        return fragments;
    }


    public void onBackPressed() {    //按两次返回退出程序

        clickTime=clickTime+1;

        if(clickTime==1&&timer==null){
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            timer=new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    times=times+1;
                    if(times==2){
                        clickTime=0;
                        times=-1;
                        timer.cancel();
                        timer=null;
                    }
                }
            }, 0,1000);
        }else if(clickTime==2){
            if(timer!=null){
                timer.cancel();
                timer=null;
                super.onBackPressed();
            }else{
                super.onBackPressed();
            }
        }
    }



}


