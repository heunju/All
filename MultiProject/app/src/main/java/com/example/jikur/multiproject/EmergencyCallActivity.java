package com.example.jikur.multiproject;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EmergencyCallActivity extends AppCompatActivity {

    ListView listView;
    EmergencyCallAdapter adapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_call);
        context = getApplicationContext();


        listView = findViewById(R.id.listView);

        adapter = new EmergencyCallAdapter();
        adapter.addItem(new EmergencyCallItem("경찰", "000"));
        adapter.addItem(new EmergencyCallItem("화재 · 병원", "119"));
        adapter.addItem(new EmergencyCallItem("질병정보", "1339"));
        adapter.addItem(new EmergencyCallItem("의료복지시설", "02-712-9763"));
        adapter.addItem(new EmergencyCallItem("노인학대", "1577-1389"));
        adapter.addItem(new EmergencyCallItem("노인취업", "062-351-5070"));

        listView.setAdapter(adapter);


        //  클릭됬을 때 전화걸기
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                EmergencyCallItem item = (EmergencyCallItem) adapter.getItem(position);

                int permission = checkSelfPermission(Manifest.permission.CALL_PHONE);
                if(permission == PackageManager.PERMISSION_DENIED)
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+item.getNumber()));
                startActivity(intent);
            }
        });
    }


    //ListVIew 에 사용자가 표시할 데이터를 제공하기위해 Adapter 생성 후 ListView 와 연결
    public class EmergencyCallAdapter extends BaseAdapter {

        ArrayList<EmergencyCallItem> items = new ArrayList<EmergencyCallItem>();

        @Override
        public int getCount() {
            return items.size(); //item 개수만큼 count
        }

        //긴급 전화번호부 메소드 생성
        public void addItem(EmergencyCallItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        //EmergencyItemView return
        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            EmergencyCallItemView itemView = new EmergencyCallItemView(getApplicationContext());
            EmergencyCallItem item = items.get(position);

            itemView.setTitle(item.getTitle());
            itemView.setNumber(item.getNumber());

            return itemView;
        }
    }
}
