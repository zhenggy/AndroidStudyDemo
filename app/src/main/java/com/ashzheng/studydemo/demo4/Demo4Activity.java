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
