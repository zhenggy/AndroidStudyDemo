package com.ashzheng.studydemo.demo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.ashzheng.studydemo.R;

public class Demo1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("demoInfo", "onCreate() 方法执行！");

        if(null != savedInstanceState){
            Log.d("demoInfo", "Bundle对象获取到的myInfo的值：" + savedInstanceState.getString("myInfo"));
        }else{
            Log.d("demoInfo", "Bundle对象为空");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("demoInfo", "onDestroy() 方法执行！");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("demoInfo", "onPause() 方法执行！");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("demoInfo", "onRestart() 方法执行！");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("demoInfo", "onStart() 方法执行！");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("demoInfo", "onStop() 方法执行！");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("demoInfo", "onResume() 方法执行！");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("myInfo", "hahaha");
        Log.d("demoInfo", "onSaveInstanceState() 方法执行！");
    }
}
