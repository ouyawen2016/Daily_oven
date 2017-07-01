package com.oven.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;



/**某类新闻列表子项
 * Created by 管理员账号 on 2017/5/6.
 */

public class Theme {
     private int id;
    public String themeId;
    public String themeName;
    public String description;
    public String background;
     public String image_source;

    //编辑暂时不做
   public List<Editors> editorses;


    public String getThemeId() {
        return themeId;
    }
    public String getThemeName() {
        return themeName;
    }
    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }
    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
