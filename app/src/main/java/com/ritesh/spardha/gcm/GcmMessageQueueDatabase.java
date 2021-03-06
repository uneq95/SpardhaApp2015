package com.ritesh.spardha.gcm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by ritesh_kumar on 07-Aug-15.
 */
public class GcmMessageQueueDatabase {

    //database version
    private static int DATABASE_VERSION = 1;
    final Context context;
    SQLiteDatabase db;
    DatabaseHelper dbhlp;

    public GcmMessageQueueDatabase(Context c) {
        this.context = c;
        dbhlp = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, Db_Constants.DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) throws SQLException {
            // TODO Auto-generated method stub
            db.execSQL(Db_Constants.CREATE_MSG_QUEUE_TABLE);
            db.execSQL(Db_Constants.CREATE_MSG_TYPE_1_TABLE);
            db.execSQL(Db_Constants.CREATE_MSG_TYPE_2_TABLE);
            db.execSQL(Db_Constants.CREATE_MSG_TYPE_3_TABLE);

            //Log.d("OnCreate","Database on create method");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) throws SQLException {
            // TODO Auto-generated method stub
            // db.execSQL("DROP TABLE IF EXISTS attendance");
            onCreate(db);

        }
    }

    public GcmMessageQueueDatabase open() throws SQLException {
        db = dbhlp.getWritableDatabase();
        //Log.d("opening", "database opened");
        return this;
    }

    public void close() {
        dbhlp.close();
    }

    private void insertInMainGcmQueue(long foreignKeyId, int tableKey) {
        ContentValues cv = new ContentValues();
        cv.put(Db_Constants.KEY_FOREIGN_KEY, foreignKeyId);
        cv.put(Db_Constants.KEY_TABLE_KEY, tableKey);
        db.insert(Db_Constants.MSG_QUEUE_TABLE, null, cv);
        // the latest message is added to the end of the table

    }

    public void insertMsgType1(GcmMessage gcmMsgType1) {
        ContentValues cv = new ContentValues();
        cv.put(Db_Constants.KEY_SPORT, gcmMsgType1.getSport());
        cv.put(Db_Constants.KEY_LOCATION, gcmMsgType1.getLocation());
        cv.put(Db_Constants.KEY_DATE, gcmMsgType1.getDate());
        cv.put(Db_Constants.KEY_TIME, gcmMsgType1.getTime());
        cv.put(Db_Constants.KEY_TEAM1, gcmMsgType1.getTeam1());
        cv.put(Db_Constants.KEY_TEAM2, gcmMsgType1.getTeam2());
        cv.put(Db_Constants.KEY_TEAM1_IMGLINK, gcmMsgType1.getTeam1ImgLink());
        cv.put(Db_Constants.KEY_TEAM2_IMGLINK, gcmMsgType1.getTeam2ImgLink());
        long foreignKey = db.insert(Db_Constants.MSG_TYPE_1_TABLE, null, cv);
        insertInMainGcmQueue(foreignKey, 1);
    }

    public void insertMsgType2(GcmMessage gcmMsgType2) {
        ContentValues cv = new ContentValues();
        cv.put(Db_Constants.KEY_IMAGE_LINK, gcmMsgType2.getPhotoLink());
        cv.put(Db_Constants.KEY_MESSAGE, gcmMsgType2.getImageLinkedMsg());
        long foreignKey = db.insert(Db_Constants.MSG_TYPE_2_TABLE, null, cv);
        insertInMainGcmQueue(foreignKey, 2);
    }

    public void insertMsgType3(GcmMessage gcmMsgType3) {

        ContentValues cv = new ContentValues();
        cv.put(Db_Constants.KEY_MSG_TITLE, gcmMsgType3.getMsgTitle());
        cv.put(Db_Constants.KEY_MSG_BODY, gcmMsgType3.getMsgBody());
        long foreignKey = db.insert(Db_Constants.MSG_TYPE_3_TABLE, null, cv);
        insertInMainGcmQueue(foreignKey, 3);
    }

    public ArrayList<GcmMessage> fetchAllGcmMessages() {
        ArrayList<GcmMessage> messages = new ArrayList<>();
        Cursor c = db.query(Db_Constants.MSG_QUEUE_TABLE, new String[]{Db_Constants.KEY_ROWID,Db_Constants.KEY_TABLE_KEY ,Db_Constants.KEY_FOREIGN_KEY}, null, null, null, null, null);
        System.out.println(c.getCount());
        if (c.getCount() > 0) {

            c.moveToLast();
            /*while (c.moveToPrevious()) {
                int tableKey = c.getInt(c.getColumnIndex(Db_Constants.KEY_TABLE_KEY));
                int foreignKey = c.getInt(c.getColumnIndex(Db_Constants.KEY_FOREIGN_KEY));
                GcmMessage gcmMessage = null;
                switch (tableKey) {
                    case 1:
                        gcmMessage = fetchMessageType1(foreignKey);
                        break;
                    case 2:
                        gcmMessage = fetchMessageType2(foreignKey);
                        break;
                    case 3:
                        gcmMessage = fetchMessageType3(foreignKey);
                        break;
                    default:
                        break;

                }
                messages.add(gcmMessage);
            }*/
            do{
                int tableKey=c.getInt(c.getColumnIndex(Db_Constants.KEY_TABLE_KEY));
                int foreignKey=c.getInt(c.getColumnIndex(Db_Constants.KEY_FOREIGN_KEY));
                GcmMessage gcmMessage=null;
                switch(tableKey){
                    case 1: gcmMessage=fetchMessageType1(foreignKey);break;
                    case 2: gcmMessage=fetchMessageType2(foreignKey);break;
                    case 3: gcmMessage=fetchMessageType3(foreignKey);break;
                    default: break;

                }
                messages.add(gcmMessage);

            }while(c.moveToPrevious());
        }
        return messages;
    }

    public GcmMessage fetchMessageType1(int foreignKey) {
        GcmMessage msg = null;
        String sport, location, date, time, team1, team2,team1ImgLink,team2ImgLink;
        Cursor c;
        String[] columns = new String[]{Db_Constants.KEY_SPORT, Db_Constants.KEY_LOCATION, Db_Constants.KEY_DATE, Db_Constants.KEY_TIME, Db_Constants.KEY_TEAM1, Db_Constants.KEY_TEAM2,Db_Constants.KEY_TEAM1_IMGLINK,Db_Constants.KEY_TEAM2_IMGLINK};
        String condition = String.format(" %s = %d", Db_Constants.KEY_ROWID, foreignKey);
        c = db.query(Db_Constants.MSG_TYPE_1_TABLE, columns, condition, null, null, null, null);
        if (c.getCount()>0) {
            c.moveToFirst();
            sport = c.getString(c.getColumnIndex(Db_Constants.KEY_SPORT));
            location = c.getString(c.getColumnIndex(Db_Constants.KEY_LOCATION));
            date = c.getString(c.getColumnIndex(Db_Constants.KEY_DATE));
            time = c.getString(c.getColumnIndex(Db_Constants.KEY_TIME));
            team1 = c.getString(c.getColumnIndex(Db_Constants.KEY_TEAM1));
            team2 = c.getString(c.getColumnIndex(Db_Constants.KEY_TEAM2));
            team1ImgLink =c.getString(c.getColumnIndex(Db_Constants.KEY_TEAM1_IMGLINK));
            team2ImgLink =c.getString(c.getColumnIndex(Db_Constants.KEY_TEAM2_IMGLINK));
            msg = new GcmMessage(1, sport, location, date, time, team1, team2,team1ImgLink,team2ImgLink);
        }
        return msg;

    }

    private GcmMessage fetchMessageType2(int foreignKey) {
        GcmMessage msg = null;
        String imageLink, imageMessage;
        Cursor c;
        String[] columns = new String[]{Db_Constants.KEY_IMAGE_LINK, Db_Constants.KEY_MESSAGE};
        String condition = String.format(" %s = %d", Db_Constants.KEY_ROWID, foreignKey);
        c = db.query(Db_Constants.MSG_TYPE_2_TABLE, columns, condition, null, null, null, null);
        if (c.getCount()>0) {
            c.moveToFirst();
            imageLink = c.getString(c.getColumnIndex(Db_Constants.KEY_IMAGE_LINK));
            imageMessage = c.getString(c.getColumnIndex(Db_Constants.KEY_MESSAGE));

            msg = new GcmMessage(2, imageLink, imageMessage);
        }
        return msg;

    }

    private GcmMessage fetchMessageType3(int foreignKey) {
        GcmMessage msg = null;
        String msgTitle, msgBody;
        String[] columns = new String[]{Db_Constants.KEY_ROWID,Db_Constants.KEY_MSG_TITLE, Db_Constants.KEY_MSG_BODY};
        String condition = String.format("%s = %d", Db_Constants.KEY_ROWID, foreignKey);
        Cursor c= db.query(Db_Constants.MSG_TYPE_3_TABLE, columns, condition, null, null, null, null);
        if (c.getCount()>0) {
            c.moveToFirst();
            msgTitle = c.getString(c.getColumnIndex(Db_Constants.KEY_MSG_TITLE));
            msgBody = c.getString(c.getColumnIndex(Db_Constants.KEY_MSG_BODY));
            msg = new GcmMessage(3, msgTitle, msgBody, true);
        }
        return msg;

    }

    /* public void clearDatabase(){
         db.delete(DATABASE_TABLE, null, null);
     }*/

}
