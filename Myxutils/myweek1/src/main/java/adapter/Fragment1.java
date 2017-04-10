package adapter;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.myweek1.MainActivity;

import Utils.Url;
import fragment.TextFragment;

/**
 * date: 2017/4/10
 * author:侯亮亮候亮亮
 */

public class Fragment1 extends FragmentPagerAdapter {
    String len[];
    String []url={"gj","ss","cj","kj","js","ty","yl","gn","shehui","tt"};

    public Fragment1(FragmentManager fm, String[] len) {
        super(fm);
        this.len = len;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("TAG",url[position]);
        Log.d("TAG",position+"");
        TextFragment textFragment = new TextFragment();
        textFragment.str=url[position];
        return textFragment;
    }

    @Override
    public int getCount() {
        return len.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return len[position%len.length].toUpperCase();
    }
}
