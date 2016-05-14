package com.ashzheng.studydemo.demo2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ashzheng.studydemo.R;

public class Demo2Activity extends Activity {

    private Button bt1;
    private Button bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);

        bt1 = (Button) findViewById(R.id.demo2_bt1);
        bt2 = (Button) findViewById(R.id.demo2_bt2);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Demo2Activity.this, Demo2_1Activity.class);
                intent.putExtra("Demo2Activity", "Hello,Demo2_1Acitvity,this is Demo2Activity");
//                startActivity(intent);
                startActivityForResult(intent, 0);//requestCode必须大于等于0，否则onActivityResult()方法不会执行
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Demo2Activity.this, Demo2_2Activity.class);
                intent.putExtra("Demo2Activity", "Hello,Demo2_2Acitvity,this is Demo2Activity");
//                startActivity(intent);
                startActivityForResult(intent, 0);//requestCode必须大于等于0，否则onActivityResult()方法不会执行
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("demoinfo", "requestCode = " + requestCode);
        Log.d("demoinfo", "resultCode = " + resultCode);
        if(null == data){
            Log.d("demoinfo", "data == null");
            return;
        }

        switch (resultCode){
            case 1:
                Log.d("demoinfo", data.getStringExtra("Demo2_1Activity"));
                break;
            case 2:
                Log.d("demoinfo", data.getStringExtra("Demo2_2Activity"));
                break;
        }

    }
}
