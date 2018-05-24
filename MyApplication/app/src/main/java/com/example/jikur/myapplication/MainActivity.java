package com.example.jikur.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn_on);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = getPackageManager().getLaunchIntentForPackage("");

                startActivity(intent);



                /*
                //ComponentName compName = new ComponentName("com.android.phone", "com.android.phone.PhoneApp");

                //"com.google.android.gm",
                //                        "com.google.android.gm.ConversationListActivityGmail"

                Intent actIntent = new Intent(Intent.ACTION_MAIN);
                actIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                actIntent.setComponent(compName);
                startActivity(actIntent);
                */

            }
        });



    }



}
