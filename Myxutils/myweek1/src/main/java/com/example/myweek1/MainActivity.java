package com.example.myweek1;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.viewpagerindicator.TabPageIndicator;

import adapter.Fragment1;

public class MainActivity extends FragmentActivity {
    private static final String[] CONTENT = new String[] { "头条", "娱乐", "体育", "财经", "科技", "汽车","NBA","热点","北京","视屏" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }

    private void initview() {
        TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
        //获取viewpager控件
        ViewPager vp = (ViewPager) findViewById(R.id.vp);
        //创建适配器viewpage
        Fragment1 myapadter = new
                Fragment1(getSupportFragmentManager(),CONTENT);
        //这个可以设置也可以不设置 是为了防止卡顿设置缓存的
//        vp.setOffscreenPageLimit(4); //缓存4个页面
        //适配viewpager
        vp.setAdapter(myapadter);
        //把indicator和viewpager的两者绑定在一起
        indicator.setViewPager(vp);

    }
}
