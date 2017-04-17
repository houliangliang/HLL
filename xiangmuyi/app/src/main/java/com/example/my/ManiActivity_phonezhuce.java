package com.example.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 作者:侯亮亮
 * 时间:2017/4/14 8:52
 * 类描述:登录页面3
 */

public class ManiActivity_phonezhuce extends Activity implements View.OnClickListener {


    private ImageView login_im_fanhui3;
    private TextView login_top;
    private TextView denglu;
    private TextView text;
    private EditText edittext;
    private RadioButton radiobutton;
    private RadioButton xieyi;
    private RadioGroup radio;
    private Button xiayibu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_03_frag);
        initView();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.xiayibu:
                if (edittext.getText().toString().trim()!=null){
                   if (radiobutton.isChecked()==true){
                       if (edittext.getText().toString().trim().length()==11){
                           Intent intent = new Intent(ManiActivity_phonezhuce.this, MainActivityphonelogin.class);
                           String trim = edittext.getText().toString();
//                           Toast.makeText(ManiActivity_phonezhuce.this,submit(),Toast.LENGTH_SHORT).show();
                           intent.putExtra("phone",trim);
                           startActivity(intent);
                       }else {
                           Toast.makeText(ManiActivity_phonezhuce.this,"手机号不合法",Toast.LENGTH_SHORT).show();
                       }
                   }else{
                       Toast.makeText(ManiActivity_phonezhuce.this,"协议必须遵守",Toast.LENGTH_SHORT).show();
                   }
                }else {
                    Toast.makeText(ManiActivity_phonezhuce.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.xieyi:
                Intent intent3 = new Intent(ManiActivity_phonezhuce.this, activity_xieyi.class);

                startActivity(intent3);
                break;
            case R.id.denglu:
                Intent intent2 = new Intent(ManiActivity_phonezhuce.this, ManiActivity_shoujidenglv.class);

                startActivity(intent2);
                break;
            case R.id.login_im_fanhui3:
               finish();
                break;

        }
    }



    private void initView() {
        login_im_fanhui3 = (ImageView) findViewById(R.id.login_im_fanhui3);
        login_top = (TextView) findViewById(R.id.login_top);
        denglu = (TextView) findViewById(R.id.denglu);
        text = (TextView) findViewById(R.id.text);
        edittext = (EditText) findViewById(R.id.edittext2);
        radiobutton = (RadioButton) findViewById(R.id.radiobutton);
        xieyi = (RadioButton) findViewById(R.id.xieyi);
        radio = (RadioGroup) findViewById(R.id.radio);
        xiayibu = (Button) findViewById(R.id.xiayibu);
        xieyi.setOnClickListener(this);
        xiayibu.setOnClickListener(this);
        denglu.setOnClickListener(this);
        login_im_fanhui3.setOnClickListener(this);
    }

    private String submit() {
        // validate
        String edittextString = edittext.getText().toString().trim();
        if (TextUtils.isEmpty(edittextString)) {
            if (TextUtils.isEmpty(edittextString)) {
                Toast.makeText(this, "+86:输入手机号", Toast.LENGTH_SHORT).show();
                return edittextString;
            }
        }

        // TODO validate success, do something

   return null;
    }
}
