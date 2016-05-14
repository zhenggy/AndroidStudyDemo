package com.ashzheng.studydemo.demo3;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import com.ashzheng.studydemo.R;

public class Demo3Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo3);

        Log.d("demoInfo", "Activity onCreate() 方法执行！");


//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.demo3_layout, new Demo3Fragment());
//        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("demoInfo", "Activity onDestroy() 方法执行！");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("demoInfo", "Activity onPause() 方法执行！");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("demoInfo", "Activity onRestart() 方法执行！");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("demoInfo", "Activity onStart() 方法执行！");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("demoInfo", "Activity onStop() 方法执行！");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("demoInfo", "Activity onResume() 方法执行！");

    }

}
