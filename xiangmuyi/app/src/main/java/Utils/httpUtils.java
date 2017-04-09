package Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

/**
 * date: 2017/4/8
 * author:侯亮亮候亮亮
 */

public class httpUtils {

    public static  String httpdata(String url){
        try {

            java.net.URL uu=new java.net.URL(url);

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
