package com.oven.db;



/**
 * 数据库基本设置及建表
 * Created by 管理员账号 on 2017/5/6.
 */

public class DBConstant {
    public static final String CREATE_THEMES ="create table themes("
            +"id integer autoincrement,"
            +"thumbnail varchar,"
            +"description text,"
            +"themeName text,"
            +"themeId varchar)";
    public static final String CREATE_THEME ="create table theme("
            +"id integer autoincrement,"
            +"themeId varchar,"
            +"description text,"
            +"themeName text,"
            +"background varchar,"
            +"image_source varchar)";
    public static final String CREATE_STORY ="create table story("
            +"id integer autoincrement,"
            +"styId varchar,"
            +"title text,"
            +"images text)";

    public  static final int DBVERSION = 1;





}
