package com.example.jikur.study_health;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by jikur on 2018-03-09.
 */

//viewpager와 tablayout과 연결해주는 Adapter
class CustomAdapter extends FragmentStatePagerAdapter {

    private static int COUNT = 3;

    //생성자
    public CustomAdapter(FragmentManager fm) {
        super(fm);
    }

    //버튼에 따라서 포지션을 다르게
    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new SecondFragment();
            case 1:
                return new ThirdFragment();
            case 2:
                return new ThirdFragment();
            //초기값
            default:
                return new FirstFragment();
        }
    }

    //viewPager에 fragment가 몇 개 들어가있다.
    @Override
    public int getCount() {
        return COUNT;
    }
}
