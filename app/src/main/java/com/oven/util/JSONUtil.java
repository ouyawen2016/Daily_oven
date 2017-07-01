package com.oven.util;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oven.bean.Latest;
import com.oven.bean.NewsDetails;
import com.oven.bean.Stories;
import com.oven.bean.Theme;
import com.oven.bean.Themes;
import com.oven.db.DBSave;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 解析服务器返回的json数据并处理
 * Created by oven on 2017/5/6.
 */

public class JSONUtil {
    //将返回的新闻详情用jsonArray与jsonObject解析,并返回news
    public static NewsDetails handleNewsResponse(String response){
       /* if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allNews = new JSONArray(response);
                JSONObject newsObject = allNews.getJSONObject(0);
                    NewsDetails news = new NewsDetails();
                    news.setBody(newsObject.getString("body"));
                    news.setImage(newsObject.getString("image"));
                    news.setType(newsObject.getInt("type"));
                    news.setImagesource(newsObject.getString("image_source"));
                    news.setId(newsObject.getString("id"));
                    news.setTitle(newsObject.getString("title"));
                return news;
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return null;*/
        try {
            Gson gson = new Gson();
          NewsDetails newsDetails= gson.fromJson(response,NewsDetails.class);
            return  newsDetails;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将返回的最新新闻列表用GSON解析并返回latest
     */
    public static Latest handleLastResponse(String response){

            try {
                Gson gson = new Gson();
                Latest latest = gson.fromJson(response,Latest.class);
                return  latest;
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }

    /**
     * 将返回的新闻主题用jsonArray与jsonObject解析并存入数据库
     */
    public static boolean handleThemesResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allThemes = new JSONArray(response);
                for(int i =0 ;i < allThemes.length();i++){
                    JSONObject newsObject = allThemes.getJSONObject(i);
                    Themes themes = new Themes();
                    themes.setDescription(newsObject.getString("description"));
                    themes.setThemeId(newsObject.getString("id"));
                    themes.setThumbnail(newsObject.getString("thumbnail"));
                    themes.setThemeName(newsObject.getString("name"));
                    DBSave.save(themes.getDescription(),themes.getThemeName(),themes.getThemeId(),themes.getThumbnail());
                }
                return true;
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }


/**
 * 将返回的新闻主题用jsonArray与jsonObject解析并存入数据库
 */
    public static boolean handleThemeResponse(String response,String themeid){
    if(!TextUtils.isEmpty(response)){
        try{
            JSONArray allTheme = new JSONArray(response);
            for(int i =0 ;i < allTheme.length();i++){
                JSONObject newsObject = allTheme.getJSONObject(i);
                Theme theme = new Theme();
                theme.setDescription(newsObject.getString("description"));
                theme.setThemeId(themeid);
                theme.setThemeName(newsObject.getString("name"));
                theme.setBackground(newsObject.getString("background"));
                theme.setImage_source(newsObject.getString("image_source"));
                DBSave.save(theme.getDescription(),theme.getThemeName(),theme.getThemeId(),theme.getImage_source(),theme.getBackground());
            }
            return true;
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
    return false;
}
}

