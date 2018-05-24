package com.example.jikur.locationapp;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by jikur on 2017-07-16.
 */

public class ToastUtil {
    private static Toast mToast;

    public static void showToast(Context context, String msg)
    {
        if(mToast!=null)
            mToast.cancel();

        mToast=Toast.makeText(context, msg, Toast.LENGTH_LONG);
        mToast.show();
    }

}
