package org.androidtown.lbs.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2016-11-26.
 */

public class InsertInfo extends Activity {
    Button buttonGoMain,buttonTakePic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_insert);
        buttonGoMain = (Button)findViewById(R.id.button_goMain);
        buttonGoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InsertInfo.this,MainActivity.class);
                startActivity(i);
            }
        });
        buttonTakePic = (Button)findViewById(R.id.button_takePic);
        buttonTakePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InsertInfo.this,TakePicAndStore.class);
                startActivity(i);
            }
        });
    }
}