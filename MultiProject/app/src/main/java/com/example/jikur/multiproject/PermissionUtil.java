package com.example.jikur.multiproject;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jikur on 2018-03-02.
 */

public class PermissionUtil {

    private final int REQ_CODE;
    private final String[] permissions;

    //생성자
    public PermissionUtil(int REQ_CODE, String[] permissions)
    {
        this.REQ_CODE=REQ_CODE;
        this.permissions=permissions;
    }


    //버전 체크 후 권한 요청(마시맬로우 버전 이후부터는 한 번 더 체크해야함)
    public void check(Activity activity, IPermissionGrant pGrant)
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
            requestPermission(activity,pGrant); //마시맬로우 버전보다 크면 다시 권한 요청
        else
        {
            pGrant.run();
        }
    }


    //권한에 대한 승인여부
    @RequiresApi(api=Build.VERSION_CODES.M)
    private void requestPermission(Activity activity, IPermissionGrant pGrant) {

        List<String> requires = new ArrayList<>();
        for(String perm : permissions)
        {
            if (activity.checkSelfPermission(perm)!= PackageManager.PERMISSION_GRANTED)
            {
                requires.add(perm);
            }
        }

        //승인이 안된 권한이 있을 경우 승인요청
        if (requires.size()>0) //권한이 안됬을 경우
        {
            String[] perms = new String[requires.size()];
            perms=requires.toArray(perms);
            activity.requestPermissions(perms, REQ_CODE);
        }
        else
            pGrant.run();
    }

    public boolean afterPermissionResult(int requestCode, int[] grantResults, IPermissionGrant pGrant)
    {
        if(requestCode==REQ_CODE)
        {
            boolean granted = true;
            for(int result : grantResults)
            {
                if(result!=PackageManager.PERMISSION_GRANTED)
                    granted = false;
            }

            if(granted)
            {
                pGrant.run();
                return  true;
            }
            else
                pGrant.fail(); //승인이 안된경우
        }
        return false;
    }

    //인터페이스 선언
    public interface IPermissionGrant
    {
        void run();
        void fail();
    }
}
