package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.my.Main2Activity;
import com.example.my.MainActivity_loginshouye;
import com.example.my.R;

import java.util.ArrayList;

import Bean.phone;
import Utils.UiUtils;
import adapter.frag_apadter;

/**
*作者:侯亮亮
*时间:2017/4/13 18:25
*类描述:侧滑页面
*/

public class TestFragment extends Fragment {

    private ListView mLv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frag1,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLv = (ListView) getView().findViewById(R.id.frag1_lv);
        mLv.setDivider(null);
        LinearLayout linear= (LinearLayout) getView().findViewById(R.id.Linear_yejian);
        Button but= (Button) getView().findViewById(R.id.frag1_log);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity_loginshouye.class);
                startActivity(intent);
            }
        });


        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.switchAppTheme(getActivity());
                reload();

            }

            private void reload() {
                Intent intent = getActivity().getIntent();
                getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);//进入动画
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                startActivity(intent);
            }
        });

        //初始化数据
        initdata();

    }

    private void initdata() {
        ArrayList<phone> strings = new ArrayList<phone>();
        strings.add(new phone("好友动态",R.mipmap.dynamicicon_leftdrawer));
        strings.add(new phone("我的主题",R.mipmap.topicicon_leftdrawer));
        strings.add(new phone("收藏",R.mipmap.ic_action_favor_on_normal));
        strings.add(new phone("活动",R.mipmap.activityicon_leftdrawer));
        strings.add(new phone("商城",R.mipmap.sellicon_leftdrawer));
        strings.add(new phone("反馈",R.mipmap.feedbackicon_leftdrawer));
        strings.add(new phone("我要爆料",R.mipmap.share_jokebar_textpage_normal_night));
        mLv.setAdapter(new frag_apadter(getActivity(),strings));
    }
}
