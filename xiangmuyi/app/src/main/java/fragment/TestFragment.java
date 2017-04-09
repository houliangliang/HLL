package fragment;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.my.R;

import java.util.ArrayList;

import Bean.phone;
import adapter.frag_apadter;

/**
 * date: 2017/4/9
 * author:侯亮亮候亮亮
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
        //初始化数据
        initdata();

    }

    private void initdata() {
        ArrayList<phone> strings = new ArrayList<phone>();
        strings.add(new phone("搜索",R.mipmap.ic_drawer_search_normal));
        strings.add(new phone("收藏",R.mipmap.ic_drawer_favorite_normal));
        strings.add(new phone("消息",R.mipmap.ic_drawer_message_normal));
        strings.add(new phone("离线",R.mipmap.ic_drawer_offline_normal));
        strings.add(new phone("活动",R.mipmap.left_drawer_activity));
        strings.add(new phone("设置",R.mipmap.ic_drawer_setting_normal));
        strings.add(new phone("反馈",R.mipmap.ic_drawer_feedback_normal));
        strings.add(new phone("精彩应用",R.mipmap.ic_drawer_appstore_normal));
        mLv.setAdapter(new frag_apadter(getActivity(),strings));
    }
}
