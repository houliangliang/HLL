package Utils;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;


/**
 * date: 2017/4/15
 * author:侯亮亮候亮亮
 */

public class FargUtils {

    public static void Replace_Frag(FragmentManager fm, Fragment ff, int id){
        android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(id, ff);
        fragmentTransaction.commit();
    }
}
