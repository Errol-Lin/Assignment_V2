package s3534890.com.eventplanner.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import s3534890.com.eventplanner.Model.Events;

/**
 * Created by Errol on 19/09/16.
 */
public class SQLHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "EventDatabase";
    private static final String TABLE_NAME = "EventTable";
    private static final int DATABASE_VERSION = 1;
    private static final String EVENT_ID = "EVENT_ID";
    private static final String EVENT_NAME = "EVENT_NAME";
    private static final String ADDED = "ADDED";
    private static final String START_DATE = "START_DATE";
    private static final String START_TIME = "START_TIME";
    private static final String END_DATE = "END_DATE";
    private static final String END_TIME = "END_TIME";
    private static final String VENUE = "VENUE";
    private static final String LOCATION = "LOCATION";
    private static final String ATTENDEE = "ATTENDEE";
    private static final String NOTES = "NOTES";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + EVENT_ID + " VARCHAR(255) PRIMARY KEY," + EVENT_NAME + " VARCHAR(255)," + ADDED + " INTEGER," + START_DATE + " INTEGER," + START_TIME + " VARCHAR(100)," + END_DATE + " VARCHAR(100)," + END_TIME + " VARCHAR(100)," + VENUE + " VARCHAR(100)," + LOCATION + " VARCHAR(100)," + ATTENDEE + " VARCHAR(100)," + NOTES + " VARCHAR(100));";

    public SQLHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(CREATE_TABLE);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        try{
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
            onCreate(db);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void insert(String ID, String name, long added, long startD, String startT, String endD, String endT, String venue, String location, String attendee, String notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EVENT_ID,ID);
        contentValues.put(EVENT_NAME,name);
        contentValues.put(ADDED,added);
        contentValues.put(START_DATE,startD);
        contentValues.put(START_TIME,startT);
        contentValues.put(END_DATE,endD);
        contentValues.put(END_TIME,endT);
        contentValues.put(VENUE,venue);
        contentValues.put(LOCATION,location);
        contentValues.put(ATTENDEE,attendee);
        contentValues.put(NOTES,notes);
        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }

    public void loadData(){
        SQLiteDatabase db = this.getWritableDatabase();

        String[] columns = {EVENT_ID,EVENT_NAME,ADDED,START_DATE,START_TIME,END_DATE,END_TIME,VENUE,LOCATION,ATTENDEE,NOTES};
        Cursor cursor = db.query(TABLE_NAME,columns,null,null,null,null,null);

        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            long added = cursor.getInt(2);
            long startD = cursor.getInt(3);
            String startT = cursor.getString(4);
            String endD = cursor.getString(5);
            String endT = cursor.getString(6);
            String venue = cursor.getString(7);
            String location = cursor.getString(8);
            String attendee = cursor.getString(9);
            String notes = cursor.getString(10);
//            Events events = new Events(id,added,name,startD,attendee,notes,endD,startT,endT,venue,location);
//            RecyclerViewAdapter.mResults.add(events);
        }
    }
}
