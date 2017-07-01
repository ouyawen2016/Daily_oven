package com.oven.util;

/**
 * 向服务器发送网络请求，并返回需要的数据
 * Created by oven 2017/5/5.
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class HttpUtil {
    private static String NEWSLIST_LATEST ="http://news-at.zhihu.com/api/4/news/latest";
    private static String NEWS_DETAILS = "http://news-at.zhihu.com/api/4/news"+"/";
    private static String Themes = "http://news-at.zhihu.com/api/4/themes";
    private static String  sendHttpRequest(final String address) throws IOException {
        HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                        reader.close();
                        return response.toString();
                } else{
                        throw new IOException("Network Error - response code:" + connection.getResponseCode());
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
    public static String getNewslistLatest()throws IOException{
          return sendHttpRequest(NEWSLIST_LATEST);
      }
    public static String getNewThemes() throws IOException {
        return sendHttpRequest(Themes);
    }
    public static String getNewsDetails(String id)throws IOException{
         String mnewsid = NEWS_DETAILS + id;
        return sendHttpRequest(mnewsid);
    }

    public static Bitmap getImageBitmap(String url){
        Bitmap bitmap = null;
        InputStream is = null;
        try {
            URL imgUrl = new URL(url);
             HttpURLConnection conn = (HttpURLConnection)imgUrl.openConnection();
             conn.setDoInput(true);
             conn.connect();
             is = conn.getInputStream();
             bitmap = BitmapFactory.decodeStream(is);
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

}




