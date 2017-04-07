package com.example.my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import Utils.MyAsync;
import Utils.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();
    }

    private void initview() {
        ListView lv= (ListView) findViewById(R.id.lv);
        MyAsync myAsync = new MyAsync(lv, this);
        myAsync.execute(URL.url);
    }
}
