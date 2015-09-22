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
    public static final String KEY_CONTACTB = "contactb";
    public static final String KEY_NO_A = "no_a";
    public static final String KEY_NO_B = "no_b";


    public static final String DATABASE_NAME  = "sports_detail";
    public static final String DATABASE_TABLE = "eventdetail";
    private static final int DATABASE_VERSION = 3;

    private  final Context context;
    private SQLiteDatabase sportsdatabase;
    public DBHelper dbhelperobject;

    String[] categorysportsname = {"Athletics","Badminton","BasketBall","Boxing","Carrom","Chess","Cricket","FootBall","HandBall","Hockey","Kabaddi","KhoKho","Power Lifting","Squash","TaeKwonDo","Tennis","TableTennis","VolleyBall","WeightLifting"};
    String[] categorywinner = {"Click to Download","IIT Kharagpur","BRCM CET(Boys),\n\nMITS(Girls)","---","UGI(Boys),\n\nKIT(Girls)","IIT BHU","IIT BHU","IIT BHU","IIT BHU","MERI Kolkatta","IIT BHU","IIT BHU","---","GEC Ajmer","Click To Download","Shiv Nadar","IIT Roorkee","Shri Ram College","---"};
    String[] categoryrunnerup = {"Click to Download","IIT BHU","IIT BHU(Boys),\n\nIIT BHU(Girls)","---","BBD Lucknow(Boys),\n\nUGI(Girls)","IET Lucknow","BBD Lucknow","---","NIT Raipur","IIT BHU","YMCA Faridabad","IIT Mumbai","---","IIT BHU","Click to download","IIT BHU","SNU Delhi","IIT BHU","--"};
    String[] categoryrulebook = {"Athletics","Badminton","BasketBall","Boxing","Carrom","Chess","Cricket","FootBall","HandBall","Hockey","Kabaddi","KhoKho","Powerlifting","Squash","TaeKwonDo","Tennis","TableTennis","VolleyBall","WeightLifting"};
    String[] categorycontacta = {"Gaurav  Sharma","Abhishek Dhananjaya","Mohammad Shahbaz","---","---","Gopal Garg ","Venkat Subhash ","Mayank kumar Morya","---","Suyash Tripathi","---","Nilabh Nandan","--","Siddhartha bhargava","Prabhat Srivastava","Samar Khan","Dhaval Dalia","Shivam jaiswal","---"};
    String[] categorycontactb = {"---","---","Abhishek Dhayal","---","---","---","Dhruv Shikhar","Satyanand Tiwari","---","Prashant Kumar Singh","---","Prashanth Sai","---","---","---","---","---","Abhijit Mondal","---"};
    String[] contacta = {"+917860561883","+919532892608","+918601546468","---","---","+919897232528","+918953723461","+919919546976","---","+918953880553","---","+917752957824","---","+917275588982","+919450675543","+919889203242","+917753069418","+919795637067","---"};
    String[] contactb = {"---","---","+919473791571","---","---","---","+917376306960","+918960999789","---","+919616036867","---","+919492992254","---","---","---","---","---","+919454240708","---"};

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
            db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
            db.execSQL("CREATE TABLE " + DATABASE_TABLE +" (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_SPORTSNAME + " TEXT NOT NULL, "
                    +KEY_WINNER + " TEXT NOT NULL, "+KEY_RUNNERUP + " TEXT NOT NULL, "+KEY_RULEBOOK + " TEXT NOT NULL, "+KEY_CONTACTA + " TEXT NOT NULL, "+KEY_CONTACTB + " TEXT NOT NULL, "+KEY_NO_A+ " TEXT NOT NULL, "+KEY_NO_B+ " TEXT NOT NULL);");

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
        for(int i=0;i<19;i++){

            cv.put(KEY_SPORTSNAME, categorysportsname[i]);
            cv.put(KEY_WINNER, categorywinner[i]);
            cv.put(KEY_RUNNERUP, categoryrunnerup[i]);
            cv.put(KEY_RULEBOOK, categoryrulebook[i]);
            cv.put(KEY_CONTACTA, categorycontacta[i]);
            cv.put(KEY_CONTACTB, categorycontactb[i]);
            cv.put(KEY_NO_A, contacta[i]);
            cv.put(KEY_NO_B, contactb[i]);
            sportsdatabase.insert(DATABASE_TABLE, null, cv);
        }
        dbhelperobject.close();
    }


    public String[] GetSportDetail(long l) throws SQLException{

        dbhelperobject = new DBHelper(context);
        sportsdatabase = dbhelperobject.getWritableDatabase();

        String[] toprow = new String[]{KEY_ROWID,KEY_SPORTSNAME,KEY_WINNER,KEY_RUNNERUP,KEY_RULEBOOK,KEY_CONTACTA,KEY_CONTACTB,KEY_NO_A,KEY_NO_B};
        Cursor cr = sportsdatabase.query(DATABASE_TABLE, toprow, KEY_ROWID + "=" +l,null,null,null,null);
        String[] data = new String[8];

        if(cr!=null){
            cr.moveToFirst();
            data[0] = cr.getString(1);
            data[1] = cr.getString(2);
            data[2] = cr.getString(3);
            data[3] = cr.getString(4);
            data[4] = cr.getString(5);
            data[5] = cr.getString(6);
            data[6] = cr.getString(7);
            data[7] = cr.getString(8);

            return data;
        }
        dbhelperobject.close();
        return  null;
    }


}
