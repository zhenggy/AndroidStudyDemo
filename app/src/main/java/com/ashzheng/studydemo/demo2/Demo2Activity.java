package com.ashzheng.studydemo.demo2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ashzheng.studydemo.R;

public class Demo2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);

        Intent intent = new Intent(this, Demo2_1Activity.class);

        intent.putExtra("", "");

        startActivity(intent);
    }

}
