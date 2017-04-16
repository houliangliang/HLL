package fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myweek2.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import Bean.Slidingmenu_Bean;
import Utils.FargUtils;
import Utils.Url;
import Utils.httpUtils;

/**
 * date: 2017/4/15
 * author:侯亮亮候亮亮
 */

public class TestFragment extends Fragment {

    private ListView mTextfrag_lv;
    private FragmentManager mSupportFragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.textfragment_frag,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTextfrag_lv = (ListView) getView().findViewById(R.id.textfrag_lv);

        initdata();
        mSupportFragmentManager = getActivity().getSupportFragmentManager();
       mTextfrag_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
             if (position==0){
                 Toast.makeText(getActivity(),"666666666",Toast.LENGTH_SHORT).show();

                 Fragment allFragment = new AllFragment();
                 View inflate = View.inflate(getActivity(), R.layout.activity_main, null);
                 FrameLayout fl= (FrameLayout) inflate.findViewById(R.id.mian_frame);
                 FargUtils.Replace_Frag(mSupportFragmentManager,allFragment,R.id.mian_frame);

             }else if (position==1){
                 Fragment xi = new xiFragment();
                 View inflate = View.inflate(getActivity(), R.layout.activity_main, null);
                 FrameLayout fl= (FrameLayout) inflate.findViewById(R.id.mian_frame);
                 FargUtils.Replace_Frag(mSupportFragmentManager,xi,R.id.mian_frame);
             }else if (position==2){
                 Fragment zhong= new zhongFragment();
                 View inflate = View.inflate(getActivity(), R.layout.activity_main, null);
                 FrameLayout fl= (FrameLayout) inflate.findViewById(R.id.mian_frame);
                 FargUtils.Replace_Frag(mSupportFragmentManager,zhong,R.id.mian_frame);
             }

               return true;
           }
       });

    }

    private void initdata() {
        Handler hh=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
               String data= (String) msg.obj;
                Gson gson = new Gson();
                Slidingmenu_Bean slidingmenu_bean = gson.fromJson(data, Slidingmenu_Bean.class);
                List<Slidingmenu_Bean.DataBean> data1 = slidingmenu_bean.getData();
                ArrayList<String> strings = new ArrayList<>();
                for (int i=0;i<data1.size();i++){
                    strings.add(data1.get(i).getName());
                }
                mTextfrag_lv.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,strings));
            }
        };
        new httpUtils(hh, Url.sliding_url).start();

    }

}
