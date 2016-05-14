##Activity与Activity间的通信

###Activity的启动
一个Activity启动另一个Activity最简单的方法就是用startActivity(Intent intent)方法,这个方法调用请求是发送给操作系统的ActivityManager负责创建Activity实例并调用其onCreat()方法。ActivityManager是通过传入startActivity(Intent intent)方法的Intent参数知道需要启动哪一个Activity。

ActivityManager维护着一个非特定应用独享的**回退栈**。所有应用的activity都共享该回退栈。这也是将ActivityManager设计成操作系统级的activity管理器来负责启动应用activity的原因之一。不局限与单个应用，回退栈作为一个整体共享给操作系统及设备使用。


描述：通过 Demo2Activity 启动 Demo2\_1Activity 或 Demo2\_2Activity，Demo2\_1Activity或Demo2\_2Activity退出后把信息回馈到 Demo2Activity

1. 在Demo2Activity中
	
		Intent intent = new Intent(Demo2Activity.this, Demo2_1Activity.class);
	    intent.putExtra("Demo2Activity", "Hello,Demo2_1Acitvity,this is Demo2Activity");	
	    startActivityForResult(intent, 0);
		//requestCode必须大于等于0，否则onActivityResult()方法不会执行
		//如果在Demo2_1Acitvity中没有返回消息，那么resultCode会等于requestCode，并且data = null
这样，就把消息传递给了Demo2_1Acitvity

2. 在Demo2_1Acitvity中

		/*收到 Demo2Activity 的消息*/
		Intent intent1 = getIntent();
        Log.d("demoinfo", "Demo2_1Activity收到：  " + intent1.getStringExtra("Demo2Activity"));

		/*给Demo2Activity发送消息*/
        Intent intent2 = new Intent();
        intent2.putExtra("Demo2_1Activity", "I'm Demo2_1Activity!!");
        //setResult(1);	两种方式，这个是只传一个resultCode
        setResult(1, intent2);

3. 在Demo2Activity中重载onActivityResult(int requestCode, int resultCode, Intent data)方法

		requestCode就是之前在Demo2Activity中startActivityForResult(Intent, int)传入的requestCode
		resultCode就是在Demo2_1Acitvity中setResult(int, Intent);传入的int resultCode
		data就是在Demo2_1Acitvity中setResult(int, Intent);传入的Intent

代码：

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

---

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

---
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

---
参考《Android编程权威指南》