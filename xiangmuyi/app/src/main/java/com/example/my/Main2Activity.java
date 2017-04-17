package com.example.my;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import java.util.List;

import Bean.UserBean;
import Bean.titleBean;
import Utils.URL;
import Utils.UiUtils;
import Utils.titleHttp;
import adapter.titleadapter;
import fragment.TestFragment;
/**
*作者:侯亮亮
*时间:2017/4/13 20:51
*类描述:主页面
*/
public class Main2Activity extends FragmentActivity {

    private Fragment mContent;
    private ViewPager mVp;

    Handler hh=new Handler(){


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String titledata = (String) msg.obj;
            Gson gson = new Gson();
            titleBean titleBean = gson.fromJson(titledata, titleBean.class);
            List<titleBean.ResultBean.DateBean>   mDate =  titleBean.getResult().getDate();
//            Intent intent = new Intent(Main2Activity.this, MainActivityaddtitle.class);
//            intent.putExtra("data", (Parcelable) mDate);
//            startActivity(intent);
            mVp.setAdapter(new titleadapter(getSupportFragmentManager(),mDate));
        }
    };
    private ImageView mIm;
    private SlidingMenu mMenu;
    private ImageView mIm1;
    private Intent mIntent;
    private String mName;
    private TextView mLoginname;
    private ImageView mCe_cle_im;
    private RelativeLayout mRr;
    private RelativeLayout mLea;
    private String mPh;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //切换主题必须放在onCreate()之前
        int theme;
        if (savedInstanceState == null) {
            theme = UiUtils.getAppTheme(Main2Activity.this);
        } else {
            theme = savedInstanceState.getInt("theme");
        }
       setTheme(theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initview();
        initSlidingMenu();
        mIntent = getIntent();
        if (mIntent!=null){
            mName = mIntent.getStringExtra("name");
            mPh = mIntent.getStringExtra("ph");
        }
        int sigin = mIntent.getIntExtra("sigin",3);
        if (sigin==1){

            mRr.setVisibility(View.VISIBLE);
            mLea.setVisibility(View.GONE);

            try {
                WhereBuilder wb = WhereBuilder.b();
                wb.and("phone", "=", mPh);
                List<UserBean> name1 = MyApp.mDb.selector(UserBean.class).where(wb).findAll();
                Bitmap bitmap = name1.get(0).getBitmap();
                mIm.setImageBitmap(bitmap);
                mCe_cle_im.setImageBitmap(bitmap);
                mLoginname.setText(mName);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }else if (sigin==2){
            mLoginname.setText(mName);
        }


    }

    private void initview() {
        View inflate = getLayoutInflater().inflate(R.layout.frag1, null);
        mRr = (RelativeLayout) inflate.findViewById(R.id.ce_relalayout_login);
        mCe_cle_im = (ImageView) inflate.findViewById(R.id.ce_cle_im);
        mLoginname = (TextView) inflate.findViewById(R.id.name);

        mLea = (RelativeLayout) inflate.findViewById(R.id.cehua_relalayout);
       TabLayout act_tablayout= (TabLayout) findViewById(R.id.tablayout);
        mVp = (ViewPager) findViewById(R.id.viewPager);
        mIm = (ImageView) findViewById(R.id.act_im);
        mIm1 = (ImageView) findViewById(R.id.act_cehua);


        mIm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mMenu.toggle();
            }
        });
        new titleHttp(hh, URL.url).start();
        // 将ViewPager和TabLayout绑定
        act_tablayout.setupWithViewPager(mVp);
        // 设置tab文本的没有选中（第一个参数）和选中（第二个参数）的/颜色
       act_tablayout.setTabTextColors(getResources().getColor(R.color.black), Color.RED);

        mIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MainActivityaddtitle.class);
                startActivity(intent);
            }
        });
    }
    private void initSlidingMenu() {
        mMenu = new SlidingMenu(this);
        //设置左滑菜单
        mMenu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        //设置要使菜单滑动，触碰屏幕的范围
        mMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
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
        mMenu.setBehindWidth((int)v);

        // 设置渐入渐出效果的值
        mMenu.setFadeDegree(0.35f);
        //使SlidingMenu附加在Activity上
        mMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        mMenu.setMenu(R.layout.menu_frame_left);
        mMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        //使用fragment做为侧滑页面
        FragmentManager manager = getSupportFragmentManager();
        //这里的R.id.id_left_menu_frame是R.layout.menu_frame_left里的id
        manager.beginTransaction().replace(R.id.id_left_menu_frame, new TestFragment()).commit();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences mUserList = getSharedPreferences("mUserList", MODE_PRIVATE);
        String data = mUserList.getString("data",null);
//        Log.d("TAG",data);
    }
}
