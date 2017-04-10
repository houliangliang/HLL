package com.example.myxutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import Utils.http;
import Utils.http2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv= (TextView) findViewById(R.id.tv);
        ImageView im= (ImageView) findViewById(R.id.imageView);
        x.view().inject(this);
//        http.get(tv);
        http2.getoptions(im);
    }
}
