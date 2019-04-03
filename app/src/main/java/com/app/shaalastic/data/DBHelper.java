package com.app.shaalastic.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public final static int DB_VERSION=1;
    public final static String DB_NAME="user.db";
    public final static String USER_TABLE="user";
    public final static String COLUMN_ID="id";
    public final static String COLUMN_NAME="name";
    public final static String COLUMN_PASSWORD="password";
    public final static String COLUMN_INSTITUTE="institute";
    public final static String COLUMN_ROLE="role";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+USER_TABLE+" ("+
                COLUMN_INSTITUTE+" TEXT, "+
                COLUMN_ID+" TEXT, "+
                COLUMN_NAME+" TEXT, "+
                COLUMN_PASSWORD+" TEXT, "+
                COLUMN_ROLE+" INTEGER "+
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}