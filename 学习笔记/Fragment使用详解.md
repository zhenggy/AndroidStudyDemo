##Fragment生命周期详解

###1. Fragment概述
Fragment从Android v3.0版本开始引入<br>

随着界面布局的复杂化，处理起来也更加的复杂，引入Fragment可以把activity拆分成各个部分。每个Fragment都有它自己的布局和生命周期。方便了开发。<br>

采用fragment而不是activity进行应用的UI管理，可绕开Android系统activity规则的限制。<br>

fragment是一种控制器对象，activity可委派它完成一些任务通常这些任务就是管理用户界面。受管的用户界面可以是一整屏或是整屏的一部分。管理用户界面的fragment又称为UI fragment。它也有自己产生于布局文件的视图。fragment视图包含了用户可以交互的可视化UI元素。<br>

activity托管fragment，暂时可以把**托管**理解成activity在其视图层级里提供一处位置来放置fragment的视图。fragment本身不具有在屏幕上显示视图的能力，因此，只有将它的视图放置在activity的视图层级结构中，fragment视图才能显示在屏幕上。

使用 Fragment 的好处就是可以把业务逻辑和 UI 封装在一起，与外部关联系不大， 其他程序也可以用该组件，从而实现复用最大化。

###2. 两种方式加入Fragment


首先创建 Demo3Fragment 继承 Fragment 类， 并为其创建xml布局文件

	package com.ashzheng.studydemo.demo3;
	
	import android.app.Fragment;
	import android.os.Bundle;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.ViewGroup;
	
	import com.ashzheng.studydemo.R;
	
	public class Demo3Fragment extends Fragment {
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	
	        View view = inflater.inflate(R.layout.fragment_demo3, container, false);
	
	        return view;
	    }
	}


**1. 通过XML标签**

在activity的xml布局文件中添加节点

		<fragment
		        android:id="@+id/demo3_fg"
		        android:name="com.ashzheng.studydemo.demo3.Demo3Fragment"
		        android:layout_width="match_parent"
		        android:layout_height="0dp"
		        android:layout_weight="1"/>


**2. 通过代码动态创建**

1. 在activity的xml布局文件中添加容器视图

		<FrameLayout
		    android:id="@+id/demo3_layout"
		    android:layout_width="match_parent"
		    android:layout_height="0dp"
		    android:layout_weight="1"/>

2. 在activity中动态添加fragment	
	
		package com.ashzheng.studydemo.demo3;
		
		import android.app.Activity;
		import android.app.FragmentManager;
		import android.app.FragmentTransaction;
		import android.os.Bundle;
		
		import com.ashzheng.studydemo.R;
		
		public class Demo3Activity extends Activity {
		
		    @Override
		    protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.activity_demo3);
		
		
		        FragmentManager fragmentManager = getFragmentManager();
		        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		        fragmentTransaction.add(R.id.demo3_layout, new Demo3Fragment());
		        fragmentTransaction.commit();
		    }
		
		}
	
