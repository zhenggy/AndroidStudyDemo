package com.ashzheng.studydemo.demo5;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ashzheng.studydemo.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Demo5Activity extends Activity {

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo5);

        button1 = (Button) findViewById(R.id.demo5_btn1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doGet();
            }
        });

        button2 = (Button) findViewById(R.id.demo5_btn2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doPost();
            }
        });
    }

    private void doPost() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL("http://cloud.bmob.cn/0906a62b462a3082/getMemberBySex/");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setUseCaches(false);  //POST方法不用缓存
                    httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");//设置请求头
                    httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    httpURLConnection.connect();

                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.writeBytes("sex=girl");//向服务器发送请求内容
                    dataOutputStream.flush();
                    dataOutputStream.close();

                    if (httpURLConnection.getResponseCode() == 200) {   //读取服务器返回数据，和GET方法一样
                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        StringBuffer stringBuffer = new StringBuffer();
                        String readLine = "";
                        while (null != (readLine = bufferedReader.readLine())) {
                            stringBuffer.append(readLine);
                        }
                        inputStream.close();
                        bufferedReader.close();
                        httpURLConnection.disconnect();

                        Log.d("myinfo", stringBuffer.toString());

                    } else {
                        Log.d("myinfo", "failed");
                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    private void doGet() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL("http://cloud.bmob.cn/0906a62b462a3082/getMemberBySex/?sex=boy");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.connect();

                    if (httpURLConnection.getResponseCode() == 200) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        StringBuffer stringBuffer = new StringBuffer();
                        String readLine = "";
                        while (null != (readLine = bufferedReader.readLine())) {
                            stringBuffer.append(readLine);
                        }
                        inputStream.close();
                        bufferedReader.close();
                        httpURLConnection.disconnect();

                        Log.d("myinfo", stringBuffer.toString());

                    } else {
                        Log.d("myinfo", "failed");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }


}
