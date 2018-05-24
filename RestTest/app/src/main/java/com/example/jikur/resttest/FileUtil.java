package com.example.jikur.resttest;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by jikur on 2017-08-01.
 */

public class FileUtil {

    //멤버빈 저장
    public static void setMemberBean(Context context, MemberBean bean) {
        try {
            FileOutputStream fos = context.openFileOutput(MemberBean.class.getName(), Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(bean);
            os.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MemberBean getMemberBean(Context context) {
        MemberBean memBean = null;
        try {
            FileInputStream fis = context.openFileInput(MemberBean.class.getName());
            ObjectInputStream is = new ObjectInputStream(fis);
            memBean = (MemberBean) is.readObject();
            is.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return memBean;
    }

}
