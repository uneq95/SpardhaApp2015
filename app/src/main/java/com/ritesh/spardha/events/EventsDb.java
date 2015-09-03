package com.ritesh.spardha.events;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by Abhishek on 8/29/2015.
 */
public class EventsDb {

    public static final String KEY_ROWID = "id";
    public static final String KEY_SPORTSNAME = "sportsname";
    public static final String KEY_WINNER = "winner";
    public static final String KEY_RUNNERUP = "runnerup";
    public static final String KEY_RULEBOOK = "rulebook";
    public static final String KEY_CONTACTA = "contacta";


    public static final String DATABASE_NAME  = "sports_detail";
    public static final String DATABASE_TABLE = "eventdetail";
    private static final int DATABASE_VERSION = 1;

    private  final Context context;
    private SQLiteDatabase sportsdatabase;
    public DBHelper dbhelperobject;

    String[] categorysportsname = {"Athletics","Badminton","BasketBall","Boxing","Carrom","Chess","Cricket","FootBall","HandBall","Hockey","Kabaddi","KhoKho","Squash","TaeKwonDo","Tennis","TableTennis","VolleyBall","WeightLifting"};
    String[] categorywinner = {"Click to Download","IIT Kharagpur","BRCM CET(Boys),\nMITS(Girls)","---","UGI(Boys),\nKIT(Girls)","IIT BHU","IIT BHU","IIT BHU","IIT BHU","MERI Kolkatta","IIT BHU","IIT BHU","GEC Ajmer","Click To Download","Shiv Nadar","IIT Roorkee","Shri Ram College","---"};
    String[] categoryrunnerup = {"Click to Download","IIT BHU","IIT BHU(Boys),\nIIT BHU(Girls)","---","BBD Lucknow(Boys),\nUGI(Girls)","IET Lucknow","BBD Lucknow","---","NIT Raipur","IIT BHU","YMCA Faridabad","IIT Mumbai","IIT BHU","Click to download","IIT BHU","SNU Delhi","IIT BHU","--"};
    String[] categoryrulebook = {"Athletics","Badminton","BasketBall","Boxing","Carrom","Chess","Cricket","FootBall","HandBall","Hockey","Kabaddi","KhoKho","Squash","TaeKwonDo","Tennis","TableTennis","VolleyBall","WeightLifting"};
    String[] categorycontacta = {"Gaurav  Sharma - 7860561883","Abhishek Dhananjaya - 9532892608","Mohammad Shahbaz - 8601546468","---","---","Gopal Garg - 9897232528 ","Venkat Subhash -  8953723461","Mayank kumar Morya - 9919546976","---","Suyash Tripathi - 8953880553","---","Nilabh Nandan - 7752957824","Siddhartha bhargava - 7275588982","Prabhat Srivastava - 9450675543","Samar Khan - 9889203242","Dhaval Dalia - 7753069418","Shivam jaiswal - 9795637067","---"};

    public EventsDb(Context context) {
        this.context = context;
        createentry();
    }


    public static class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE " + DATABASE_TABLE +" (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_SPORTSNAME + " TEXT NOT NULL, "
                    +KEY_WINNER + " TEXT NOT NULL, "+KEY_RUNNERUP + " TEXT NOT NULL, "+KEY_RULEBOOK + " TEXT NOT NULL, "+KEY_CONTACTA + " TEXT NOT NULL);");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
            onCreate(db);
        }
    }


    public void createentry() {

        dbhelperobject = new DBHelper(context);
        sportsdatabase = dbhelperobject.getWritableDatabase();
        ContentValues cv = new ContentValues();
        for(int i=0;i<18;i++){

            cv.put(KEY_SPORTSNAME, categorysportsname[i]);
            cv.put(KEY_WINNER, categorywinner[i]);
            cv.put(KEY_RUNNERUP, categoryrunnerup[i]);
            cv.put(KEY_RULEBOOK, categoryrulebook[i]);
            cv.put(KEY_CONTACTA, categorycontacta[i]);
            sportsdatabase.insert(DATABASE_TABLE, null, cv);
        }
        dbhelperobject.close();
    }


    public String[] GetSportDetail(long l) throws SQLException{

        dbhelperobject = new DBHelper(context);
        sportsdatabase = dbhelperobject.getWritableDatabase();

        String[] toprow = new String[]{KEY_ROWID,KEY_SPORTSNAME,KEY_WINNER,KEY_RUNNERUP,KEY_RULEBOOK,KEY_CONTACTA};
        Cursor cr = sportsdatabase.query(DATABASE_TABLE, toprow, KEY_ROWID + "=" +l,null,null,null,null);
        String[] data = new String[5];

        if(cr!=null){
            cr.moveToFirst();
           data[0] = cr.getString(1);
            data[1] = cr.getString(2);
            data[2] = cr.getString(3);
            data[3] = cr.getString(4);
            data[4] = cr.getString(5);

            return data;
        }
        dbhelperobject.close();
        return  null;
    }


}