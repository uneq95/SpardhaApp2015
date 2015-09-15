package com.ritesh.spardha.gcm;

/**
 * Created by ritesh_kumar on 07-Aug-15.
 */
public abstract class Db_Constants {


    //Auto increment key for all tables
    public static final String KEY_ROWID = "_id";

    //key for main Queue table
    public static final String KEY_FOREIGN_KEY = "FOREIGN_KEY";
    public static final String KEY_TABLE_KEY = "TABLE_KEY";

    //keys for GCM1 table
    public static final String KEY_SPORT = "SPORT";
    public static final String KEY_LOCATION = "LOCATION";
    public static final String KEY_DATE = "DATE";
    public static final String KEY_TIME = "TIME";
    public static final String KEY_TEAM1 = "TEAM1";
    public static final String KEY_TEAM2 = "TEAM2";
    public static final String KEY_TEAM1_IMGLINK = "TEAM1IMGLINK";
    public static final String KEY_TEAM2_IMGLINK = "TEAM2IMGLINK";

    //keys for GCM2 table
    public static final String KEY_IMAGE_LINK = "IMG_LINK";
    public static final String KEY_MESSAGE = "IMAGE_LINKED_MSG";

    //key for GCM3 table
    public static final String KEY_MSG_TITLE = "MSG_TITLE";
    public static final String KEY_MSG_BODY = "MSG_BODY";


    // Database Name
    public static final String DATABASE_NAME = "GCM_DB";

    //Table names
    public static final String MSG_QUEUE_TABLE = "GCM_QUEUE";
    public static final String MSG_TYPE_1_TABLE = "GCM1";
    public static final String MSG_TYPE_2_TABLE = "GCM2";
    public static final String MSG_TYPE_3_TABLE = "GCM3";

    // table creation query statements

    public static final String CREATE_MSG_QUEUE_TABLE =  String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER , %s INTEGER);", MSG_QUEUE_TABLE, KEY_ROWID, KEY_TABLE_KEY, KEY_FOREIGN_KEY);
    public static final String CREATE_MSG_TYPE_1_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT , %s TEXT , %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT );", MSG_TYPE_1_TABLE, KEY_ROWID, KEY_SPORT, KEY_LOCATION, KEY_DATE, KEY_TIME, KEY_TEAM1, KEY_TEAM2,KEY_TEAM1_IMGLINK,KEY_TEAM2_IMGLINK);
    public static final String CREATE_MSG_TYPE_2_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT , %s TEXT );", MSG_TYPE_2_TABLE, KEY_ROWID, KEY_IMAGE_LINK, KEY_MESSAGE);
    public static final String CREATE_MSG_TYPE_3_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT );", MSG_TYPE_3_TABLE, KEY_ROWID, KEY_MSG_TITLE, KEY_MSG_BODY);

}
