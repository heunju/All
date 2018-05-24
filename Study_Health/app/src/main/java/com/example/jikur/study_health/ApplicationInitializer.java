package com.example.jikur.study_health;

import android.app.Application;
import android.content.Context;

/**
 * Created by jikur on 2018-02-04.
 */

//어플이 켜질때 제일 먼저 실행, context를 sharedPreferenceManager에게 전달
    //어플의 context를 받아와서 -> SharedPreferenceManager GetInstance(spf가 있으면 있는거 반환, 없으면 new로 생성)
public class ApplicationInitializer extends Application{

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationInitializer.context = getApplicationContext();
        SharedPreferenceManager.getInstance();
    }

    public static Context getAppContext()
    {
        return ApplicationInitializer.context;
    }

}
