package com.example.jikur.resttest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtName, edtTel, edtId, edtPw;
    private Button btnJoin;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtName = (EditText)findViewById(R.id.edtName);
        edtTel = (EditText)findViewById(R.id.edtTel);
        edtId = (EditText)findViewById(R.id.edtId);
        edtPw = (EditText)findViewById(R.id.edtPw);
        mProgressBar = (ProgressBar)findViewById(R.id.progressBar3);


        //회원가입 버튼 클릭 처리
        findViewById(R.id.btnJoin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RegisterTask().execute();
            }
        });
    }




    //회원가입 처리
    private class RegisterTask extends AsyncTask<String, Void, String> {

        public static final String URL_LOGIN_PROC = "http:// 220.116.191.118:8080/rest/insertMember.do";
        private String userId, userPw, name, tel;

        @Override
        protected void onPreExecute() {
            //프로그레스 다이얼로그 표시
            mProgressBar.setVisibility(View.VISIBLE);

            name = edtName.getText().toString();
            tel = edtTel.getText().toString();
            userId = edtId.getText().toString();
            userPw = edtPw.getText().toString();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                RestTemplate restTemplate = new RestTemplate();
                //restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplate.getMessageConverters().add(new FormHttpMessageConverter());

                MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
                map.add("name", name);
                map.add("tel", tel);
                map.add("userId", userId);
                map.add("userPw", userPw);


                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.ALL.APPLICATION_FORM_URLENCODED);

                HttpEntity<MultiValueMap<String,Object>> request = new HttpEntity<>(map, headers);

                return restTemplate.postForObject(URL_LOGIN_PROC, request, String.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            mProgressBar.setVisibility(View.INVISIBLE);

            Gson gson = new Gson();
            try {
                MemberBean bean = gson.fromJson(s, MemberBean.class);
                if(bean != null) {
                    if( bean.getResult().equals("ok") ) {
                        //로그인 성공
                        Intent i = new Intent(RegisterActivity.this, LoginSuccActivity.class);
                        startActivity(i);
                    } else {
                        //로그인 실패
                        Toast.makeText(RegisterActivity.this, bean.getResultMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (Exception e) {
                Toast.makeText(RegisterActivity.this, "파싱실패", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    };
}