package MyAppc;

import android.app.Application;

import org.xutils.x;

/**
 * date: 2017/4/10
 * author:侯亮亮候亮亮
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能
    }
}
