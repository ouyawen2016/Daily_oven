package com.oven.bean;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

/**头条类型
 * Created by oven on 2017/5/6.
 */

public class Top_stories {
    @SerializedName("title")
    public String topTitle;
    public String image;
    @SerializedName("id")
    public String topId;
    public Bitmap tsbitmap;

    public String getTopTitle() {
        return topTitle;
    }

    public void setTopTitle(String topTitle) {
        this.topTitle = topTitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTopId() {
        return topId;
    }

    public void setTopId(String topId) {
        this.topId = topId;
    }

    public Bitmap getTsbitmap() {
        return tsbitmap;
    }

    public void setTsbitmap(Bitmap tsbitmap) {
        this.tsbitmap = tsbitmap;
    }
}
