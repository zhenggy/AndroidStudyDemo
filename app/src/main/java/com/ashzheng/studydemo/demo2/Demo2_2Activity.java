package com.ashzheng.studydemo.demo2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ashzheng.studydemo.R;

public class Demo2_2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2_2);

        Intent intent1 = getIntent();
        Log.d("demoinfo", "Demo2_2Activity收到：  " + intent1.getStringExtra("Demo2Activity"));

        Intent intent2 = new Intent();
        intent2.putExtra("Demo2_2Activity", "I'm Demo2_2Activity!!");
        //setResult(2);
        setResult(2, intent2);
    }


}
