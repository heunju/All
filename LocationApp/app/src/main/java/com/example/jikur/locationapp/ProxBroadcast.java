package com.example.jikur.locationapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;

//BroadCast = 1. IntentFilter 메소드 2. onReceive 메소드

public class ProxBroadcast extends BroadcastReceiver {

    private String mActionName;
    private Intent mLastReceivedIntent;

    public ProxBroadcast(String actionName)
    {
        mActionName=actionName;
        mLastReceivedIntent=null;
    }

    //intentFilter에 걸린 intent들은 onReceive 메소드로 들어온다
    public IntentFilter getFilter()
    {
        IntentFilter filter = new IntentFilter(mActionName);
        return filter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent!=null)
        {
            mLastReceivedIntent = intent;

            int id = intent.getIntExtra("id", 0); //있을 경우 id가, 없을 경우 0
            double lat = intent.getDoubleExtra("latitude", 0.00);
            double lon = intent.getDoubleExtra("longitude", 0.00);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("근접경보");
            builder.setMessage("현재 등록된 위치 근처에 도달하였습니다.");
            builder.create().show();
        }
    }
}
