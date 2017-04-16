package com.example.myweek2;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import fragment.TestFragment;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //首先初始化SlidingMenu
        initSlidingMenu();

    }

    private void initSlidingMenu() {
        //获取SlidingMenu对象
        SlidingMenu slidingMenu = new SlidingMenu(this);
        //设置触屏模式
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置触屏范围
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        WindowManager wm = getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        float widthPixels = (float) displayMetrics.widthPixels;
        double v = (int) widthPixels * 0.8;
        slidingMenu.setBehindWidth((int)v);
        slidingMenu.setFadeDegree(0.35f);
        //使SlidingMenu附加在Activity上
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        slidingMenu.setMenu(R.layout.slidingmenu);
        FragmentManager manager = getSupportFragmentManager();
        //这里的R.id.id_left_menu_frame
        manager.beginTransaction().replace(R.id.sliding_frame, new TestFragment()).commit();

    }
}
