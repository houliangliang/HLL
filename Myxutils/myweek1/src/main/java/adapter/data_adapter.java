package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myweek1.R;

import org.xutils.x;

import java.util.List;

import Bean.MyBean;

/**
 * date: 2017/4/10
 * author:侯亮亮候亮亮
 */

public class data_adapter extends BaseAdapter {
    List<MyBean.ResultBean.DataBean> arr;
    Context context;

    public data_adapter(List<MyBean.ResultBean.DataBean> arr, Context context) {
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHoder vh;
        if (convertView==null){
            convertView=convertView.inflate(context, R.layout.list,null);
            vh=new viewHoder();
            vh.im= (ImageView) convertView.findViewById(R.id.imageView);
            vh.tv= (TextView) convertView.findViewById(R.id.textView);
            vh.author_name= (TextView) convertView.findViewById(R.id.author);
            convertView.setTag(vh);
        }else {
       vh= (viewHoder) convertView.getTag();
        }
        x.image().bind(vh.im,arr.get(position).getThumbnail_pic_s());
        vh.tv.setText(arr.get(position).getTitle());
        vh.author_name.setText(arr.get(position).getAuthor_name());
        return convertView;
    }
    class viewHoder{
        ImageView im;
        TextView tv,author_name;
    }
}
