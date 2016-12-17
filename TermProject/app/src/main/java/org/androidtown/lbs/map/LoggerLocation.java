package org.androidtown.lbs.map;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.LinkedList;


public class LoggerLocation extends ActionBarActivity {

    private GoogleMap map;
    int studyCnt;
    int playCnt;
    int leisureCnt;
    int healthCnt;
    int etcCnt;
    TextView infoText;

    static LinkedList<LatLng> list = new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_location_logger);

        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        infoText=(TextView)findViewById(R.id.info_text);
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
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lon))
                    .title(title));
            if(category.contains("study"))
                studyCnt++;
            else if(category.contains("play"))
                playCnt++;
            else if(category.contains("leisure"))
                leisureCnt++;
            else if(category.contains("health"))
                healthCnt++;
            else if(category.contains("etc"))
                etcCnt++;

            infoText.setText("study:"+String.valueOf(studyCnt)+" play:"+String.valueOf(playCnt)+" leisure:"+String.valueOf(leisureCnt)+" health:"+String.valueOf(healthCnt)+" etc:"+String.valueOf(etcCnt));

        }

    }
    public void onResume(){
        super.onResume();
        map.setMyLocationEnabled(true);
    }

    public void onPause(){
        super.onPause();
        map.setMyLocationEnabled(false);
    }
//
//    private void startLocationService() {
//        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//        GPSListener gpsListener = new GPSListener();
//        long minTime = 10000;
//        float minDistance = 0;
//
//        manager.requestLocationUpdates(
//                LocationManager.GPS_PROVIDER,
//                minTime,
//                minDistance,
//                gpsListener);
//
//        manager.requestLocationUpdates(
//                LocationManager.NETWORK_PROVIDER,
//                minTime,
//                minDistance,
//                gpsListener);
//
//        Toast.makeText(getApplicationContext(), "위치 확인 시작함. 로그를 확인하세요.", Toast.LENGTH_SHORT).show();
//    }
//
//    private class GPSListener implements LocationListener {
//
//
//        public void onLocationChanged(Location location) {
//            Double latitude = location.getLatitude();
//            Double longitude = location.getLongitude();
//
//            String msg = " 위도 : "+ latitude + "  경도:"+ longitude;
//            Log.i("현재 위치는 <", msg+" >");
//
////            Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_SHORT).show();
//
//            LatLng point = new LatLng(latitude,longitude);
//
//
//            map.animateCamera(CameraUpdateFactory.newLatLngZoom(point, 17));
//            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//            if(list.size()>0){
//                map.addPolyline(new PolylineOptions().add(point,list.getLast()).width(5).color(Color.RED));
//            }
//            list.add(point);
//        }
//
//
//
//        public void onProviderDisabled(String provider) {
//        }
//
//        public void onProviderEnabled(String provider) {
//        }
//
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//        }
//
//    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
