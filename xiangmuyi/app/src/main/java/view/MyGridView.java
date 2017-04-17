package view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
*作者:侯亮亮
*时间:2017/4/13 20:52
*类描述:title添加管理模块的gridview重写类
*/
public class MyGridView extends GridView {
    public MyGridView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
