package com.oven.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *简单的数据库存储
 * Created by oven on 2017/5/6.
 */


public class DBHelper extends SQLiteOpenHelper {
    private Context mContext;
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
                mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DBConstant.CREATE_THEMES);
        db.execSQL(DBConstant.CREATE_THEME);
        db.execSQL(DBConstant.CREATE_STORY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
