package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import Bean.titleBean;
import fragment.TestFragment;
import fragment.act_fragment;

/**
*作者:侯亮亮
*时间:2017/4/13 20:43
*类描述:title的适配器
*/

public class titleadapter extends FragmentPagerAdapter {
    private List<Bean.titleBean.ResultBean.DateBean> arr;
    String []url={"gj","ss","cj","kj","js","ty","yl","gn","shehui","tt"};
    public titleadapter(FragmentManager fm, List<titleBean.ResultBean.DateBean> arr) {
        super(fm);
        this.arr = arr;
    }

    public titleadapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        act_fragment act_fragment = new act_fragment();
        act_fragment.str=arr.get(position).getUri();
        return act_fragment ;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return arr.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return arr.size();
    }
}
