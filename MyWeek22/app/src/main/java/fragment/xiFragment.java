package fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myweek2.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import Bean.Slidingmenu_Bean;
import Bean.adap_Bean;
import Bean.all_Bean;
import Utils.Url;
import Utils.httpUtils;

/**
 * date: 2017/4/15
 * author:侯亮亮候亮亮
 */

public class xiFragment extends Fragment {

    private ListView mTextfrag_lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.xi,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTextfrag_lv = (ListView) getView().findViewById(R.id.xi_lv2);

        initdata();


    }

    private void initdata() {
        Handler hh=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String data= (String) msg.obj;
                Gson gson = new Gson();
                all_Bean all_bean = gson.fromJson(data, all_Bean.class);
                List<all_Bean.DataBean> data1 = all_bean.getData();
                ArrayList<adap_Bean> strings = new ArrayList<>();
                for (int i=0;i<data1.size();i++){
                    adap_Bean aa=new adap_Bean();
                    aa.setTitle(data1.get(i).getTitle());
                    aa.setBaike(data1.get(i).getBaike());
                    aa.setPrice(data1.get(i).getBuy_price());
                    strings.add(aa);
                }
                mTextfrag_lv.setAdapter(new ArrayAdapter<adap_Bean>(getActivity(),android.R.layout.simple_list_item_1,strings));
            }
        };
        new httpUtils(hh, Url.path2).start();

    }

}
