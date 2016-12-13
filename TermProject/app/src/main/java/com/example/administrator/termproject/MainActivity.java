package com.example.administrator.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
//가자 논산으로
public class MainActivity extends AppCompatActivity {
    Button buttonInfo,buttonCheckInfo,buttonGoal;
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

    }
}
