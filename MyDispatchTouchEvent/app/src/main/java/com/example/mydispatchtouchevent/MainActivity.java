package com.example.mydispatchtouchevent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView btn;
    private RelativeLayout activity_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initdata();

    }

    private void initdata() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i=0;i<20;i++){
            strings.add("动作"+i);
        }
        btn.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,strings));
    }

    private void initView() {
        btn = (ListView) findViewById(R.id.btn);
        activity_main = (RelativeLayout) findViewById(R.id.activity_main);
       btn.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View v, MotionEvent event) {
               switch (event.getAction()){
                   case MotionEvent.ACTION_DOWN:
                  Toast.makeText(MainActivity.this, "按下动作", Toast.LENGTH_SHORT).show();

                       break;
                   case MotionEvent.ACTION_UP:
                  Toast.makeText(MainActivity.this, "抬起的动作", Toast.LENGTH_SHORT).show();

                       break;
                   case MotionEvent.ACTION_MOVE:
                  Toast.makeText(MainActivity.this, "移动动作", Toast.LENGTH_SHORT).show();

                       break;
                   case MotionEvent.ACTION_OUTSIDE:
                  Toast.makeText(MainActivity.this, "取消动作", Toast.LENGTH_SHORT).show();

                       break;
                   case MotionEvent.ACTION_POINTER_DOWN:
                  Toast.makeText(MainActivity.this, "多点按下动作", Toast.LENGTH_SHORT).show();

                       break;
                   case MotionEvent.ACTION_POINTER_UP:
                  Toast.makeText(MainActivity.this, "多点抬起动作", Toast.LENGTH_SHORT).show();

                       break;

               }
               return false;
           }
       });

        btn.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                PopupWindow pop = new PopupWindow(view, 350, ViewGroup.LayoutParams.WRAP_CONTENT);
                pop.setAnimationStyle(R.style.popwin_anim_style);
                View contentView = LayoutInflater.from(MainActivity.this).inflate(
                        R.layout.layout, null);
                pop.setContentView(contentView);
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 0.2f; //0.0-1.0
                getWindow().setAttributes(lp);
                pop.setTouchable(true);
                pop.setFocusable(true);
                int[] loca=new int[2];
                view.getLocationInWindow(loca);
                pop.showAtLocation(parent, Gravity.TOP,loca[0], (int) (loca[1]-view.getHeight()*0.5f));
                pop.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    // 在dismiss中恢复透明度
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = getWindow()
                                .getAttributes();
                        lp.alpha = 1f;
                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        getWindow().setAttributes(lp);
                    }
                });
                return false;
            }
        });
    }
}
