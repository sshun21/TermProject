package org.androidtown.lbs.map;

/**
 * Created by Administrator on 2016-12-02.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE STATISTICS (_id INTEGER PRIMARY KEY AUTOINCREMENT, category TEXT,latitude REAL, longitude REAL, title TEXT, content TEXT, camera_url TEXT, timer INT );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert (String category, Double lat,Double lon,String title, String content, String camera_url, int timer) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO STATISTICS(category, latitude,longitude, title ,content ,camera_url ,timer ) VALUES(?,?,?,?,?,?,?);",new Object[]{category, lat, lon,title, content,camera_url,timer});
        db.close();
    }



}
