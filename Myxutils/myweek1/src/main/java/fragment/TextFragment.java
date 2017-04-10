package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.librarylistview.XListView;
import com.example.myweek1.R;

import Utils.xutlis;

/**
 * date: 2017/4/10
 * author:侯亮亮候亮亮
 */


public class TextFragment extends Fragment {
    public  String str;
    private XListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment,container,false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView = (XListView) getView().findViewById(R.id.xlistview);
        xutlis.getjson(getActivity(),str,mListView);
    }



}

