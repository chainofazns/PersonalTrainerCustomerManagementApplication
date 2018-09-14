package com.bignerdranch.android.personaltrainercustomermanagementapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class PTCMDatabase {
    /*
    * Keys
    * */

    //primary key
    public static final String KEY_ID = "_id";
    public static final String KEY_FIRST_NAME = "FIRST_NAME";
    public static final String KEY_LAST_NAME = "LAST_NAME";
    public static final String KEY_DATE_OF_BIRTH = "DATE_OF_BIRTH";
    public static final String KEY_SESSIONS = "SESSIONS";
    public static final String KEY_CARD_NUMBER = "CARD_NUMBER";
    public static final String KEY_MONTH = "MONTH";
    public static final String KEY_YEAR = "YEAR";
    public static final String KEY_CVC = "CVC";
    public static final String KEY_ADDRESS1 = "ADDRESS1";
    public static final String KEY_ADDRESS2 = "ADDRESS2";
    public static final String KEY_CITY = "CITY";
    public static final String KEY_STATE = "STATE";
    public static final String KEY_ZIPCODE = "ZIPCODE";

    //shove all variables here

    //Database Open/upgrade helper
    private PTCMDBOpenHelper ptcmDBOpenHelper;

    public PTCMDatabase (Context context){
        ptcmDBOpenHelper = new PTCMDBOpenHelper(context, PTCMDBOpenHelper.DATABASE_NAME, null, PTCMDBOpenHelper.DATABASE_VERSION);
        System.out.println("Database Created");
    }

    //close database
    public void closeDatabase(){
        ptcmDBOpenHelper.close();
    }

    public void addNewCustomer(String firstName, String lastName, String date, int sessions, String cardNumber, int month,  int year, int cvc,
                               String address1, String address2, String city, String state, String zipcode){
        //creating a new row for new customer
        ContentValues newCustomer = new ContentValues();

        newCustomer.put(KEY_FIRST_NAME, firstName);
        newCustomer.put(KEY_LAST_NAME, lastName);
        newCustomer.put(KEY_DATE_OF_BIRTH, date);
        newCustomer.put(KEY_SESSIONS, sessions);
        newCustomer.put(KEY_CARD_NUMBER, cardNumber);
        newCustomer.put(KEY_MONTH, month);
        newCustomer.put(KEY_YEAR, year);
        newCustomer.put(KEY_CVC, cvc);
        newCustomer.put(KEY_ADDRESS1, address1);
        newCustomer.put(KEY_ADDRESS2, address2);
        newCustomer.put(KEY_CITY, city);
        newCustomer.put(KEY_STATE, state);
        newCustomer.put(KEY_ZIPCODE, zipcode);

        //insert row into table
        SQLiteDatabase db = ptcmDBOpenHelper.getWritableDatabase();
        db.insert(PTCMDBOpenHelper.DATABASE_TABLE, null, newCustomer);

    }

    public Cursor getCustomer(){
        SQLiteDatabase db = ptcmDBOpenHelper.getWritableDatabase();

        Cursor cursor = db.query(
                PTCMDBOpenHelper.DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }



    //DB Open Helper
    private static class PTCMDBOpenHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "myDatabase.db";
        private static final String DATABASE_TABLE = "Customer";
        private static final int DATABASE_VERSION = 1;

        // SQL Statement to create a new database.
        private static final String DATABASE_CREATE = "create table " +
                DATABASE_TABLE + " (" + KEY_ID + " integer primary key autoincrement, " + KEY_FIRST_NAME + " text not null, " + KEY_LAST_NAME + " text not null, " + KEY_DATE_OF_BIRTH + " text not null, "
                + KEY_SESSIONS + " integer not null, " + KEY_CARD_NUMBER +  " integer not null, " + KEY_MONTH  + " integer not null, " + KEY_YEAR +  " integer not null, " + KEY_CVC +  " integer not null, "
                + KEY_ADDRESS1 +  " text not null, " + KEY_ADDRESS2 +  " text , " + KEY_CITY + " text not null, " + KEY_STATE + " text not null, " + KEY_ZIPCODE + " text not null);" ;
                ;

        public PTCMDBOpenHelper(Context context, String name,
                                 CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // Called when no database exists in disk and the helper class needs
        // to create a new one.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        // Called when there is a database version mismatch meaning that
        // the version of the database on disk needs to be upgraded to
        // the current version.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
            // Log the version upgrade.
            Log.w("TaskDBAdapter", "Upgrading from version " +
                    oldVersion + " to " +
                    newVersion + ", which will destroy all old data");

            // Upgrade the existing database to conform to the new
            // version. Multiple previous versions can be handled by
            // comparing oldVersion and newVersion values.

            // The simplest case is to drop the old table and create a new one.
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            // Create a new one.
            onCreate(db);
        }
    }
}
