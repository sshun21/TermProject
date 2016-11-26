package com.example.administrator.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2016-11-26.
 */

public class CheckInfo extends AppCompatActivity {
    Button buttonCheck;
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
    }
}
