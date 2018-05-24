package com.example.jikur.multiproject;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by jikur on 2018-05-04.
 */

public class EmergencyCallItemView  extends LinearLayout {

    TextView txtTitle;
    TextView txtNumber;

    public EmergencyCallItemView(Context context) {
        super(context); //부모 클래스
        init(context); //내 클래스
    }

    public EmergencyCallItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs); //부모 클래스
        init(context); //내 클래스
    }

    //내 클래스
    //layout 생성 = inflate 한다
    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.emergencycallitem, this, true);

        txtTitle = findViewById(R.id.txtTitle);
        txtNumber = findViewById(R.id.txtNumber);
    }

    public void setTitle(String title)
    {
        txtTitle.setText(title);
    }

    public void setNumber(String number)
    {
        txtNumber.setText(number);
    }


}
