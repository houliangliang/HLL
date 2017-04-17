package com.example.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.db.Selector;
import org.xutils.ex.DbException;

import java.util.List;

import Bean.UserBean;

/**
 * 作者:侯亮亮
 * 时间:2017/4/14 8:36
 * 类描述:登录页面2
 */

public class ManiActivity_shoujidenglv extends Activity implements View.OnClickListener {


    private ImageView login_im_fanhui;
    private TextView login_top;
    private ImageView login_02_iv_phone;
    private EditText login_02_phone;
    private ImageView login_02_iv_psw;
    private EditText login_02_psw;
    private TextView login_02_tv_zhuce;
    private TextView login_02_tv_wanji;
    private Button login_02_btn_login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_02_frag);
        initView();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_02_tv_zhuce:
                Intent intent2 = new Intent(ManiActivity_shoujidenglv.this, ManiActivity_phonezhuce.class);
                startActivity(intent2);

                break;
            case R.id.login_02_tv_wanji:
                Intent intent = new Intent(ManiActivity_shoujidenglv.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.login_02_btn_login:
                if (login_02_phone.getText().toString().trim().length()==11){
                 if (login_02_psw.getText().toString().trim().length()>6&&login_02_psw.getText().toString().trim().length()<20){
                     try {
                         List<UserBean> select = MyApp.mDb.selector(UserBean.class).findAll();
                         if (select!=null){
                         for(UserBean  userBean:select){

                             if (userBean.getPhone().equals(login_02_phone.getText().toString().trim())&&userBean.getLoginpaw().equals(login_02_psw.getText().toString().trim())){
                                 Toast.makeText(ManiActivity_shoujidenglv.this,"登录成功",Toast.LENGTH_SHORT).show();

                             }else {
                                 Toast.makeText(ManiActivity_shoujidenglv.this,"账号或密码错误",Toast.LENGTH_SHORT).show();

                             }}
                         }else {
                             Toast.makeText(ManiActivity_shoujidenglv.this,"账号不存在",Toast.LENGTH_SHORT).show();

                         }
                     } catch (DbException e) {
                         e.printStackTrace();
                     }
                 }else {
                     Toast.makeText(ManiActivity_shoujidenglv.this,"请输入合法密码",Toast.LENGTH_SHORT).show();

                 }
                }else {
                    Toast.makeText(ManiActivity_shoujidenglv.this,"请输入合法的手机号",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.login_im_fanhui2:
                finish();
                break;
        }


}

    private void initView() {
        login_im_fanhui = (ImageView) findViewById(R.id.login_im_fanhui2);
        login_top = (TextView) findViewById(R.id.login_top);
        login_02_iv_phone = (ImageView) findViewById(R.id.login_02_iv_phone);
        login_02_phone = (EditText) findViewById(R.id.login_02_phone);
        login_02_iv_psw = (ImageView) findViewById(R.id.login_02_iv_psw);
        login_02_psw = (EditText) findViewById(R.id.login_02_psw);
        login_02_tv_zhuce = (TextView) findViewById(R.id.login_02_tv_zhuce);
        login_02_tv_wanji = (TextView) findViewById(R.id.login_02_tv_wanji);
        login_02_btn_login = (Button) findViewById(R.id.login_02_btn_login);
        login_im_fanhui.setOnClickListener(this);
        login_02_btn_login.setOnClickListener(this);
        login_02_tv_zhuce.setOnClickListener(this);
        login_02_tv_wanji.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String phone = login_02_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String psw = login_02_psw.getText().toString().trim();
        if (TextUtils.isEmpty(psw)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
