package com.oven.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.oven.util.ContextProvider;

/**
 * 将数据存入数据库
 * Created by oven on 2017/5/7.
 */

public class DBSave {

    public static void save(String description, String themeName, String themeId, String thumbnail) {
        DBHelper dbHelper = new DBHelper(ContextProvider.getContext(), "themes.db", null, DBConstant.DBVERSION);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("thumbnail", thumbnail);
        values.put("description", description);
        values.put("themeId", themeId);
        values.put("themeName", themeName);
        db.insert("Themes", null, values);
        values.clear();
    }

    public static void save(String description, String themeName, String themeId, String Image_source, String background)

    {
        DBHelper dbHelper = new DBHelper(ContextProvider.getContext(), "themes.db", null, DBConstant.DBVERSION);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("background", background);
        values.put("description", description);
        values.put("themeId", themeId);
        values.put("image_source", Image_source);
        values.put("themeName", themeName);
        db.insert("Theme", null, values);
        values.clear();
    }
}