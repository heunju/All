package com.example.jikur.study_health;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

//appCompatActivity가 아닌 이유는 main 전에 실행되야함. Activity는 appCompat보다 상위 에 있음
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //3초 동안 화면 실행 thread-catch로 에러 탐지
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace(); //안드로이드에서 오류메세지 뜸
        }

        Intent intent=new Intent(this, MainActivity.class); //여기서 this 는 나. 여기서는 = splashActivity
        startActivity(intent); //액티비티가 A->B->A 일 때 B에서 넘겨주는게 없을 때
        //startActivityForResult(); //액티비티가 A->B->A 일 때 B에서 처리한 것을 A에 넘겨줘야할 때

        finish(); //Activity를 메모리 상에서 지워주는 코드. 안해주면 뒤로가기 눌렀을 때 이 화면이 다시 나옴

    }
}
