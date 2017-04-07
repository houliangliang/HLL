package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.my.R;

import java.util.List;

import Bean.dataBean;


/**
 * date: 2017/4/5
 * author:梁坤
 * 类用途：适配器
 */

public class myadapter extends BaseAdapter {
    List<dataBean.ListBean> arr;
    private Context context;
    public static final int ONE=0;
    public static final int TEO=1;
    private ViewHoder1 mVh;

    public myadapter(List<dataBean.ListBean> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }


    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null) {
                mVh = new ViewHoder1();
                convertView = convertView.inflate(context, R.layout.list1, null);
                mVh.adderss = (TextView) convertView.findViewById(R.id.address);
                mVh.addressname = (TextView) convertView.findViewById(R.id.addressname);
                convertView.setTag(mVh);
            }else {
                mVh  = (ViewHoder1) convertView.getTag();
            }
           mVh.adderss.setText("地点名："+arr.get(position).getSite_name());
           mVh.addressname.setText("地点："+arr.get(position).getAddress());
        return convertView;
    }

class ViewHoder1{

    TextView adderss,addressname;

}

}
