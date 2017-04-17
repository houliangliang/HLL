package Utils;

import android.os.Handler;
import android.os.Message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.net.URL;

/**
*作者:侯亮亮
*时间:2017/4/12 18:41
*类描述:标题的网络获取工具类
*/

public class titleHttp extends  Thread {
Handler hh;
    String url;

    public titleHttp(Handler hh, String url) {
        this.hh = hh;
        this.url = url;
    }

    @Override
    public void run() {
        super.run();
        String httpdata = httpdata(url);
        Message message = new Message();
        message.obj=httpdata;
        hh.sendMessage(message);
    }

    public  String httpdata(String url){
            try {

                java.net.URL uu=new URL(url);

                HttpURLConnection httpurl= (HttpURLConnection) uu.openConnection();

                httpurl.setRequestMethod("POST");

                httpurl.setConnectTimeout(5000);

                httpurl.setReadTimeout(5000);

                if (httpurl.getResponseCode()==200)  {

                    InputStream inputStream = httpurl.getInputStream();

                    ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                    byte by[]=new byte[1024];
                    int len=0;

                    while ((len=inputStream.read(by))!=-1){
                        byteArrayOutputStream.write(by,0,len);
                    }
                    return byteArrayOutputStream.toString("utf-8");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  null;
        }
}
