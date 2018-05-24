package com.example.jikur.samplelistview2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

//activity_main을 보여주는 화면
//singeritemview
//singeritem은 디자인X, 들어갈 요소들만 저장

public class MainActivity extends AppCompatActivity {

    ListView listView;
    SingerAdpater adapter;
    //adpater: 틀을 정리한 singer view의 인스턴스를 저장, 저장한 singeritemview들을 listview에 추가
    //singeritemview의 인스턴스를 저장

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //main.xml 호출

        listView = (ListView) findViewById(R.id.listView);

        adapter = new SingerAdpater();

        adapter.addItem(new SingerItem("TAE YEON", "010-1000-1000", 20, R.drawable.singer1));
        adapter.addItem(new SingerItem("DONG HAE", "010-2000-2000", 22, R.drawable.singer2));
        adapter.addItem(new SingerItem("BAEK HYUN", "010-3000-3000", 21, R.drawable.singer3));
        adapter.addItem(new SingerItem("SUZY", "010-4000-4000", 24, R.drawable.singer4));
        adapter.addItem(new SingerItem("MIN HYUk", "010-5000-5000", 25, R.drawable.singer5));

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //item이 클릭됬을 때, 기능을 하도록 하는
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "선택: " + item.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }


    class SingerAdpater extends BaseAdapter { //adpater는 채울 것 만들어서 listview에 return. 여러개가 있어도 하나만 선언하면 됨
        ArrayList<SingerItem> items = new ArrayList<SingerItem>(); //정의한 인스턴스들, 즉 SingerItem의 인스턴스를 선언
        //< >안에는 변수 타입, items는 변수이름

        @Override
        public int getCount() {
            return items.size();
        } //BaseAdapter에서 정의된 함수. items의 저장된 item의 개수만큼 -> item.size()

        public void addItem(SingerItem item) {
            items.add(item);
        } //앞에 선언한 5개의 additem호출

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override

        //View getView: singeritem뷰를 리턴
        public View getView(int position, View convertView, ViewGroup viewGroup) { //position을 자동으로 0~4까지(additem에 5개 선언)
            SingerItemView2 view = new SingerItemView2(getApplicationContext());
            SingerItem item = items.get(position); //position번째 item을 내옴

            view.setName(item.getName()); //getName()은 SIngerItem.java에서 호출, setName는 SingerItemView호출
            view.setMobile(item.getMobile());
            view.setAge(item.getAge());
            view.setImage(item.getResId());

            return view; //singerItemview 타입의 인스턴스(view)를 만든 후 리턴
        }
    }
}