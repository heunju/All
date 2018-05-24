package com.example.jikur.multiproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CallActivity extends AppCompatActivity {


    Button familyCall;
    Button emergencyCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        initView();
    }

    public void initView()
    {
        familyCall = findViewById(R.id.btn_familyCall);
        emergencyCall = findViewById(R.id.btn_emergencyCall);
    }


    public void familyCall(View view)
    {
        Intent intent = new Intent(this, FamilyCallActivity.class);
        startActivity(intent);
    }

    public void emergencyCall(View view)
    {
        Intent intent = new Intent(this, EmergencyCallActivity.class);
        startActivity(intent);
    }

}
