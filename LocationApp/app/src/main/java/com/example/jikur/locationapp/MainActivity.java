package com.example.jikur.locationapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //권한 체크
        String[] permissions={Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION};
        ActivityCompat.requestPermissions(this, permissions, 1);

        //버튼 구현
        findViewById(R.id.btnLocStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocationService();
            }
        });

    }

    //위치정보확인 셋팅 시작
    private void startLocationService()
    {
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        GPSListener gpsListener = new GPSListener();
        long minTime = 800; //0.8초
        float minDistance = 0;

        try {
            //퍼미션 체크
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
            return;

            //GSP 를 이용한 위치 요청
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    minTime, minDistance, gpsListener);

            //네트워크를 이용한 위치 요청
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    minTime, minDistance, gpsListener);

            //마지막 사용했던 위치정보를 확인
            Location lastLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(lastLocation!=null)
            {
                Double lat = lastLocation.getLatitude();
                Double lon = lastLocation.getLongitude();

                String msg= "마지막 위치: " + lat+"," + lon;
                ToastUtil.showToast(MainActivity.this, msg);
            }
       }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    class GPSListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            String msg = "Latitude(위도): " + latitude + "\nLongitude(경도): " + longitude;
            Log.i("Test", msg);
            ToastUtil.showToast(MainActivity.this, msg);

        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
        @Override
        public void onProviderEnabled(String provider) {
        }
        @Override
        public void onProviderDisabled(String provider) {
        }
    }

}
