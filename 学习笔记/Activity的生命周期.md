##Activity的生命周期



###1. 从打开应用到按后退键
	
		打开应用
		onCreate() 方法执行！ 	 不可见
		onStart() 方法执行！ 	 可见
		onResume() 方法执行！	 可见&在前台
		按下后退键
		onPause() 方法执行！  可见
		onStop() 方法执行！	 不可见
		onDestroy() 方法执行！不可见

###2. 从打开应用到按主屏幕键 再到重新打开应用

		打开应用
		onCreate() 方法执行！
		onStart() 方法执行！
		onResume() 方法执行！
		按下主屏幕键
		onPause() 方法执行！
		onStop() 方法执行！
		重新打开应用
		onRestart() 方法执行！
		onStart() 方法执行！
		onResume() 方法执行！
	
![](http://i.imgur.com/JkgLvMi.png)

1. Activity在onPause() 和 onStop()后都可能由于内存不足等原因被杀死从而当用户重新打开app时会重新执行onCreat()方法。
2. onPause()方法在app被其他app覆盖时会执行，如果短时间内重新回到前面，则会执行onResume()方法。如果长时间不可见的话则会执行onStop()方法。

###3. 横竖屏切换问题
通过横竖屏切换时发现，Activity的生命周期的变化如下：

	onPause() 方法执行！
	onStop() 方法执行！
	onDestroy() 方法执行！

	onCreate() 方法执行！
	onStart() 方法执行！
	onResume() 方法执行！
是把当前的Activity销毁后重新创建   

**在res目录下新建layout-land文件夹，里面放的是Activity横屏时显示的布局，名字要与layout文件夹下的文件名字一样。**

####设备旋转前保存数据：
1. 重载onSaveInstanceState()方法

	onPause() 方法执行！
	onStop() 方法执行！
	onSavaInstanceState(Bundle) 方法执行
	onDestroy() 方法执行！

	onCreate() 方法执行！
	onStart() 方法执行！
	onResume() 方法执行！
方法onSavaInstanceState()默认的实现要求所有activity的视图将自身状态数据保存在Bundle对象中。在重新执行onCreat()方法时会传入Bundle对象。


		onCreate() 方法执行！		/**首次打开app**/
		Bundle对象为空
		onStart() 方法执行！
		onResume() 方法执行！
	
		onPause() 方法执行！		/**旋转手机屏幕**/
		onSaveInstanceState() 方法执行！
		onStop() 方法执行！
		onDestroy() 方法执行！
		onCreate() 方法执行！
		Bundle对象获取到的myInfo的值：hahaha
		onStart() 方法执行！
		onResume() 方法执行！

当旋转手机屏幕并重新执行onCreat()方法后在onSaveInstanceState()中向Bundle保存的值传了过去。

那么有个问题，onSaveInstanceState()这个方法什么时候会执行？一定会在onPause()和onStop()之间执行么？

答案：onSaveInstanceState()这个方法不一定都会执行，当用户按下后退键时，代表了不再需要这个Activity了，这时候就没有必要执行这个方法了。但是，如果用户按下主屏幕键或者这个Activity转为后台运行时，这个时候需要保存信息，这个方法会执行。

	onRestart() 方法执行！		/**打开app**/
	onStart() 方法执行！
	onResume() 方法执行！

	onPause() 方法执行！			/**按下后退键**/
	onStop() 方法执行！
	onDestroy() 方法执行！

----------
	onCreate() 方法执行！			/**打开app**/
	Bundle对象为空
	onStart() 方法执行！
	onResume() 方法执行！

	onPause() 方法执行！			/**按下主屏幕键**/
	onSaveInstanceState() 方法执行！
	onStop() 方法执行！

用户离开当前activity管理的用户界面，或Android需要回收内存时，activity也会被销毁。
不过Android从不会为了回收内存而去销毁正在运行的activity。activity只有在暂停或者停止状态下才可能被销毁。此时就会调用onSaveInstanceState()方法。
调用这个方法时，用户数据会被保存在Bundle对象中。然后操作系统将Bundle对象放入activity记录中。

activity暂存后，Activity对象不再存在，但操作系统会将activity记录保存起来。在需要回复activity时，操作系统可以使用暂存的activity记录重新激活activity。

常见的做法是覆盖onSaveInstanceState()方法把数据保存在Bundle对象中，覆盖onPause()方法处理其他需要处理的事情。

那么暂存的activity记录可以保存多久？用户按下后退键后，系统会彻底销毁当前的activity。activity记录也会被清除。系统重启或长时间不适用这个activity时，记录也会被删除。

测试代码：

		package com.ashzheng.helloandroid;
	
	import android.app.Activity;
	import android.os.Bundle;
	import android.util.Log;
	
	public class MainActivity extends Activity {
	
	
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        Log.d("info", "onCreate() 方法执行！");
	
	        if(null != savedInstanceState){
	            Log.d("info", "Bundle对象获取到的myInfo的值：" + savedInstanceState.getString("myInfo"));
	        }else{
	            Log.d("info", "Bundle对象为空");
	        }
	
	    }
	
	    @Override
	    protected void onDestroy() {
	        super.onDestroy();
	        Log.d("info", "onDestroy() 方法执行！");
	    }
	
	    @Override
	    protected void onPause() {
	        super.onPause();
	        Log.d("info", "onPause() 方法执行！");
	    }
	
	    @Override
	    protected void onRestart() {
	        super.onRestart();
	        Log.d("info", "onRestart() 方法执行！");
	    }
	
	    @Override
	    protected void onStart() {
	        super.onStart();
	        Log.d("info", "onStart() 方法执行！");
	    }
	
	    @Override
	    protected void onStop() {
	        super.onStop();
	        Log.d("info", "onStop() 方法执行！");
	    }
	
	    @Override
	    protected void onResume() {
	        super.onResume();
	        Log.d("info", "onResume() 方法执行！");
	
	    }
	
	    @Override
	    protected void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	
	        outState.putString("myInfo", "hahaha");
	        Log.d("info", "onSaveInstanceState() 方法执行！");
	    }
	}
