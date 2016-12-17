package org.androidtown.lbs.map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
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
    String camera_url;
    Button buttonStoreAll;
    int timer;
    Double latitude,longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DBHelper dbHelp = new DBHelper(getApplicationContext(), "shin.db" , null ,1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_picture_store);
        Intent i = getIntent();
        final String category = i.getStringExtra("category");
        final String title = i.getStringExtra("title");
        final String content = i.getStringExtra("content");
        startLocationService();
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
        buttonStoreAll = (Button)findViewById(R.id.button_storeAllData);
        buttonStoreAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.stop();
                timer =  (int)((SystemClock.elapsedRealtime() - chronometer.getBase())/1000);
                java.util.Calendar cal = java.util.Calendar.getInstance();
                String day=String.valueOf(cal.get(cal.YEAR)) + ":"+String.valueOf(cal.get(cal.MONTH)+1)+":"+String.valueOf(cal.get(cal.DATE));
                dbHelp.insert(category,latitude,longitude, title, content,camera_url,timer,day);
                Intent i = new Intent(TakePicAndStore.this,MainActivity.class);
                finish();
                startActivity(i);
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        imageViewPic.setImageURI(data.getData());
        camera_url=data.getData().toString();
    }
    private void startLocationService() {
        // 위치 관리자 객체 참조
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // 리스너 객체 생성
        GPSListener gpsListener = new GPSListener();
        long minTime = 10000;
        float minDistance = 0;

        try {
            // GPS 기반 위치 요청
            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    gpsListener);

            // 네트워크 기반 위치 요청
            manager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    minTime,
                    minDistance,
                    gpsListener);
        } catch(SecurityException ex) {
            ex.printStackTrace();
        }
    }

    private class GPSListener implements LocationListener {

        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

    }
}
