package org.androidtown.lbs.map;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-11-26.
 */

public class CheckInfo extends Activity {
    Button buttonCheck,changeButton;
    ListView listView;
    String insert;
    int cnt=0;
    String category;
    Double lat;
    Double lon;
    String title;
    String content;
    String camera_url;
    int timer;
    String day;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_info);

        buttonCheck = (Button)findViewById(R.id.button_check);
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CheckInfo.this,MainActivity.class);
                startActivity(i);
            }
        });
        listView = (ListView)findViewById(R.id.listview_info);

        ArrayList<Listviewitem> data=new ArrayList<>();
        DBHelper dbHelper = new DBHelper(getApplicationContext(), "shin.db" , null ,1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor results = db.rawQuery("SELECT * FROM STATISTICS", null);
        while(results.moveToNext()) {
            category = results.getString(1);
            lat = results.getDouble(2);
            lat = Double.parseDouble(String.format("%.7f",lat));
            lon = results.getDouble(3);
            lon = Double.parseDouble(String.format("%.7f",lon));
            title = results.getString(4);
            content = results.getString(5);
            camera_url = results.getString(6);
            timer = results.getInt(7);
            day = results.getString(8);
            Uri uri = Uri.parse(camera_url);


            insert = category+ "   \t"+String.valueOf(lat)+ "    \t"+String.valueOf(lon)+ "\n"+title+ "\t"+content+  "\n"+String.valueOf(timer)+"초"+ "\t"+day;

            Listviewitem new_data = new Listviewitem(uri, insert);

            data.add(new_data);
        }
        ListviewAdapter adapter=new ListviewAdapter(this,R.layout.listviewitem,data);
        listView.setAdapter(adapter);

    }
}
