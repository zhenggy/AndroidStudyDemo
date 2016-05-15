##Fragment事务

Fragment类引入到Honeycomb时，为协同工作，Activity类被更改为含有FragmentManager类，FragmentManager类负责管理fragment并将它们的视图添加到activity的视图层级结构中。

FragmentManager类具体管理的是：<br>
 **1. fragment队列**<br>
 **2. fragment事务的回退栈**

fragment事务被用来 **添加、移除、附加、分离或替换** fragment队列中的fragment。这是使用fragment在运行时组装和重新组装用户界面的核心方式。FragmentManager管理者fragment事务的回退栈。

FragmentManager.beginTransaction()方法创建并返回FragmentTransaction实例。FragmentTransaction类使用了一个fluent interface接口方法，通过该方法配置FragmentTransaction返回FragmentTransaction类对象，而不是void，由此可得到一个FragmentTransaction队列。

activity因设备旋转或回收内存被销毁后重建时，Activity.onCreate()方法会响应activity重建而被调用。activity被销毁时，它的FragmentManager会将fragment队列保存下来。这样，activity重建时，新的FragmentManager会首先获取保存的队列，然后重建fragment队列，从而恢复到原来的状态。

##Fragment实现选项卡功能

![](http://i.imgur.com/2vxv4a9.png)

	package com.ashzheng.studydemo.demo4;
	
	import android.app.Activity;
	import android.app.FragmentManager;
	import android.app.FragmentTransaction;
	import android.os.Bundle;
	import android.widget.RadioGroup;
	
	import com.ashzheng.studydemo.R;
	
	public class Demo4Activity extends Activity {
	    private Demo4Fragment1 fragment1;
	    private Demo4Fragment2 fragment2;
	    private Demo4Fragment3 fragment3;
	    private Demo4Fragment4 fragment4;
	
	    private FragmentManager fragmentManager;
	
	    private RadioGroup radioGroup;
	
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_demo4);
	
	        radioGroup = (RadioGroup) findViewById(R.id.demo4_rg);
	        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
	            @Override
	            public void onCheckedChanged(RadioGroup radioGroup, int i) {
	                selectFragment(i);
	            }
	        });
	
	        fragmentManager = getFragmentManager();
	        selectFragment(R.id.demo4_rbt3);
	    }
	
	
	    private void selectFragment(int checkedId) {
	
	        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	        hideFragment(fragmentTransaction);
	
	        switch (checkedId) {
	            case R.id.demo4_rbt1:
	                if (null == fragment1) {
	                    fragment1 = new Demo4Fragment1();
	                    fragmentTransaction.add(R.id.demo4_layout, fragment1);
	                } else {
	                    fragmentTransaction.show(fragment1);
	                }
	                break;
	            case R.id.demo4_rbt2:
	                if (null == fragment2) {
	                    fragment2 = new Demo4Fragment2();
	                    fragmentTransaction.add(R.id.demo4_layout, fragment2);
	                } else {
	                    fragmentTransaction.show(fragment2);
	                }
	                break;
	            case R.id.demo4_rbt3:
	                if (null == fragment3) {
	                    fragment3 = new Demo4Fragment3();
	                    fragmentTransaction.add(R.id.demo4_layout, fragment3);
	                } else {
	                    fragmentTransaction.show(fragment3);
	                }
	                break;
	            case R.id.demo4_rbt4:
	                if (null == fragment4) {
	                    fragment4 = new Demo4Fragment4();
	                    fragmentTransaction.add(R.id.demo4_layout, fragment4);
	                } else {
	                    fragmentTransaction.show(fragment4);
	                }
	                break;
	        }
	        fragmentTransaction.commit();
	    }
	
	    private void hideFragment(FragmentTransaction fragmentTransaction) {
	        if(null != fragment1){
	            fragmentTransaction.hide(fragment1);
	        }
	        if(null != fragment2){
	            fragmentTransaction.hide(fragment2);
	        }
	        if(null != fragment3){
	            fragmentTransaction.hide(fragment3);
	        }
	        if(null != fragment4){
	            fragmentTransaction.hide(fragment4);
	        }
	
	    }
	
	}
