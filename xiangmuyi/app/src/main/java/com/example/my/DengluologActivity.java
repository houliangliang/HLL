package com.example.my;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.ex.DbException;

import Bean.UserBean;

/**
*作者:侯亮亮
*时间:2017/4/14 9:05
*类描述:登录页面4设置个人信息
*/
public class DengluologActivity extends Activity {

    private ImageView log_img;
    private EditText ed_name;
    private ImageView log_img2;
    private TextView mWancheng;
    private Bitmap mMap;
    private String mZhanghao;
    private String mMima;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dengluolog);
        initView();
        Intent intent = getIntent();
        mZhanghao = intent.getStringExtra("zhanghao");
        mMima = intent.getStringExtra("mima");
    }

    private void initView() {
        log_img = (ImageView) findViewById(R.id.log_img);
        ed_name = (EditText) findViewById(R.id.ed_name);
        mWancheng = (TextView) findViewById(R.id.wangcheng);
        mWancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_name.getText().toString().trim()!=null&&!"".equals(ed_name.getText().toString().trim())){
                    if (mMap!=null){
                        Log.d("TAG",ed_name.getText().toString());
                        UserBean userBean = new UserBean();
                        userBean.setBitmap(mMap);
                        userBean.setName(ed_name.getText().toString().trim());
                        userBean.setPhone(mZhanghao);
                        userBean.setLoginpaw(mMima);

                        try {
                          MyApp.mDb.save(userBean);

                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(DengluologActivity.this, Main2Activity.class);
                        intent.putExtra("sigin",1);
                        intent.putExtra("name",ed_name.getText().toString().trim());
                        intent.putExtra("ph",mZhanghao);
                        startActivity(intent);
                    }else {
                        UserBean userBean = new UserBean();
                        userBean.setName(ed_name.getText().toString().trim());
                        userBean.setPhone(mZhanghao);
                        userBean.setLoginpaw(mMima);

                        try {
                            MyApp.mDb.save(userBean);

                        } catch (DbException e) {
                            e.printStackTrace();
                            Log.d("TAG", e.toString()+"6666666666666666");
                        }

                    } Intent intent = new Intent(DengluologActivity.this, Main2Activity.class);
                    intent.putExtra("sigin",2);
                    intent.putExtra("name",ed_name.getText().toString().trim());
                    intent.putExtra("ph",mZhanghao);
                    startActivity(intent);
                }else {
                    Toast.makeText(DengluologActivity.this,"请输入名称",Toast.LENGTH_SHORT).show();

                }
            }
        });
        log_img2 = (ImageView) findViewById(R.id.log_img2);
        log_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getActivity(), ZhaoXiangActivity.class);
                //startActivity(intent);
                final String[] items = new String[] { "从相册选择", "相机", "取消"};
                AlertDialog.Builder dialog=new AlertDialog.Builder(DengluologActivity.this);
                dialog.setIcon(R.mipmap.ic_launcher)
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    Intent intent2=new Intent();
                                       intent2.setAction(intent2.ACTION_PICK);
                                      intent2.setType("image/*");
                                      startActivityForResult(intent2,2);
                                }else if (which == 1) {
                                     Intent intent1=new Intent();
                                        intent1.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                                      startActivityForResult(intent1,101);
                                }else {
                                    dialog.dismiss();
                                }
                            }
                        });
                dialog.create().show();

            }
        });
    }

    private void submit() {
        // validate
        String name = ed_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "name不能为空", Toast.LENGTH_SHORT).show();
            return;
        }




    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==3){
            Uri yri=data.getData();

            log_img.setImageURI(yri);
       Toast.makeText(DengluologActivity.this,"走我了",Toast.LENGTH_SHORT).show();
        }

        if(requestCode==101){

            mMap = data.getParcelableExtra("data");
            Toast.makeText(DengluologActivity.this, mMap +"",Toast.LENGTH_SHORT).show();
           log_img.setImageBitmap(mMap);


        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
