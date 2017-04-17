package com.example.my;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import Bean.titleBean;
import Bean.titleBean.ResultBean.DateBean;
import Utils.URL;
import Utils.UiUtils;
import Utils.titleHttp;
import adapter.OtherAdapter;
import view.MyGridView;

/**
*作者:侯亮亮
*时间:2017/4/13 20:51
*类描述:title添加管理模块页面
*/
public class MainActivityaddtitle extends Activity implements AdapterView.OnItemClickListener {
    private MyGridView mUserGv, mOtherGv;
    private List<String> mUserList = new ArrayList<>();


    private List<String> mOtherList = new ArrayList<>();
    private OtherAdapter mUserAdapter, mOtherAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int theme;
        if (savedInstanceState == null) {
            theme = UiUtils.getAppTheme(MainActivityaddtitle.this);
        } else {
            theme = savedInstanceState.getInt("theme");
        }
        setTheme(theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activityaddtitle);
        initView();
    }
    public void initView() {
        mUserGv = (MyGridView) findViewById(R.id.userGridView);
        mOtherGv = (MyGridView) findViewById(R.id.otherGridView);
//        Intent intent = getIntent();
//        ArrayList<DateBean> date = (ArrayList<DateBean>) intent.getParcelableArrayListExtra("data");
//        for (int i=0;i<date.size();i++){
//            mUserList.add();
//        }.

//        mUserList.add();
//        mUserList.add("热点");
//        mUserList.add("上海");
//        mUserList.add("时尚");
//        mUserList.add("科技");
//        mUserList.add("体育");
//        mUserList.add("军事");
//        mUserList.add("财经");
//        mUserList.add("网络");

//        mOtherList.add("汽车");
//        mOtherList.add("房产");
//        mOtherList.add("社会");
//        mOtherList.add("情感");
//        mOtherList.add("女人");
//        mOtherList.add("旅游");
//        mOtherList.add("健康");
//        mOtherList.add("美女");
//        mOtherList.add("游戏");
//        mOtherList.add("数码");
//        mOtherList.add("娱乐");
//        mOtherList.add("探索");
        Handler hh=new Handler(){  @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String titledata = (String) msg.obj;
            Gson gson = new Gson();
            titleBean titleBean = gson.fromJson(titledata, titleBean.class);
            List<titleBean.ResultBean.DateBean> mDate = titleBean.getResult().getDate();
            for (int i=0;i<mDate.size();i++){
            mUserList.add(mDate.get(i).getTitle());
        }

            mUserAdapter = new OtherAdapter(MainActivityaddtitle.this, mUserList,true);
            mOtherAdapter = new OtherAdapter(MainActivityaddtitle.this, mOtherList,false);
            mUserGv.setAdapter(mUserAdapter);
            mOtherGv.setAdapter(mOtherAdapter);
            mUserGv.setOnItemClickListener(MainActivityaddtitle.this);
            mOtherGv.setOnItemClickListener(MainActivityaddtitle.this);
            SharedPreferences mUser = getSharedPreferences("mUserList", MODE_PRIVATE);
            SharedPreferences.Editor edit = mUser.edit();
            edit.putString("data",mUserList.toString());
            edit.commit();
        }
        };
        new titleHttp(hh, URL.url).start();
    }
    /**
     *获取点击的Item的对应View，
     *因为点击的Item已经有了自己归属的父容器MyGridView，所有我们要是有一个ImageView来代替Item移动
     * @param view
     * @return
     */
    private ImageView getView(View view) {
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        Bitmap cache = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        ImageView iv = new ImageView(this);
        iv.setImageBitmap(cache);
        return iv;
    }
    /**
     * 获取移动的VIEW，放入对应ViewGroup布局容器
     * @param viewGroup
     * @param view
     * @param initLocation
     * @return
     */
    private View getMoveView(ViewGroup viewGroup, View view, int[] initLocation) {
        int x = initLocation[0];
        int y = initLocation[1];
        viewGroup.addView(view);
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParams.leftMargin = x;
        mLayoutParams.topMargin = y;
        view.setLayoutParams(mLayoutParams);
        return view;
    }
    /**
     * 创建移动的ITEM对应的ViewGroup布局容器
     * 用于存放我们移动的View
     */
    private ViewGroup getMoveViewGroup() {
        //window中最顶层的view
        ViewGroup moveViewGroup = (ViewGroup) getWindow().getDecorView();
        LinearLayout moveLinearLayout = new LinearLayout(this);
        moveLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        moveViewGroup.addView(moveLinearLayout);
        return moveLinearLayout;
    }
    /**
     * 点击ITEM移动动画
     *
     * @param moveView
     * @param startLocation
     * @param endLocation
     * @param moveChannel
     * @param clickGridView
     */
    private void MoveAnim(View moveView, int[] startLocation, int[] endLocation, final String moveChannel,
                          final GridView clickGridView, final boolean isUser) {
        int[] initLocation = new int[2];
        //获取传递过来的VIEW的坐标
        moveView.getLocationInWindow(initLocation);
        //得到要移动的VIEW,并放入对应的容器中
        final ViewGroup moveViewGroup = getMoveViewGroup();
        final View mMoveView = getMoveView(moveViewGroup, moveView, initLocation);
        //创建移动动画
        TranslateAnimation moveAnimation = new TranslateAnimation(
                startLocation[0], endLocation[0], startLocation[1],
                endLocation[1]);
        moveAnimation.setDuration(300L);//动画时间
        //动画配置
        AnimationSet moveAnimationSet = new AnimationSet(true);
        moveAnimationSet.setFillAfter(false);//动画效果执行完毕后，View对象不保留在终止的位置
        moveAnimationSet.addAnimation(moveAnimation);
        mMoveView.startAnimation(moveAnimationSet);
        moveAnimationSet.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                moveViewGroup.removeView(mMoveView);
                // 判断点击的是DragGrid还是OtherGridView
                if (isUser) {
                    mOtherAdapter.setVisible(true);
                    mOtherAdapter.notifyDataSetChanged();
                    mUserAdapter.remove();
                } else {
                    mUserAdapter.setVisible(true);
                    mUserAdapter.notifyDataSetChanged();
                    mOtherAdapter.remove();
                }
            }
        });
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        switch (parent.getId()) {
            case R.id.userGridView:
                //position为 0，1 的不可以进行任何操作
                if (position != 0 && position != 1) {
                    final ImageView moveImageView = getView(view);
                    if (moveImageView != null) {
                        TextView newTextView = (TextView) view.findViewById(R.id.text_item);
                        final int[] startLocation = new int[2];
                        newTextView.getLocationInWindow(startLocation);
                        final String channel = ((OtherAdapter) parent.getAdapter()).getItem(position);//获取点击的频道内容
                       mUserList.remove(position);
                        mOtherAdapter.setVisible(false);
                        //添加到最后一个
                        mOtherAdapter.addItem(channel);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                try {
                                    int[] endLocation = new int[2];
                                    //获取终点的坐标
                                    mOtherGv.getChildAt(mOtherGv.getLastVisiblePosition()).getLocationInWindow(endLocation);
                                    MoveAnim(moveImageView, startLocation, endLocation, channel, mUserGv, true);
                                } catch (Exception localException) {
                                }
                            }
                        }, 50L);
                    }
                }
                break;
            case R.id.otherGridView:
                final ImageView moveImageView = getView(view);
                if (moveImageView != null) {
                    TextView newTextView = (TextView) view.findViewById(R.id.text_item);
                    final int[] startLocation = new int[2];
                    newTextView.getLocationInWindow(startLocation);
                    final String channel = ((OtherAdapter) parent.getAdapter()).getItem(position);
                   mUserList.add(mOtherList.get(position));
                    mUserAdapter.setVisible(false);
                    //添加到最后一个
                    mUserAdapter.addItem(channel);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            try {
                                int[] endLocation = new int[2];
                                //获取终点的坐标
                                mUserGv.getChildAt(mUserGv.getLastVisiblePosition()).getLocationInWindow(endLocation);
                                MoveAnim(moveImageView, startLocation, endLocation, channel, mOtherGv,false);
                            } catch (Exception localException) {
                            }
                        }
                    }, 50L);// 50 毫秒
                }
                break;
            default:
                break;
        }
    }
}
