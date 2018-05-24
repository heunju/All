package com.example.jikur.study_health;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

//한 액티비티 안에 2개 이상의 view를 보여줄 수 있음: fragment
    public class ViewPagerActivity extends AppCompatActivity {

        ViewPager viewPager;
        TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        initView();
        setAdapter();
        setListener();
    }

    private void initView()
    {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
    }

    private void setAdapter()
    {
        CustomAdapter customAdapter = new CustomAdapter(getSupportFragmentManager());
        viewPager.setAdapter(customAdapter);
    }

    public void setListener()
    {
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));  //
        //탭 버튼을 눌렀을 때 어떤 작동을 하게 할 것인가? 탭을 눌렀을 때 뷰페이저를 바꾸겠다.
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout)); //뷰페이지가 바꼈을 때 어떻게 작동?

    }


}
