package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.librarylistview.XListView;
import com.example.my.R;

import Utils.xutlis;

/**
*作者:侯亮亮
*时间:2017/4/12 19:07
*类描述:主页面viewpage适配的fragment
*/

public class act_fragment extends Fragment {

    public  String str;
    private XListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.act_fragment,container,false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView = (XListView) getView().findViewById(R.id.xlistview);
        xutlis.getjson(getActivity(),str,mListView);
        mListView.setPullLoadEnable(true);

        mListView.setPullRefreshEnable(true);



        mListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                onLoad();
            }

            @Override
            public void onLoadMore() {
                onLoad();
            }
        });
    }

    private void onLoad() {

        mListView.stopRefresh();

        mListView.stopLoadMore();

        mListView.setRefreshTime("刚刚");

    }
}
