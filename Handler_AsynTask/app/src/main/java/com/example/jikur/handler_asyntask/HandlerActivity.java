package com.example.jikur.handler_asyntask;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class HandlerActivity extends AppCompatActivity {

    private TextView mTextWork;
    private ProgressBar mProWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        mTextWork = (TextView) findViewById(R.id.txtWork);
        mProWork = (ProgressBar) findViewById(R.id.prbWork);
        mProWork.setMax(100);

        new Thread(run1).start();
    }

    //쓰레드 생성하기
    private Runnable run1 = new Runnable() {
        @Override
        public void run() {

            try {
                for (int i = 1; i <= 20; i++) {
                    //메인 쓰레드 작업을 위해서 호출한다.
                    //handle2.sendEmptyMessage(i*5);
                    Message msg= Message.obtain();
                    msg.what=1; //진행중 0일때는 중지
                    msg.arg1=i*5;
                    handle2.sendMessage(msg);

                    Thread.sleep(1000);


                }
            } catch (Exception e) {
                //Toast.makeText(HandlerActivity.this, "화면갱신 실패", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            handle2.sendEmptyMessage(0);
        }
    };

    private Handler handle2=new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            int value= msg.arg1;
            if(msg.what==0)
            {
                mTextWork.setText("Done");
            }
            else
            {
                mTextWork.setText(value +"%");
                mProWork.setProgress(value); //mProWork.incrementProgressBy(5); 와 같음
            }
        }
    };
}



