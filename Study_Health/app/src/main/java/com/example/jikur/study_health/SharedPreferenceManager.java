package com.example.jikur.study_health;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jikur on 2018-02-04.
 */

public class SharedPreferenceManager {

    private static String LOGIN = "login";

    private static SharedPreferenceManager spf = null;
    private static SharedPreferences.Editor LoginEditor;

    public static SharedPreferenceManager getInstance() {

        if (spf == null) {
            spf = new SharedPreferenceManager();
            return spf;
        } else
            return spf;
    }

    private Context context;
    private SharedPreferences loginSpf;

    private SharedPreferenceManager() //생성자선언 : 클래스가 실행됬을 때 어떤 작업을 할 것인지 초기값을 넣어줌
    {
        context = ApplicationInitializer.getAppContext();
        loginSpf = context.getSharedPreferences(LOGIN, Context.MODE_PRIVATE);
        LoginEditor = loginSpf.edit();
    }

    public void setId(String id)
    {
        LoginEditor.putString("ID", id);
    }
    public String getId()
    {
        return loginSpf.getString("ID", "");
    }

    public void setPw(String pw)
    {
        LoginEditor.putString("PW", pw);
    }
    public String getPw()
    {
        return loginSpf.getString("PW", "");
    }

    //로그아웃
    public void logout(){
        LoginEditor.clear();
        LoginEditor.commit();
    }

}
