package com.example.jikur.locationapp;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;
    private static boolean mIsFirstMap = false;
    private static final String PROXI_INTENT_KEY = "proximity"; //근접경보 intent 키 정의
    private ArrayList<PendingIntent> mPendingIntentList = new ArrayList<PendingIntent>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        String[] permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        ActivityCompat.requestPermissions(this, permissions, 1);

        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map).getMapAsync(this));

        startLocationService();

        //근접경보 위치 등록
        register(1000, 37.629285, 127.090435, 1000, 1000*60*60); //학교
        register(1000, 37.621787, 127.087897, 1000, 1000*60*60);

        ProxBroadcast pb= new ProxBroadcast(PROXI_INTENT_KEY);
        registerReceiver(pb, pb.getFilter());
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;

        googleMap.setMyLocationEnabled(true); //현재위치 버튼 설정
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng SEOUL = new LatLng(37.56, 126.97);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의수도");
        googleMap.addMarker(markerOptions);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(20));

    }

    private void startLocationService()
    {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        GPSListener gpsListener = new GPSListener();
        long minTime = 0; //0.8초
        float minDistance = 0;

        try {
            //퍼미션체크 (alt+enter 누르면 됨)
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            //*****GPS를 이용한 위치 요청 (실내x)
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    minTime, minDistance, gpsListener); //최소 5초에 한 번씩은 갱신 / 어디에서 처리할래?=gpsListener

            //*****네트워크를 이용한 위치 요청
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    minTime, minDistance, gpsListener);

            //마지막 사용했던 위치정보를 확인한다
            Location lastLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastLocation != null) {
                Double lat = lastLocation.getLatitude();
                Double lon = lastLocation.getLongitude();
                String msg = "마지막 위치: " + lat + "," + lon;
                ToastUtil.showToast(MapActivity.this, msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public class GPSListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();
            String msg = "Latitude(위도): " + latitude + "\nLongitude(경도): " + longitude;
            Log.i("TEST", msg);
            ToastUtil.showToast(MapActivity.this, msg);

            if (mGoogleMap != null) {
                if (!mIsFirstMap) {
                    LatLng latLng = new LatLng(latitude, longitude);
                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
                    mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                    mIsFirstMap = true;
                }
            }
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

    //근접경보 등록
    private void register(int id, double lat, double lon, float radius, long expiration) {

        Intent pIntent = new Intent(PROXI_INTENT_KEY);
        pIntent.putExtra("id", id);
        pIntent.putExtra("latitude", lat);
        pIntent.putExtra("longitude", lon);

        PendingIntent pi = PendingIntent.getBroadcast(MapActivity.this, id, pIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        lm.addProximityAlert(lat, lon, radius, expiration, pi);
        mPendingIntentList.add(pi);


    }
}