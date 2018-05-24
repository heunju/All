package com.example.jikur.study_health;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //private 하는 이유 : 다른 엑티비티에서 같은 이름이 있을 때 그 엑티비티 것을 쓰기위해
    //private 는 접근제한자. 해당 클래스에서만 사용가능

    Button btn_login;
    Button btn_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        //자바 클래스의 btn_login과 디자인의 btn_login과 같다는 코드
        btn_login=findViewById(R.id.btn_profile);
        btn_signUp=findViewById(R.id.btn_signUp);
    }

    public void login(View v)
    {
        Intent intent = new Intent(this, ViewPagerActivity.class);
        startActivity(intent);
        finish();
    }

    public void signUp(View v)
    {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
