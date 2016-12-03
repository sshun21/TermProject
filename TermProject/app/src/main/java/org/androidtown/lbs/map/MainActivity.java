package org.androidtown.lbs.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    Button buttonInfo,buttonCheckInfo,buttonGoal,buttonShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonInfo=(Button)findViewById(R.id.button_insertInfo);
        buttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,InsertInfo.class);
                startActivity(i);
            }
        });
        buttonCheckInfo = (Button)findViewById(R.id.button_checkInfo);
        buttonCheckInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,CheckInfo.class);
                startActivity(i);
            }
        });
        buttonGoal = (Button)findViewById(R.id.button_goal);
        buttonGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,CheckGoal.class);
                startActivity(i);
            }
        });
        buttonShow=(Button)findViewById(R.id.button_show);
        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,LoggerLocation.class);
                startActivity(i);
            }
        });

    }
}