###3. Fragment生命周期
![](http://i.imgur.com/PDMCpkV.png)

fragment生命周期类似于activity的生命周期。生命周期方法的对应非常重要，因为fragment代表activity在工作，它的状态应该也反应了activity的状态。因而，fragment需要对应的生命周期方法来处理activity的工作。


**这些方法的作用会在代码中的注释给出**

	Fragment                                      

	1. 界面打开					
		onCreate() 方法执行！				
		onCreateView() 方法执行！             
		onActivityCreated() 方法执行！	
		onStart() 方法执行！
		onResume() 方法执行！

	2. 按下主屏幕键/锁屏		
		onPause() 方法执行！
		onStop() 方法执行！

	3. 重新打开
		onStart() 方法执行！
		onResume() 方法执行！

	4. 按下后退键
		onPause() 方法执行！
		onStop() 方法执行！
		onDestroyView() 方法执行！
		onDestroy() 方法执行！
		onDetach() 方法执行！

	Activity
		1. 打开应用
		onCreate() 方法执行！
		onStart() 方法执行！
		onResume() 方法执行！

		2. 按下主屏幕键/锁屏
		onPause() 方法执行！
		onStop() 方法执行！

		3. 重新打开应用
		onRestart() 方法执行！
		onStart() 方法执行！
		onResume() 方法执行！

		4. 按下后退键
		onPause() 方法执行！ 
		onStop() 方法执行！	
		onDestroy() 方法执行！

	在Activity中加入Fragment,对应的生命周期
	1. 打开
	Fragment onCreate() 方法执行！
	Fragment onCreateView() 方法执行！
	Activity onCreate() 方法执行！
	Fragment onActivityCreated() 方法执行！
	Activity onStart() 方法执行！
	Fragment onStart() 方法执行！
	Activity onResume() 方法执行！
	Fragment onResume() 方法执行！
	
	2. 按下主屏幕键/锁屏
	Fragment onPause() 方法执行！
	Activity onPause() 方法执行！
	Fragment onStop() 方法执行！
	Activity onStop() 方法执行！
	
	3. 再次打开
	Activity onRestart() 方法执行！
	Activity onStart() 方法执行！
	Fragment onStart() 方法执行！
	Activity onResume() 方法执行！
	Fragment onResume() 方法执行！
	
	4. 按下后退键
	Fragment onPause() 方法执行！
	Activity onPause() 方法执行！
	Fragment onStop() 方法执行！
	Activity onStop() 方法执行！
	Fragment onDestroyView() 方法执行！
	Fragment onDestroy() 方法执行！
	Fragment onDetach() 方法执行！
	Activity onDestroy() 方法执行！

activity的FragmentManager负责调用队列中fragment的生命周期方法，添加fragment供FragmentManager管理时，onAttach（Activity）、onCreat（Bundle）以及onCreatView（）方法会被调用。

托管activity的onCreate()方法执行后，onActivityCreated()方法也会被调用。因为正在向Activity.onCreat（）方法中添加fragment，所以fragment被添加后，该方法会被调用。

在activity处于停止、暂停或运行状态下时，添加fragment会发生什么呢？此种情况下，FragmentManager立即驱使fragment快速跟上activity的步伐，直到与activity的最新状态保持同步。例如，向运行状态的activity中添加fragment时，以下fragment生命周期方法会被依次调用:onAttch()、onCreat()、onCreatView()、onActivityCreated()、onStart(),以及onResume()方法。

只要fragment的状态与activity的状态保持了同步，托管activity的FragmentManager便会继续调用其他生命周期方法以继续保持fragment与activity的状态一致，而几乎同时，它接收到了从操作系统发出的相应调用。但fragment方法究竟是在activity方法之前还是之后调用的这一点是无法保证的。

fragment生命周期与activity生命周期的一个关键区别就在于，fragment的生命周期方法是由托管activity而不是操作系统调用的。操作系统无从知晓activity用来管理视图的fragment。fragment的使用是activity自己内部的事情。可以发现，**activity中的生命周期方法都是protected，而fragment的则是public**，这就是因为fragment是由activity来管理的，activity需要调用这些个方法。

###4.Fragment中的数据保存

和在activity中一样，fragment也提供了onSaveInstanceState()方法，使用也基本和activity一样，不同的是，在fragment中，虽然Fragment.onCreate()方法配置了fragment实例，但创建和配置fragment视图是通过Fragment.onCreateView()方法来完成的。所以可以在**Fragment.onCreateView()**这个方法中获取在保存状态下重建视图所使用的数据。

具体代码：

Demo3Fragment

	package com.ashzheng.studydemo.demo3;
	
	import android.app.Fragment;
	import android.content.Context;
	import android.os.Bundle;
	import android.util.Log;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.ViewGroup;
	
	import com.ashzheng.studydemo.R;
	
	public class Demo3Fragment extends Fragment {
	
	    @Override
	    public void onAttach(Context context) {
	        //当fragment 第一次与 Activity 产生关联时调用，以后不再调用
	        super.onAttach(context);
	        Log.d("demoinfo", "Fragment onAttach() 方法执行！");
	    }
	
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        //在 onAttach 执行完后会立刻调用此方法，通常被用于读取保存的状态值，获取或者初始化一些数据，
	        // 但是该方法不执行，窗口是不会显示的，因此如果获取的数据需要访问网络，最好新开线程。
	        super.onCreate(savedInstanceState);
	        Log.d("demoinfo", "Fragment onCreate() 方法执行！");
	    }
	
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        //创建 Fragment 中显示的 view, 其中 inflater 用来装载布局文件， container 表示 <fragment> 标签的父标签对应的 ViewGroup 对象，
	        // savedInstanceState 可以获取 Fragment 保存的状态
	
	        Log.d("demoinfo", "Fragment onCreateView() 方法执行！");
	
	        if(null != savedInstanceState){
	            Log.d("demoinfo", "保存了的数据： "+ savedInstanceState.getString("myinfo"));
	        }else {
	            Log.d("demoinfo", "没有保存的数据！");
	        }
	
	        View view = inflater.inflate(R.layout.fragment_demo3, container, false);
	
	        return view;
	    }
	
	    @Override
	    public void onActivityCreated(Bundle savedInstanceState) {
	        //在 Activity.onCreate() 方法调用后会立刻调用此方法，表示窗口已经初始化完毕，此时可以调用控件了
	
	        super.onActivityCreated(savedInstanceState);
	        Log.d("demoinfo", "Fragment onActivityCreated() 方法执行！");
	    }
	
	    @Override
	    public void onStart() {
	        //开始执行与控件相关的逻辑代码，如按键点击
	        super.onStart();
	        Log.d("demoinfo", "Fragment onStart() 方法执行！");
	    }
	
	    @Override
	    public void onResume() {
	        //这是 Fragment 从创建到显示的最后一个回调的方法
	        super.onResume();
	        Log.d("demoinfo", "Fragment onResume() 方法执行！");
	    }
	
	    @Override
	    public void onPause() {
	        //当发生界面跳转时，临时暂停，暂停时间是 500ms,0.5s后直接进入下面的 onStop 方法
	        super.onPause();
	        Log.d("demoinfo", "Fragment onPause() 方法执行！");
	    }
	
	    @Override
	    public void onStop() {
	        //当该方法返回时， Fragment 将从屏幕上消失
	        super.onStop();
	        Log.d("demoinfo", "Fragment onStop() 方法执行！");
	    }
	
	    @Override
	    public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        Log.d("demoinfo", "Fragment onSaveInstanceState() 方法执行！");
	        outState.putString("myinfo", "haha");
	    }
	
	    @Override
	    public void onDestroyView() {
	        //当 fragment 状态被保存，或者从回退栈弹出，该方法被调用
	        super.onDestroyView();
	        Log.d("demoinfo", "Fragment onDestroyView() 方法执行！");
	    }
	
	    @Override
	    public void onDestroy() {
	        //当 Fragment 不再被使用时，如按返回键，就会调用此方法
	        super.onDestroy();
	        Log.d("demoinfo", "Fragment onDestroy() 方法执行！");
	    }
	
	    @Override
	    public void onDetach() {
	        //Fragment 生命周期的最后一个方法，执行完后将不再与 Activity 关联，将释放所有 fragment 对象和资源
	        super.onDetach();
	        Log.d("demoinfo", "Fragment onDetach() 方法执行！");
	    }
	}

Demo3Activity

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

activity_demo3.xml

	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:tools="http://schemas.android.com/tools"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    android:orientation="vertical"
	    tools:context="com.ashzheng.studydemo.demo3.Demo3Activity">
	
	    <fragment
	        android:id="@+id/demo3_fg"
	        android:name="com.ashzheng.studydemo.demo3.Demo3Fragment"
	        android:layout_width="match_parent"
	        android:layout_height="0dp"
	        android:layout_weight="1"/>
	    <FrameLayout
	        android:id="@+id/demo3_layout"
	        android:layout_width="match_parent"
	        android:layout_height="0dp"
	        android:layout_weight="1"/>	
	
	</LinearLayout>

总项目地址：https://github.com/zhenggy/AndroidStudyDemo