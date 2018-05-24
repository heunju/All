package com.example.jikur.multiproject;

/**
 * Created by jikur on 2018-05-04.
 */

public class EmergencyCallItem{

    String title;
    String number;


    public EmergencyCallItem(String title, String number)
    {
        this.title=title;
        this.number=number;
    }

    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title=title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
