package Utils;

import android.os.Handler;
import android.os.Message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * date: 2017/4/15
 * author:侯亮亮候亮亮
 */

public class httpUtils extends  Thread{

    private Handler hh;
    private String url;

    public httpUtils(Handler hh, String url) {
        this.hh = hh;
        this.url = url;
    }

    @Override
    public void run() {
        super.run();

        Message message = new Message();
        String httpdata = httpdata(url);
        message.obj=httpdata;
        hh.sendMessage(message);


    }

    public static  String httpdata(String url){
             try {

                 URL uu=new URL(url);

                 HttpURLConnection httpurl= (HttpURLConnection) uu.openConnection();

                 httpurl.setRequestMethod("GET");

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
