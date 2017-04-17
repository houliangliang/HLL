package com.example.my;

import android.app.Application;
import android.util.Log;

import org.xutils.DbManager;
import org.xutils.db.table.TableEntity;
import org.xutils.x;

import java.io.File;

/**
*作者:侯亮亮
*时间:2017/4/13 20:52
*类描述:  Application方法重写
*/

public class MyApp extends Application {

    public static DbManager mDb;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                //设置数据库名，默认xutils.db
                .setDbName("myap.db")
                //设置数据库路径，默认存储在app的私有目录
//                .setDbDir(new File("/mnt/sdcard/"))
                //设置数据库的版本号
                .setDbVersion(1);
        //设置数据库打开的监听
        //这个方法不能用如果用了程序运行第一遍 的时候不会存进数据库
//                .setDbOpenListener(new DbManager.DbOpenListener() {
//                    @Override
//                    public void onDbOpened(DbManager db) {
//                               //开启数据库支持多线程操作，提升性能，对写入加速提升巨大
//                        db.getDatabase().enableWriteAheadLogging();
//                    }
//                })
//                //设置数据库更新的监听
//                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
//                    @Override
//                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
//                    }
//                })
//                //设置表创建的监听
//                .setTableCreateListener(new DbManager.TableCreateListener() {
//                    @Override
//                    public void onTableCreated(DbManager db, TableEntity<?> table){
//                        Log.i("JAVA", "onTableCreated：" + table.getName());
//
//                    }
//                }
//    );
        //设置是否允许事务，默认true
        //.setAllowTransaction(true)

        mDb = x.getDb(daoConfig);
//        titleBean.ResultBean.DateBean dateBean = new titleBean.ResultBean.DateBean();
//        dateBean.setId(1);
//        dateBean.setUri("sd");
//        dateBean.setTitle("你好");
//        try {
//            mDb.save(dateBean);
//        } catch (DbException e) {
//            e.printStackTrace();
//        }

    }
}
