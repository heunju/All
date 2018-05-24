package com.example.jikur.resttest;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MemberListActivity extends AppCompatActivity {

    private ListView mListView;
    private MemberListAdapter memberListAdapter;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);

        List<MemberBean.MemberBeanSub> list = (List<MemberBean.MemberBeanSub>)
                getIntent().getSerializableExtra(Constants.INTENT_KEY_MEMBER_LIST);

        mListView = (ListView) findViewById(R.id.listView);
        memberListAdapter = new MemberListAdapter(this, mListView);
        mListView.setAdapter(memberListAdapter);

        MemberBean memberBean = FileUtil.getMemberBean(this);
        if(memberBean!=null)
        {
            MemberBean.MemberBeanSub subBean = memberBean.getMemberBean();
            Toast.makeText(this, memberBean.getMemberBean().getUserId() + ", " + subBean.getName(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        memberListAdapter.updateMemberListTask(); //데이터 갱신

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mListView.setSelection(pos);//스크롤 뷰 위치 설정
            }
        }, 500);
    }

    @Override
    public void onPause() {
        super.onPause();

        /*내가 만든 스크롤 위치 저장 */
        pos = mListView.getFirstVisiblePosition(); //현재 스크롤 뷰의 위치를 저장
        pos += 2;
    }
}


