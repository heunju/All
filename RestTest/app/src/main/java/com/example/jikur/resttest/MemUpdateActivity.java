package com.example.jikur.resttest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.net.URL;

public class MemUpdateActivity extends AppCompatActivity {

    private EditText edtJoinName, edtJoinId, edtJoinPw, edtJoinHp;
    private ImageView mImageProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_update);

        MemberBean.MemberBeanSub memberBean =  (MemberBean.MemberBeanSub)getIntent().getSerializableExtra("memberBean");

        edtJoinName = (EditText)findViewById(R.id.edtJoinName);
        edtJoinId = (EditText)findViewById(R.id.edtJoinId);
        edtJoinPw = (EditText)findViewById(R.id.edtJoinPw);
        edtJoinHp = (EditText)findViewById(R.id.edtJoinHp);
        mImageProfile = (ImageView) findViewById(R.id.imgProfile);

        //정보수정 버튼 이벤트 등록
        findViewById(R.id.btnJoinOk).setOnClickListener(btnJoinOk);

        edtJoinName.setText(memberBean.getName());
        edtJoinId.setText(memberBean.getUserId());
        edtJoinPw.setText(memberBean.getUserPw());
        edtJoinHp.setText(memberBean.getHp());

        new ImageLoaderTask(mImageProfile).execute(Constants.BASE_URL+memberBean.getProfileImg());

    }//end onCreate


    //정보수정 버튼 처리
    private View.OnClickListener btnJoinOk = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            //클릭했을 때 서버 업로드 = 쓰레드 = 테스크 선언
            new UpdateMemberTask().execute();
        }
    };


    //UpdateTask
    class UpdateMemberTask extends AsyncTask<String, Void, String> {
        private String URL_MEMBER_UPDATE = Constants.BASE_URL + "/rest/updateMember.do";
        String strJoinId, strJoinPw, strJoinName, strJoinHp;

        @Override
        protected void onPreExecute() {
            strJoinId = edtJoinId.getText().toString();
            strJoinPw = edtJoinPw.getText().toString();
            strJoinName = edtJoinName.getText().toString();
            strJoinHp = edtJoinHp.getText().toString();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                //restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplate.getMessageConverters().add(new FormHttpMessageConverter());

                MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
                map.add("userId", strJoinId);
                map.add("userPw", strJoinPw);
                map.add("name", strJoinName);
                map.add("hp", strJoinHp);
                //초록색은 멤버변수가 입력되어야한다.

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.ALL.APPLICATION_FORM_URLENCODED);

                HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);

                return restTemplate.postForObject(URL_MEMBER_UPDATE, request, String.class);
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
                if (bean != null) {
                    if (bean.getResult().equals("ok")) {
                        finish();
                    }else
                    {
                        Toast.makeText(MemUpdateActivity.this, bean.getResultMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (Exception e) {
                Toast.makeText(MemUpdateActivity.this, "파싱실패", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }


    //이미지 비동기 로딩 Task
    class ImageLoaderTask extends AsyncTask<String, Void, Bitmap> {

        private ImageView dispImageView;

        public ImageLoaderTask(ImageView dispImgView) {
            this.dispImageView =dispImgView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            String imgUrl = params[0];

            Bitmap bmp = null;

            try {
                bmp = BitmapFactory.decodeStream(  (InputStream)new URL(imgUrl).getContent()  );
            } catch (Exception e) {
                e.printStackTrace();
            }

            return bmp;
        }//end doInBackground()


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap != null){
                //표시
                dispImageView.setImageBitmap(bitmap);

            }
        }
    };
}
