package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my.R;

import java.util.List;

import Bean.phone;

/**
 * date: 2017/4/9
 * 类用途：侧滑页面适配
 * author:侯亮亮候亮亮
 */

public class frag_apadter extends BaseAdapter {

    private Context context;
    private List<phone>arr;

    public frag_apadter(Context context, List<phone> arr) {
        this.context = context;
        this.arr = arr;
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder vh;
        if (convertView==null){
            convertView=convertView.inflate(context,R.layout.frag1_list,null);
            vh=new ViewHoder();
            vh.im= (ImageView) convertView.findViewById(R.id.frag1_im);
            vh.tv= (TextView) convertView.findViewById(R.id.frag_tv);
            convertView.setTag(vh);
        }else {
            vh= (ViewHoder) convertView.getTag();
        }
        vh.im.setBackgroundResource(arr.get(position).getIm());
        vh.tv.setText(arr.get(position).getName());
        return convertView;
    }

    class ViewHoder{
        ImageView im;
        TextView tv;
    }
}
