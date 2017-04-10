package Utils;

import android.content.Context;

import com.example.librarylistview.XListView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import Bean.MyBean;
import adapter.data_adapter;

/**
 * date: 2017/4/10
 * author:侯亮亮候亮亮
 */

public class xutlis {
    public static void getjson(final Context context, String values, final XListView xListView){
        RequestParams params = new RequestParams(Url.url);
        params.addQueryStringParameter("uri",values);
        x.http().get(params, new Callback.CommonCallback<String>() {



            @Override
            public void onSuccess(String result) {
                //解析result
                Gson gson = new Gson();
                MyBean   mMyBean = gson.fromJson(result, MyBean.class);
                List<MyBean.ResultBean.DataBean> data = mMyBean.getResult().getData();
                xListView.setAdapter(new data_adapter(data,context));
            }
            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });

    }

}
