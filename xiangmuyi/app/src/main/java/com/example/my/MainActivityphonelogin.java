package com.example.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.ex.DbException;

import Bean.UserBean;
import Utils.MyTime;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class MainActivityphonelogin extends Activity implements View.OnClickListener {

    private ImageView phonelogin_im;
    private TextView phonelogin_title;
    private TextView phonelogin_send;
    private EditText phonelogin_yanzhen;
    private TextView phonelogin_xian;
    private ImageView phonelogin_im2;
    private Button phonelogin_time;
    private EditText phonelogin_mima;
    private TextView phonelogin_xian2;
    private ImageView phonelogin_im3;
    private Button login;
    private MyTime mytime;
    private String mPhone;
    private boolean mFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activityphonelogin);
        initView();
        Intent intent = getIntent();
        mPhone = intent.getStringExtra("phone");
        phonelogin_send.setText("已向手机"+ mPhone +"发送验证码");
        phonelogin_time.setClickable(false);

        mytime = new MyTime(60000,1000,phonelogin_time);
        mytime.start();
        initSMSSDK();
    }

    private void initView() {
        phonelogin_im = (ImageView) findViewById(R.id.phonelogin_im);
        phonelogin_title = (TextView) findViewById(R.id.phonelogin_title);
        phonelogin_send = (TextView) findViewById(R.id.phonelogin_send);
        phonelogin_yanzhen = (EditText) findViewById(R.id.phonelogin_yanzhen);
        phonelogin_xian = (TextView) findViewById(R.id.phonelogin_xian);
        phonelogin_im2 = (ImageView) findViewById(R.id.phonelogin_im2);
        phonelogin_time = (Button) findViewById(R.id.phonelogin_time);
        phonelogin_mima = (EditText) findViewById(R.id.phonelogin_mima);
        phonelogin_xian2 = (TextView) findViewById(R.id.phonelogin_xian2);
        phonelogin_im3 = (ImageView) findViewById(R.id.phonelogin_im3);
        login = (Button) findViewById(R.id.login);
        phonelogin_im.setOnClickListener(this);
        phonelogin_time.setOnClickListener(this);
        login.setOnClickListener(this);
    }
    private void initSMSSDK() {

        SMSSDK.initSDK(this, "1d10a1de21c1c", "457eb1fbfab8c5e98a5cf1a62eff3c3a");
        EventHandler eh=new EventHandler(){

            @Override
            public void afterEvent(int event, int result, Object data) {

                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }

        };
        SMSSDK.registerEventHandler(eh);
        SMSSDK.getVerificationCode("86", mPhone);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.phonelogin_time:
                phonelogin_time.setClickable(false);
                SMSSDK.getVerificationCode("86",mPhone);
                mytime = new MyTime(60000,1000,phonelogin_time);
                mytime.start();
                break;
            case R.id.login:
                if(!TextUtils.isEmpty(phonelogin_yanzhen.getText().toString().trim())){
                    if(phonelogin_yanzhen.getText().toString().trim().length()==4){
                        if (!TextUtils.isEmpty(phonelogin_mima.getText().toString().trim())){
                        if (phonelogin_mima.getText().toString().trim().length()<20&&phonelogin_mima.getText().toString().trim().length()>6){
                        String iCord = phonelogin_yanzhen.getText().toString().trim();
                        SMSSDK.submitVerificationCode("86", mPhone, iCord);
                        mFlag = false;
                        }else {
                            Toast.makeText(MainActivityphonelogin.this, "请输入正确密码", Toast.LENGTH_LONG).show();
                        }}else {
                            Toast.makeText(MainActivityphonelogin.this, "密码不能为空", Toast.LENGTH_LONG).show();

                        }
                    }else{
                        Toast.makeText(MainActivityphonelogin.this, "请输入完整验证码", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivityphonelogin.this, "请输入验证码", Toast.LENGTH_LONG).show();
                }


                break;
            case R.id.phonelogin_im:
                finish();
                break;
        }
    }

    private void submit() {
        // validate
        String yanzhen = phonelogin_yanzhen.getText().toString().trim();
        if (TextUtils.isEmpty(yanzhen)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        String mima = phonelogin_mima.getText().toString().trim();
        if (TextUtils.isEmpty(mima)) {
            Toast.makeText(this, "请输入密码（6-20位英文或数字）", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            Log.e("event", "event="+event);
            if (result == SMSSDK.RESULT_COMPLETE) {
                //短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功,验证通过
                    Toast.makeText(getApplicationContext(), "验证码校验成功", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivityphonelogin.this, DengluologActivity.class);
//                    UserBean userBean = new UserBean();
//                    userBean.setPhone(mPhone);
//                    userBean.setLoginpaw(phonelogin_mima.getText().toString().trim());
//                    try {
//                        MyApp.mDb.save(userBean);
//                    } catch (DbException e) {
//                        e.printStackTrace();
//                    }
                    intent.putExtra("zhanghao",mPhone);
                    intent.putExtra("mima",phonelogin_mima.getText().toString().trim());

                    startActivity(intent);
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    //服务器验证码发送成功
                    Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                    //返回支持发送验证码的国家列表
                    Toast.makeText(getApplicationContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
                }
            } else {
                if(mFlag){
                    Toast.makeText(MainActivityphonelogin.this, "验证码获取失败，请重新获取", Toast.LENGTH_SHORT).show();
                }else{
                    ((Throwable) data).printStackTrace();
                    Toast.makeText(MainActivityphonelogin.this, "验证码错误", Toast.LENGTH_SHORT).show();
                }


            }

        }};

}
