package com.ashzheng.studydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ashzheng.studydemo.demo1.Demo1Activity;
import com.ashzheng.studydemo.demo2.Demo2Activity;
import com.ashzheng.studydemo.demo3.Demo3Activity;
import com.ashzheng.studydemo.demo4.Demo4Activity;
import com.ashzheng.studydemo.demo5.Demo5Activity;
import com.ashzheng.studydemo.demo6.Demo6Activity;
import com.ashzheng.studydemo.demo7.Demo7Activity;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ListView listView;
    private ArrayList<String> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);

        getData();

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent intent1 = new Intent(MainActivity.this, Demo1Activity.class);
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent(MainActivity.this, Demo2Activity.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(MainActivity.this, Demo3Activity.class);
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4 = new Intent(MainActivity.this, Demo4Activity.class);
                        startActivity(intent4);
                        break;
                    case 4:
                        Intent intent5 = new Intent(MainActivity.this, Demo5Activity.class);
                        startActivity(intent5);
                        break;
                    case 5:
                        Intent intent6 = new Intent(MainActivity.this, Demo6Activity.class);
                        startActivity(intent6);
                        break;
                    case 6:
                        Intent intent7 = new Intent(MainActivity.this, Demo7Activity.class);
                        startActivity(intent7);
                        break;
                }
            }
        });
    }

    public void getData() {

        dataList = new ArrayList<>();
        dataList.add("Activity生命周期");
        dataList.add("Activity与Activity间的通信");
        dataList.add("Fragment基本用法");
        dataList.add("Fragment实现选项卡");
        dataList.add("Android网络编程");
        dataList.add("跟随手指的小球");
        dataList.add("获取相册和拍照的图片");
    }
}
