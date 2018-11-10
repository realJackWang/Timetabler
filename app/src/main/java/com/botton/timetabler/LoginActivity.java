package com.botton.timetabler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.botton.timetabler.Activity.BtmBarActivity;

public class LoginActivity extends AppCompatActivity {
     String Id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        Button login = (Button) findViewById(R.id.login);
        TextView studentId = (TextView) findViewById(R.id.studentId);
        Id = studentId.getText().toString();

        //点击登陆按钮 就跳转到课程表主界面辣
        login.setOnClickListener((view -> {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, BtmBarActivity.class);
            intent.putExtra("userId",Id);
            LoginActivity.this.startActivity(intent);
        }));
        //************************//
    }

}
