package com.example.my;

import android.app.Activity;
import android.content.Intent;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;


public class MainActivity extends Activity implements View.OnClickListener {

    private Button buttxiayibu;
    private EditText shoujihao;
    private String shouji;
//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what == 1)
//                Toast.makeText(MainActivity.this, "回调完成", Toast.LENGTH_SHORT).show();
//            else if (msg.what == 2)
//                Toast.makeText(MainActivity.this, "提交验证码成功", Toast.LENGTH_SHORT).show();
//            else if (msg.what == 3)
//                Toast.makeText(MainActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
//            else if (msg.what == 4)
//                Toast.makeText(MainActivity.this, "返回支持发送国家验证码", Toast.LENGTH_SHORT).show();
//        }
//    };
    private ImageView dianjiqingkong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shurushoujihao);

        //查找控件
        initview();


    }


    private void initview() {
        dianjiqingkong = (ImageView) findViewById(R.id.qingkong);
        buttxiayibu = (Button) findViewById(R.id.buttxyba);
        shoujihao = (EditText) findViewById(R.id.shoujihaoed);

        //当前edittext获取焦点时  弹出数字键盘
        shoujihao.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        buttxiayibu.setOnClickListener(this);
        dianjiqingkong.setOnClickListener(this);
        shouji = shoujihao.getText().toString();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttxyba:
                if(TextUtils.isEmpty(shoujihao.getText())){
                    Toast.makeText(MainActivity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                }else if(shoujihao.length()!=11){
                    Toast.makeText(MainActivity.this,"手机号是11位",Toast.LENGTH_SHORT).show();
                    //Toast.makeText(MainActivity.this,shoujihao.getText().toString(),Toast.LENGTH_SHORT).show();

                } else{
                    Intent intent = new Intent(MainActivity.this,Mainactivity2.class);
                    intent.putExtra("dianhua",shoujihao.getText().toString());
//                    huidiao(shoujihao.getText().toString());
                    startActivity(intent);

                    finish();
                }


                break;

            case R.id.qingkong:
                //点击清空edittext内容
                shoujihao.getText().clear();
                Toast.makeText(MainActivity.this,"请重新输入",Toast.LENGTH_SHORT).show();
                break;
        }
    }
//    private void huidiao(String phone ) {
//        EventHandler eh = new EventHandler() {
//            @Override
//            public void afterEvent(int event, int result, Object data) {
//                if (result == SMSSDK.RESULT_COMPLETE) {
//                    handler.sendEmptyMessage(1);
//                    //回调完成
//                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
//                        //提交验证码成功
//                        handler.sendEmptyMessage(2);
//
//                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
//                        //获取验证码成功
//                        handler.sendEmptyMessage(3);
//
//                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
//                        //返回支持发送验证码的国家列表
//                        handler.sendEmptyMessage(4);
//                    }
//                } else {
//                    ((Throwable) data).printStackTrace();
//                }
//            }
//        };
//        SMSSDK.initSDK(this, "1cf7513f0d15c", " ab4953e62b52c51a518f4f9741b14ff5");
//        SMSSDK.registerEventHandler(eh); //注册短信回调
//
//        SMSSDK.getVerificationCode("86", phone);//请求获取短信验证码
//    }

}
