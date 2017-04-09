package com.example.my;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ListView;


import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import Utils.MyAsync;
import Utils.URL;
import fragment.TestFragment;


public class MainActivity extends FragmentActivity {

    private Fragment mContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();
        initSlidingMenu();

    }

    private void initview() {
        ListView lv= (ListView) findViewById(R.id.lv);
        MyAsync myAsync = new MyAsync(lv, this);
        myAsync.execute(URL.url);
    }
    private void initSlidingMenu() {
        SlidingMenu menu = new SlidingMenu(this);
        //设置左滑菜单
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
          //设置要使菜单滑动，触碰屏幕的范围
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置阴影图片的宽度
//        menu.setShadowWidthRes(R.dimen.shadow_width);
        //设置阴影图片
//        menu.setShadowDrawable(R.color.colorAccent);
        // 设置滑动菜单视图的宽度
        //计算屏幕的宽度占屏幕的80%设置SlidingMenu菜单的宽度
        WindowManager wm = getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        float widthPixels = (float) displayMetrics.widthPixels;
        double v = (int) widthPixels * 0.8;
        menu.setBehindWidth((int)v);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
       //使SlidingMenu附加在Activity上
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.menu_frame_left);
        //使用fragment做为侧滑页面
        FragmentManager manager = getSupportFragmentManager();
        //这里的R.id.id_left_menu_frame
        manager.beginTransaction().replace(R.id.id_left_menu_frame, new TestFragment()).commit();

    }
}
