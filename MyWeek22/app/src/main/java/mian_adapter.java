import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * date: 2017/4/15
 * author:侯亮亮候亮亮
 */

public class mian_adapter extends BaseAdapter {
    List arr;
    Context mContext;

    public mian_adapter(List arr, Context context) {
        this.arr = arr;
        mContext = context;
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
        return convertView;
    }
}
