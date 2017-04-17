package com.example.my;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Bean.phone;
import Utils.MyTime;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;


/**
 * date:2017/4/13.
 * author:梁坤 lenovo
 * 类描述：
 */

public class Mainactivity2 extends Activity implements View.OnClickListener {

    private TextView shoujitextview;
    private EditText yanzhengma;
    private EditText xinmima;
    private Button buttxiayibu;
    private Button chongxinfasong;
    private MyTime mytime = null;
    private boolean flag = true;
    private String mKe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shuruyanzheng);

        initview();


        //点击事件
        dianji();
        //获取手机号
        fuzhi();

    }

    private void fuzhi() {
        Bundle bundle  = this.getIntent().getExtras();
        mKe = (String) bundle.get("dianhua");
        shoujitextview.setText(mKe);

        SMSSDK.initSDK(this, "1cf74badd33ac", " 4a3a2074d4dc98b9c2f8608f9de6e7ba");
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
        SMSSDK.getVerificationCode("86", mKe);

    }


    private void dianji() {
        chongxinfasong.setOnClickListener(this);
        buttxiayibu.setOnClickListener(this);

    }

    //找控件
    private void initview() {
        shoujitextview = (TextView) findViewById(R.id.shoujitextview);
        yanzhengma = (EditText) findViewById(R.id.yanzhengma);
        xinmima = (EditText) findViewById(R.id.xinmima);
        buttxiayibu = (Button) findViewById(R.id.buttxyb);
        chongxinfasong = (Button) findViewById(R.id.chongxinfasong);
        chongxinfasong.setClickable(false);

        mytime = new MyTime(60000,1000,chongxinfasong);
        mytime.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.chongxinfasong :
                //计时
                chongxinfasong.setClickable(false);
                SMSSDK.getVerificationCode("86", mKe);
                mytime = new MyTime(60000,1000,chongxinfasong);
                mytime.start();
                Toast.makeText(Mainactivity2.this,"请注意查看短信",Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttxyb:
                if(!TextUtils.isEmpty(yanzhengma.getText().toString().trim())){
                    if(yanzhengma.getText().toString().trim().length()==4){
                        String iCord = yanzhengma.getText().toString().trim();
                        SMSSDK.submitVerificationCode("86", mKe, iCord);
                        flag = false;
                    }else{
                        Toast.makeText(Mainactivity2.this, "请输入完整验证码", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(Mainactivity2.this, "请输入验证码", Toast.LENGTH_LONG).show();
                }
                break;
        }
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
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    //服务器验证码发送成功
                    Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                    //返回支持发送验证码的国家列表
                    Toast.makeText(getApplicationContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
                }
            } else {
                if(flag){
                    Toast.makeText(Mainactivity2.this, "验证码获取失败，请重新获取", Toast.LENGTH_SHORT).show();
                }else{
                    ((Throwable) data).printStackTrace();
                    Toast.makeText(Mainactivity2.this, "验证码错误", Toast.LENGTH_SHORT).show();
                }


            }

        }};

    }
