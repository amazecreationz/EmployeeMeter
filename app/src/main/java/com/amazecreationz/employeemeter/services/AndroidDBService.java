package com.amazecreationz.employeemeter.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.amazecreationz.employeemeter.constants.SQLConstants;

/**
 * Created by Anand Mohan<moghan.anand@gmail.com> on 14/4/17 3:04 PM.
 */

public class AndroidDBService extends SQLiteOpenHelper implements SQLConstants {

    public AndroidDBService(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("moghan", "hello here");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("moghan", "hello");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
