package com.example.jikur.resttest;

import java.io.Serializable;

/**
 * Created by jikur on 2017-07-24.
 */

//Serializable 하는 이유:
public class CommonBean implements Serializable
{

    private String result;
    private String resultMsg;

    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getResultMsg() {
        return resultMsg;
    }
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

}
