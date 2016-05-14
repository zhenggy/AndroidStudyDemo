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
                }
            }
        });
    }

    public void getData() {

        dataList = new ArrayList<>();
        dataList.add("Activity生命周期");
        dataList.add("Activity与Activity间的通信");
        dataList.add("Fragment基本用法");
    }
}
