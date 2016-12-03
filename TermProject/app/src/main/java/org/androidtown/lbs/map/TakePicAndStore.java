package org.androidtown.lbs.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016-11-26.
 */

public class TakePicAndStore extends Activity {
    Chronometer chronometer;
    Button buttonStart,buttonEnd,buttonTakePic;
    ImageView imageViewPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_picture_store);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        buttonStart = (Button)findViewById(R.id.button_start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.start();
            }
        });
        buttonEnd = (Button)findViewById(R.id.button_end);
        buttonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.stop();
            }
        });
        buttonTakePic=(Button)findViewById(R.id.button_takePic);
        imageViewPic =(ImageView)findViewById(R.id.imageView_Pic);
        buttonTakePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,1);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        imageViewPic.setImageURI(data.getData());
    }
}