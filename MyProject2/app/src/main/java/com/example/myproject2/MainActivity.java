package com.example.myproject2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ClockView clock_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        clock_view = (ClockView) findViewById(R.id.clock_view);
        clock_view.setOnLetterChangedListener(new ClockView.getdata() {
            @Override
            public void data(String mFormat1) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("xingqi",mFormat1);
                Toast.makeText(MainActivity.this, mFormat1, Toast.LENGTH_SHORT).show();

                startActivity(intent);
            }
        });




    }
}
