package com.example.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 作者:侯亮亮
 * 时间:2017/4/14 7:53
 * 类描述:登录页面1
 */

public class MainActivity_loginshouye extends Activity implements View.OnClickListener {


    private TextView login_top;
    private Button login_btn_phone;
    private Button login_btn_new;
    private TextView login_line_tv;
    private ImageView login_iv_qq;
    private ImageView login_iv_xl;
    private ImageView login_iv_qqwb;
    private ImageView login_iv_rr;
    private ImageView mIm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_01_frag);
        initView();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.login_btn_phone:
                Intent intent = new Intent(MainActivity_loginshouye.this, ManiActivity_shoujidenglv.class);
                startActivity(intent);
                break;
            case R.id.login_btn_new:
                Intent intent2 = new Intent(MainActivity_loginshouye.this, ManiActivity_phonezhuce.class);
                startActivity(intent2);
                break;

            case R.id.login_im_fanhui:
                finish();
                break;
        }
    }

    private void initView() {
        login_top = (TextView) findViewById(R.id.login_top);
        login_btn_phone = (Button) findViewById(R.id.login_btn_phone);
        login_btn_new = (Button) findViewById(R.id.login_btn_new);
        login_line_tv = (TextView) findViewById(R.id.login_line_tv);
        login_iv_qq = (ImageView) findViewById(R.id.login_iv_qq);
        login_iv_xl = (ImageView) findViewById(R.id.login_iv_xl);
        login_iv_qqwb = (ImageView) findViewById(R.id.login_iv_qqwb);
        login_iv_rr = (ImageView) findViewById(R.id.login_iv_rr);
        mIm = (ImageView) findViewById(R.id.login_im_fanhui);
        mIm.setOnClickListener(this);
        login_btn_phone.setOnClickListener(this);
        login_btn_new.setOnClickListener(this);
    }
}
