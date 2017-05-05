package com.example.myproject2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView textView;
    private String mXingqi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        mXingqi = intent.getStringExtra("xingqi");
        initView();

    }

    private void initView() {
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(mXingqi);
    }
}
