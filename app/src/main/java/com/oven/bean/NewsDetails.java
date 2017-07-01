package com.oven.bean;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

/**
 * 新闻详情
 * Created by oven on 2017/5/6.
 */

public class NewsDetails {
    private String id;
    private String body;
    private String image;
    private Bitmap bitmap;
    private String title;
    @SerializedName("image_source")
    private String imagesource;
    private int type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public String getImage() {
        return image;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }
    public String getImagesource() {
        return imagesource;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

}



