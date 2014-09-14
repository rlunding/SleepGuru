package org.lunding.sleepguru;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Lunding on 13/09/14.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = DBHelper.class.getSimpleName();
    private SQLiteDatabase db;

    public DBHelper(Context context){
        super(context, StatusContract.DB_NAME, null, StatusContract.DB_VERSION);
        db = getWritableDatabase();
        Log.d(TAG, "DBHelper constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = String.format(
                "create table %s (%s integer primary key autoincrement, %s varchar(64), %s integer, %s datetime)",
                    StatusContract.TABLE,
                    StatusContract.Column.ID,
                    StatusContract.Column.USER,
                    StatusContract.Column.SCORE,
                    StatusContract.Column.CREATED_AT);
        Log.d(TAG, "onCreate with SQL: " + sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists " + StatusContract.TABLE);
        onCreate(db);
        Log.d(TAG, "Tables upgraded");
    }
}
