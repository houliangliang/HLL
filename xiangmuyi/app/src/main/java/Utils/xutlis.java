package Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;

import com.example.librarylistview.XListView;
import com.example.my.R;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import Bean.MyBean;
import adapter.data_adapter;

/**
*作者:侯亮亮
*时间:2017/4/13 20:55
*类描述:xutil工具类
*/

public class xutlis {
    public static void getjson(final Context context, String values, final XListView xListView){
        RequestParams params = new RequestParams(URL.act_url);
        params.addQueryStringParameter("uri",values);
        x.http().get(params, new Callback.CommonCallback<String>() {

            //  判断网络是否连接
            public boolean isNetWorkAvailable(Context context){
                //网络连接管理器
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                //获得网络信息
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();
                if(info!=null){
                    return true;
                }
                return false;
            }

            //是否是wifi
            public boolean isWifi(Context context){
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();
                if(info!=null&&info.getType()==ConnectivityManager.TYPE_WIFI){
                    return true;
                }
                return false;
            }

            //是否是流量
            public boolean isGPS(Context context){
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();
                if(info!=null&&info.getType()==ConnectivityManager.TYPE_MOBILE){
                    return true;
                }
                return false;
            }

            @Override
            public void onSuccess(String result) {
                boolean netWorkAvailable = isNetWorkAvailable(context);
                //解析resultboolean netWorkAvailable = isNetWorkAvailable(this);
                boolean wifi = isWifi(context);


                        Gson gson = new Gson();
                        MyBean   mMyBean = gson.fromJson(result, MyBean.class);
                        List<MyBean.ResultBean.DataBean> data = mMyBean.getResult().getData();
                        xListView.setAdapter(new data_adapter(data,context));

                        Toast.makeText(context,"wifi连接",Toast.LENGTH_SHORT).show();


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
