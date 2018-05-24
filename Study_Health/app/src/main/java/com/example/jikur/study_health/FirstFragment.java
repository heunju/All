package com.example.jikur.study_health;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//empty 액티비티로 생성
public class FirstFragment extends android.support.v4.app.Fragment{

    //생성자 선언
    public FirstFragment()
    {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //view를 만들어줘서 return. activity_first_fragment를 인플레이트해(엑티비티가 아님)

        View view = inflater.inflate(R.layout.activity_first_fragment, container,false);
        return view;
    }
}
