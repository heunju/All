package com.example.jikur.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * Created by jikur on 2017-07-11.
 */
//ListView 는 무조건 adapter 생성
public class NewsAdapter extends BaseAdapter {

    private Context context;
    private NewsBean newsBean;

    public NewsAdapter(Context context, NewsBean newsBean)
    {
        this.context=context;
        this.newsBean=newsBean;
    }

    @Override
    public int getCount() {
        return newsBean.getChannel().getItem().size();
    }

    @Override
    public Object getItem(int position) {
        return newsBean.getChannel().getItem().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //1. layout-xml 파일 가져오기
        LayoutInflater li=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=li.inflate(R.layout.lay_news, null);

        //2. 데이터 취득
        final NewsBean.Channel.item item=newsBean.getChannel().getItem().get(position);

        //3. 데이터 화면에 표시
        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
        TextView txtDate = (TextView) convertView.findViewById(R.id.txtDate);
        TextView txtDesc = (TextView) convertView.findViewById(R.id.txtDesc);

        txtTitle.setText(item.getTitle());
        txtDate.setText(item.getPubDate());
        txtDesc.setText(item.getDescription());

        convertView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_VIEW);
                Uri u = Uri.parse(item.getLink());
                it.setData(u);
                context.startActivity(it);
            }
        });
        return convertView;
    }
}
