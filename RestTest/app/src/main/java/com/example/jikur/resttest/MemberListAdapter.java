package com.example.jikur.resttest;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jikur on 2017-07-25.
 */

public class MemberListAdapter extends BaseAdapter {

    private Context mContext;
    private ListView mListView;
    private List<MemberBean.MemberBeanSub> memberList = new ArrayList<MemberBean.MemberBeanSub>();

    //생성자
    public MemberListAdapter(Context context, ListView listView)
    {
        mContext=context;
        mListView = listView;
        updateMemberListTask();
    }

    @Override
    public int getCount() {
        return memberList.size();
    }

    @Override
    public Object getItem(int position) {
        return memberList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void updateMemberListTask() {
        new MemberListTask().execute();
    }

    //인플레이션
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        //1. 인플레이트 해라.
        LayoutInflater li = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //2. 인플레이터 한 레이아웃에서 컴포넌트들을 찾는다.
        convertView=li.inflate(R.layout.view_mem_list, null);

        TextView txtName = (TextView)convertView.findViewById(R.id.txtName);
        TextView txtId = (TextView)convertView.findViewById(R.id.txtId);
        ImageView imgProfile = (ImageView)convertView.findViewById(R.id.imgProfile);

        //3. 데이터를 가져온다.
        final MemberBean.MemberBeanSub bean = memberList.get(position);

        //3. 찾은 컴포넌트에게 데이터 대입
        new ImageLoaderTask(imgProfile).execute( Constants.BASE_URL + bean.getProfileImg());
        txtName.setText(bean.getName());
        txtId.setText(bean.getUserId());



        //클릭하면 수정화면으로 이동 (
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(mContext, MemUpdateActivity.class);
                intent.putExtra("memberBean", bean);
                mContext.startActivity(intent);

                //PrefUtil.setPref(mContext, "listViewSelection", position+"");
                //Toast.makeText(mContext, position+"", Toast.LENGTH_LONG).show();
            }
        });
        return convertView;
    }


    //회원정보를 가져오는 Task 선언
    class MemberListTask extends AsyncTask<String, Void, String>
    {
        private String URL_MEMBER_LIST = Constants.BASE_URL + "/rest/selectMemberList.do";

        @Override
        protected String doInBackground(String... params) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                //restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplate.getMessageConverters().add(new FormHttpMessageConverter());

                MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.ALL.APPLICATION_FORM_URLENCODED);

                HttpEntity<MultiValueMap<String,Object>> request = new HttpEntity<>(map, headers);

                return restTemplate.postForObject(URL_MEMBER_LIST, request, String.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            Gson gson = new Gson();
            try {
                MemberBean bean = gson.fromJson(s, MemberBean.class);
                if(bean != null) {
                    if( bean.getResult().equals("ok") ) {
                        memberList=bean.getMemberList();
                        //리스트뷰 갱신 ***중요***
                        MemberListAdapter.this.notifyDataSetInvalidated();
                    }
                }
            } catch (Exception e) {
                Toast.makeText(mContext, "파싱실패", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}
