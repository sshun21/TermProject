package org.androidtown.lbs.map;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2016-11-26.
 */

public class CheckGoal extends Activity {
    Button buttonCheck;
    int studySec;
    int playSec;
    int leisureSec;
    int healthSec;
    int etcSec;
    TextView textDest;
    Button check,view;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goal_check);
        textDest=(TextView)findViewById(R.id.textview_des);
        buttonCheck = (Button)findViewById(R.id.button_check);
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CheckGoal.this,MainActivity.class);
                startActivity(i);
            }
        });
        check=(Button)findViewById(R.id.check);
        view=(Button)findViewById(R.id.view);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textDest.setText("나의 목표\nstudy:3000sec\nplay:3000sec\nleaisure:3000sec \nhealth:3000sec\netc:3000sec");
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(getApplicationContext(), "shin.db" , null ,1);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor results = db.rawQuery("SELECT * FROM STATISTICS", null);
                String category ="";

                while(results.moveToNext()) {
                    double lat = results.getDouble(2);
                    lat = Double.parseDouble(String.format("%.7f",lat));
                    double lon = results.getDouble(3);
                    lon = Double.parseDouble(String.format("%.7f",lon));
                    String title = results.getString(4);
                    category = results.getString(1);
                    int timer=results.getInt(7);
                    if(category.contains("study"))
                        studySec+=timer;
                    else if(category.contains("play"))
                        playSec+=timer;
                    else if(category.contains("leisure"))
                        leisureSec+=timer;
                    else if(category.contains("health"))
                        healthSec+=timer;
                    else if(category.contains("etc"))
                        etcSec+=timer;

                    textDest.setText("나의 목표\nstudy:"+String.valueOf(3000-studySec)+"sec"+"\nplay:"+String.valueOf(3000-playSec)+"sec\nleaisure:"+String.valueOf(3000-leisureSec)+"sec \nhealth:"+String.valueOf(3000-healthSec)+"sec\netc:"+String.valueOf(3000-etcSec)+"sec");

                }

            }
        });


    }
}
