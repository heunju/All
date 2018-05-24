package com.example.jikur.logintest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


// 2. View의 인자에 바로 Listener을 구현
public class MainActivity extends AppCompatActivity {

    //선언 먼저 해주고
    private EditText editId;
    private EditText editPassword;
    private Button btnSign;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //레이아웃과 자바 연동시키기
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSign = (Button) findViewById(R.id.btnSign);
        editId = (EditText) findViewById(R.id.editId);
        editPassword = (EditText) findViewById(R.id.editPassword);
    }

    public void login(View view) //xml에 login함수 정의
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void signUp(View view)
    {
        Intent intent = new Intent(this, SingUpActivity.class);
        startActivity(intent);
        finish();
    }
}






/*
// 1. OnClickListener Interface를 implements 하여 메서드를 구현
public class MainActivity extends AppCompatActivity implements View.OnClickListener{ //버튼이 많을 때 쓰기

    //선언 먼저 해주고
    private EditText editId;
    private EditText editPassword;
    private Button btnSign;
    private Button btnLogin;

    @Override //AppCompatActivity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //레이아웃과 자바 연동시키기
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnSign = (Button)findViewById(R.id.btnSign);
        editId = (EditText)findViewById(R.id.editId);
        editPassword = (EditText)findViewById(R.id.editPassword);

        btnLogin.setOnClickListener(this);
        btnSign.setOnClickListener(this);
    }

    @Override //VIew.OnclickListener
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.editId:
                Toast.makeText(this, "id를 입력하세요", Toast.LENGTH_SHORT).show();
                break;
            case R.id.editPassword:
                Toast.makeText(this, "pw를 입력하세요", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnSign:
                Intent intent1 = new Intent(getApplicationContext(), SingUpActivity.class);
                startActivity(intent1);
                break;
            case R.id.btnLogin:
                Intent intent2 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
*/

