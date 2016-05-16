#Android网络编程基础简介

##1. Android网络通信用到的方法
* HTTP：
	1. 支持客户/服务器模式。
	2. 简单快速：客户向服务器请求服务时，只需传送请求方法和路径。请求方法常用的有GET、HEAD、POST。每种方法规定了客户与服务器联系的类型不同。由于HTTP协议简单，使得HTTP服务器的程序规模小，因而通信速度很快。
	3. 灵活：HTTP允许传输任意类型的数据对象。正在传输的类型由Content-Type加以标记。
	4. 无连接：无连接的含义是限制每次连接只处理一个请求。服务器处理完客户的请求，并收到客户的应答后，即断开连接。采用这种方式可以节省传输时间。
	5. 无状态：HTTP协议是无状态协议。无状态是指协议对于事务处理没有记忆能力。缺少状态意味着如果后续处理需要前面的信息，则它必须重传，这样可能导致每次连接传送的数据量增大。另一方面，在服务器不需要先前信息时它的应答就较快 

* Socket：
 
	* Socket 一般有两种类型： TCP 套接字 和 UDP 套接字。用于描述IP地址和端口，是一个通信链的句柄。在Internet上的主机一般运行了多个服务软件，同时提供几种服务。每种服务都打开一个Socket，并绑定到一个端口上，不同的端口对应于不同的服务。
	 
	* 网络上的两个程序通过一个双向的通讯连接实现数据的交换，这个双向链路的一端称为一个Socket。Socket通常用来实现客户方和服务方的连接。Socket是TCP/IP协议的一个十分流行的编程界面，一个Socket由一个IP地址和一个端口号唯一确定。
	
	* 在java中，Socket和ServerSocket类库位于java .net包中。ServerSocket用于服务器端，Socket是建立网络连接时使用的。在连接成功时，应用程序两端都会产生一个Socket实例，操作这个实例，完成所需的会话。


###HTTP
HTTP请求 组成： 请求行、消息报头、请求正文。

HTTP响应 组成： 状态行、消息报头、响应正文。

 
###GET请求和POST请求的区别

引入一个幂等性的概念，幂等是说，一个请求原封不动的发送N次和M次（N不等于M，N和M都大于1）服务器上资源的状态最终是一致的。比如发贴是非幂等的，重放10次发贴请求会创建10个帖子。但修改帖子内容是幂等的，一个修改请求重放无论多少次，帖子最终状态都是一致的。

---
1. GET 被强制服务器支持
2. 浏览器对URL的长度有限制，所以GET请求不能代替POST请求发送大量数据
3. GET请求发送数据更小
4. GET请求是安全的
5. GET请求是幂等的
6. POST请求不能被缓存
7. POST请求相对GET请求是「安全」的

---
* GET用来从服务端获取数据，POST用于上传或者修改数据
* GET大小限制在2KB以内，POST一般没有限制
* GET参数在URL，POST参数在请求主体中（也就是用send发送），安全性POST高
* 部分浏览器会缓存GET请求的response，以至于相同的GET请求会得到相同的response即使服务端的数据已经改变，POST不会被缓存
* 使用XMLHttpRequest时，POST需要显式指定请求头

----
基本应用：

**注意添加权限，注意要开启线程**

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




参考:

知乎 pw，李一奇 麦子学院教程等

https://segmentfault.com/a/1190000004014583