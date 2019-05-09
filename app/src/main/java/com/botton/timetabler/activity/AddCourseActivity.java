package com.botton.timetabler.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.botton.timetabler.R;
import com.botton.timetabler.util.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.botton.timetabler.util.StringUtil;
import com.botton.timetabler.util.bean.GetJsonDataUtil;
import com.botton.timetabler.util.bean.JsonBean;
import com.google.gson.Gson;

import org.json.JSONArray;

/**
 * @author JackWang
 * @fileName AddCourseActivity
 * @date on 2018/11/14 上午 11:47
 * @email 544907049@qq.com
 **/

public class AddCourseActivity extends AppCompatActivity {

//    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
//    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
//    private OptionsPickerView pvOptions,pvOptions_2;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;

    private boolean isLoaded = false;


    LinearLayout courestime;

    private TextView coursetimetext;
    private EditText inputCourseName;
    private EditText inputplace;
    private LinearLayout layout;
    private int startTime = 0;
    private int endTime = 0;
    private int dayTime = 0;

    private boolean aaaa = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        setFinishOnTouchOutside(false);

        initview();


        submit();


        courestime.setOnClickListener(v -> {
            if (isLoaded) {
                showPickerView();
            }
        });


    }

    private void initview() {
        inputCourseName = findViewById(R.id.course_name);
        inputplace = findViewById(R.id.course_place);
        coursetimetext = findViewById(R.id.course_time_text);
        courestime = findViewById(R.id.course_time);
        layout = findViewById(R.id.linearLayout);

        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            finish();
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            List<Course> lists = (List<Course>) bundle.getSerializable("course");
            if (lists != null) {
                addtime(lists);
                aaaa = false;
            }
        }


//        String strContentString = bundle.getString("course");


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

    private void submit() {
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        Button okButton = findViewById(R.id.submit);
        okButton.setOnClickListener(v -> {
            String courseName = inputCourseName.getText().toString();
            String teacher = "";
            String classRoom = inputplace.getText().toString();

            if (courseName.equals("") || (coursetimetext.getText().equals("") && aaaa)) {
                Toast.makeText(AddCourseActivity.this, "基本课程信息未填写", Toast.LENGTH_SHORT).show();
            } else {
                ArrayList<Course> lists = new ArrayList<>();
                Random random = new Random();
                int random_num = (1 + random.nextInt(7));
                for (int i=0;i<linearLayout.getChildCount()-2;i++) {

                    LinearLayout linearLayout1 = (LinearLayout) linearLayout.getChildAt(i+1);
                    TextView textView = linearLayout1.findViewById(R.id.course_time_text);

                    String[] strings = textView.getText().toString().split("：|-");

                    dayTime = StringUtil.stringToDay(strings[0]);
                    startTime =StringUtil.findIntInString(strings[1]) ;
                    endTime = StringUtil.findIntInString(strings[2]);


                    Course course = new Course(courseName, teacher, classRoom,
                            Integer.valueOf(dayTime), Integer.valueOf(startTime), Integer.valueOf(endTime), random_num);
                    lists.add(course);
                }

                Intent intent = new Intent(AddCourseActivity.this, BtmBarActivity.class);
                intent.putExtra("course", lists);

                setResult(0, intent);
                finish();
            }
        });
    }


//    private void initOptionPicker() {//条件选择器初始化
//
//        /**
//         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
//         */
//
//        pvOptions = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
//            //返回的分别是三个级别的选中位置
//            startTime = StringUtil.findIntInString(options1Items.get(options1).getPickerViewText());
//            endTime = StringUtil.findIntInString(options2Items.get(options1).get(options2));
//
//            String tx = options1Items.get(options1).getPickerViewText() +"—"+ options2Items.get(options1).get(options2);
//            btn_Options.setText(tx);
//        })
//                .setTitleText("开始-结束")
//                .setContentTextSize(20)//设置滚轮文字大小
//                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
//                .setSelectOptions(0, 1)//默认选中项
//                .setBgColor(Color.WHITE)
//                .setTitleBgColor(getResources().getColor(R.color.blue))
//                .setTitleColor(Color.WHITE)
//                .setCancelColor(Color.WHITE)
//                .setSubmitColor(Color.WHITE )
//                .setTextColorCenter(Color.BLACK)
//                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
//                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .setLabels("", "", "")
//                .setBackgroundId(0x00000000) //设置外部遮罩颜色
//                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
//                    @Override
//                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
//                        String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
//                        Toast.makeText(AddCourseActivity.this, str, Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .build();
//
//        pvOptions.setPicker(options1Items, options2Items);
//
//    }


