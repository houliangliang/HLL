package Utils;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import Bean.dataBean;
import adapter.myadapter;


import static android.R.attr.data;

/**
 * date: 2017/4/5
 * author:梁坤
 * 类用途：网络请求类
 */

public class MyAsync extends AsyncTask<String , Integer,String> {
    ListView lv;
    Context context;
    private myadapter mMyadapter;

    public MyAsync(ListView lv, Context context) {
        this.lv = lv;
        this.context = context;
    }

    public MyAsync(ListView lv) {
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //gson解析
        Gson gson = new Gson();
        //解析数据封装类
        dataBean myBean = gson.fromJson(s, dataBean.class);
        final List<dataBean.ListBean> list = myBean.getList();
        mMyadapter = new myadapter(list,context);
        //适配数据
        lv.setAdapter(mMyadapter);
        //监听条目跳转页面
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,list.get(position).getId()+"",Toast.LENGTH_SHORT).show();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

               list.remove(position);
                mMyadapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... params) {
        String httpdata = httpUtils.httpdata(params[0]);


        return httpdata;
    }

}
