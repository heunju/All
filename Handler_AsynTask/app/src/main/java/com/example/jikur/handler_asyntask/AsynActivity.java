package com.example.jikur.handler_asyntask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AsynActivity extends AppCompatActivity {

    private TextView mTextWork;
    private ProgressBar mProWork;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyn);

        mTextWork = (TextView) findViewById(R.id.txtWork);
        mProWork = (ProgressBar) findViewById(R.id.prbWork);
        mProWork.setMax(100);

        new TaskRun1().execute();
    }

    private class TaskRun1 extends AsyncTask<Void, Integer, String> {

        private ProgressDialog prd;

        @Override
        protected void onPreExecute()
        {
            //쓰레드가 즉 doInBackground가 실행되기 전에 실행된다.
            prd=new ProgressDialog(AsynActivity.this);
            prd.setMessage("현재 업데이트 중...");
            prd.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) //업데이트 갱신되는 부분
        {
            mTextWork.setText(values[0]+"%");
            mProWork.setProgress(values[0]);
        }

        @Override
        protected String doInBackground(Void... params)  //thread영역
        {
            for (int i = 1; i <= 20; i++) {
                publishProgress(i * 5);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Done";
        }

        @Override
        protected void onPostExecute(String str)
        {
            prd.dismiss();

            if(str!=null)
                mTextWork.setText(str);
        }
    };
}
