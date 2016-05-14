package com.ashzheng.studydemo.demo2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ashzheng.studydemo.R;

public class Demo2_1Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2_1);

        Intent intent1 = getIntent();
        Log.d("demoinfo", "Demo2_1Activity收到：  " + intent1.getStringExtra("Demo2Activity"));


        Intent intent2 = new Intent();
        intent2.putExtra("Demo2_1Activity", "I'm Demo2_1Activity!!");
        //setResult(1);
        setResult(1, intent2);

    }


}
