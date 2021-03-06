package com.example.oliver.mynote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class RegistersucceedActivity extends AppCompatActivity{
    private TextView textView;
    private int count = 3;/*
    private Animation animation;*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去除标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_registersucceed);
        // 初始化控件对象
        textView = (TextView) findViewById(R.id.time);/*
        animation = AnimationUtils.loadAnimation(this, R.anim.animation_text);*/
        //textView.startAnimation(animation);
        handler.sendEmptyMessageDelayed(0, 1000);
    }
    private int getCount() {
        count--;
        if (count == 0) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return count;
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                textView.setText(getCount()+"");
                handler.sendEmptyMessageDelayed(0, 1000);/*
                animation.reset();
                textView.startAnimation(animation);*/
            }

        };

    };

}
