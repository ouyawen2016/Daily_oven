package com.oven.bean;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;
import com.oven.util.HttpUtil;

import java.io.IOException;

/**
 * story类型
 * Created by 管理员账号 on 2017/5/6.
 */

public class Stories {

    private String title;
    public String[] images;
    @SerializedName("id")
    private String styId;
    private boolean multipic;
    private Bitmap imagebitmap;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String getStyId() {
        return styId;
    }

    public void setStyId(String styId) {
        this.styId = styId;
    }

    public Bitmap getImagebitmap() {
        return imagebitmap;
    }

    public void setImagebitmap(Bitmap imagebitmap) {
        this.imagebitmap = imagebitmap;
    }
}
