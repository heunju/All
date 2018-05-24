package com.example.jikur.multiproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button message;
    Button call;
    Button light;
    Button reading;
    Button dataonoff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }


    private void initView()
    {
        message=findViewById(R.id.btn_message);
        call=findViewById(R.id.btn_call);
        dataonoff=findViewById(R.id.btn_dataonoff);
        light = findViewById(R.id.btn_light);
        reading = findViewById(R.id.btn_reading);
    }



    public void message(View view)
    {
        Intent intent = new Intent(this, MessageActivity.class);
        startActivity(intent);
    }

    public void call(View view)
    {
        Intent intent = new Intent(this, CallActivity.class);
        startActivity(intent);
    }

    public void dataonoff(View view)
    {
        Intent intent = new Intent(this, DataOnOffActivity.class);
        startActivity(intent);
    }

    public void reading(View view)
    {
        Intent intent = new Intent(this, GlassActivity.class);
        startActivity(intent);
    }

    public void light(View view)
    {
        Intent intent = new Intent(this, LightActivity.class);
        startActivity(intent);
    }



}
