package com.example.jikur.jsontest;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new NewsTask().execute("http://www.showpun.com/api/common/v1/adDeal/selectAdDealListAjax.ans?adDealGrpId=1");
        mListView = (ListView)findViewById(R.id.listView);
    }

    class NewsTask extends AsyncTask<String, String, JsonBean>
    {
        private ProgressDialog prd;
        @Override
        protected void onPreExecute()
        {
            prd = new ProgressDialog(MainActivity.this);
            prd.setMessage("뉴스를 가져오는 중입니다...");
            prd.setCancelable(false);
            prd.show();
        }

        @Override
        protected JsonBean doInBackground(String... params) {

            StringBuilder jsonData = new StringBuilder();

            try{
                URL url = new URL(params[0]);
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String line = null;

                while(true)
                {
                    line = reader.readLine();
                    if(line == null) break;
                    jsonData.append(line + "\n");
                }
                reader.close();

                String xmlData = jsonData.toString();
                //파싱시작
                Gson gson = new GsonBuilder().create();
                JsonBean jsonBean = gson.fromJson(xmlData, JsonBean.class);

                return jsonBean;
           }catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JsonBean jsonBean) {
            //다이얼로그 제거
            prd.dismiss();

            if (jsonBean != null) {
                //1. 어댑터 생성
                JsonAdapter jsonAdapter = new JsonAdapter(MainActivity.this, jsonBean);
                //2. setAdapter()
                mListView.setAdapter(jsonAdapter);

            }
        }
    }
}
