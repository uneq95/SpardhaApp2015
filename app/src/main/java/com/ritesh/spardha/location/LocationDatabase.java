package com.ritesh.spardha.location;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ritesh_kumar on 22-May-15.
 */
public class LocationDatabase {

    static final String KEY_ROWID="_id";
    static final String KEY_SUBJECT="Category";
    static final String KEY_DATE="LocationName";
    static final String KEY_REASON="Longitude";

    static final String DATABASE_NAME = "MyDb";
    static final String DATABASE_TABLE = "attendance";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_CREATE = "create table "+DATABASE_TABLE+" ("+KEY_ROWID+" integer primary key autoincrement, "+KEY_SUBJECT+" text, "+KEY_DATE+" text not null, "+KEY_REASON+" integer not null);";
    final Context context;
    SQLiteDatabase db;
    DatabaseHelper dbhlp;

    public LocationDatabase(Context c){
        this.context=c;
        dbhlp= new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) throws SQLException {
            // TODO Auto-generated method stub
            db.execSQL(DATABASE_CREATE);
            //Log.d("OnCreate","Database on create method");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) throws SQLException {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS attendance");
            onCreate(db);

        }
    }
    public LocationDatabase open() throws SQLException{
        db=dbhlp.getWritableDatabase();
        //Log.d("opening", "database opened");
        return this;
    }
    public void close(){
        dbhlp.close();
    }
    public long lodgeDetails(String subject,String date,String reason){
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_SUBJECT, subject);
        contentValues.put(KEY_DATE, date);
        contentValues.put(KEY_REASON, reason);
        return db.insert(DATABASE_TABLE, null, contentValues);
    }

    public void clearDatabase(){
        db.delete(DATABASE_TABLE, null, null);
    }
}