//    private void getOptionData() {
//
//
//        //选项1
//        options1Items.add(new ProvinceBean(0, "第1节", "描述部分", "其他数据"));
//        options1Items.add(new ProvinceBean(1, "第2节", "描述部分", "其他数据"));
//        options1Items.add(new ProvinceBean(2, "第3节", "描述部分", "其他数据"));
//        options1Items.add(new ProvinceBean(3, "第4节", "描述部分", "其他数据"));
//        options1Items.add(new ProvinceBean(4, "第5节", "描述部分", "其他数据"));
//        options1Items.add(new ProvinceBean(5, "第6节", "描述部分", "其他数据"));
//        options1Items.add(new ProvinceBean(6, "第7节", "描述部分", "其他数据"));
//        options1Items.add(new ProvinceBean(7, "第8节", "描述部分", "其他数据"));
//        options1Items.add(new ProvinceBean(8, "第9节", "描述部分", "其他数据"));
//        options1Items.add(new ProvinceBean(9, "第10节", "描述部分", "其他数据"));
//
//        //选项2
//        ArrayList<String> options2Items_01 = new ArrayList<>();
//        options2Items_01.add("第1节");
//        options2Items_01.add("第2节");
//        options2Items_01.add("第3节");
//        options2Items_01.add("第4节");
//        options2Items_01.add("第5节");
//        options2Items_01.add("第6节");
//        options2Items_01.add("第7节");
//        options2Items_01.add("第8节");
//        options2Items_01.add("第9节");
//        options2Items_01.add("第10节");
//        ArrayList<String> options2Items_02 = new ArrayList<>();
//        options2Items_02.add("第2节");
//        options2Items_02.add("第3节");
//        options2Items_02.add("第4节");
//        options2Items_02.add("第5节");
//        options2Items_02.add("第6节");
//        options2Items_02.add("第7节");
//        options2Items_02.add("第8节");
//        options2Items_02.add("第9节");
//        options2Items_02.add("第10节");
//        ArrayList<String> options2Items_03 = new ArrayList<>();
//        options2Items_03.add("第3节");
//        options2Items_03.add("第4节");
//        options2Items_03.add("第5节");
//        options2Items_03.add("第6节");
//        options2Items_03.add("第7节");
//        options2Items_03.add("第8节");
//        options2Items_03.add("第9节");
//        options2Items_03.add("第10节");
//        ArrayList<String> options2Items_04 = new ArrayList<>();
//        options2Items_04.add("第4节");
//        options2Items_04.add("第5节");
//        options2Items_04.add("第6节");
//        options2Items_04.add("第7节");
//        options2Items_04.add("第8节");
//        options2Items_04.add("第9节");
//        options2Items_04.add("第10节");
//        ArrayList<String> options2Items_05 = new ArrayList<>();
//        options2Items_05.add("第5节");
//        options2Items_05.add("第6节");
//        options2Items_05.add("第7节");
//        options2Items_05.add("第8节");
//        options2Items_05.add("第9节");
//        options2Items_05.add("第10节");
//        ArrayList<String> options2Items_06 = new ArrayList<>();
//        options2Items_06.add("第6节");
//        options2Items_06.add("第7节");
//        options2Items_06.add("第8节");
//        options2Items_06.add("第9节");
//        options2Items_06.add("第10节");
//        ArrayList<String> options2Items_07 = new ArrayList<>();
//        options2Items_07.add("第7节");
//        options2Items_07.add("第8节");
//        options2Items_07.add("第9节");
//        options2Items_07.add("第10节");
//        ArrayList<String> options2Items_08 = new ArrayList<>();
//        options2Items_08.add("第8节");
//        options2Items_08.add("第9节");
//        options2Items_08.add("第10节");
//        ArrayList<String> options2Items_09 = new ArrayList<>();
//        options2Items_09.add("第9节");
//        options2Items_09.add("第10节");
//        ArrayList<String> options2Items_10 = new ArrayList<>();
//        options2Items_10.add("第10节");
//
//
//        options2Items.add(options2Items_01);
//        options2Items.add(options2Items_02);
//        options2Items.add(options2Items_03);
//        options2Items.add(options2Items_04);
//        options2Items.add(options2Items_05);
//        options2Items.add(options2Items_06);
//        options2Items.add(options2Items_07);
//        options2Items.add(options2Items_08);
//        options2Items.add(options2Items_09);
//        options2Items.add(options2Items_10);
//
//
//        /*--------数据源添加完毕---------*/
//    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了

                        thread = new Thread(() -> {
                            // 子线程中解析省市区数据
                            initJsonData();
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
                    break;
            }
        }
    };


    private void showPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {

            dayTime = StringUtil.stringToDay(options1Items.get(options1).getPickerViewText());
            System.out.println(dayTime);
            startTime = StringUtil.findIntInString(options2Items.get(options1).get(options2));
            endTime = StringUtil.findIntInString(options3Items.get(options1).get(options2).get(options3));

            //返回的分别是三个级别的选中位置
            String tx = options1Items.get(options1).getPickerViewText() + ":" +
                    options2Items.get(options1).get(options2) + "-" +
                    options3Items.get(options1).get(options2).get(options3);

            coursetimetext.setText(tx);

            Toast.makeText(AddCourseActivity.this, tx, Toast.LENGTH_SHORT).show();
        })

                .setTitleText("时间选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "week.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }


}
