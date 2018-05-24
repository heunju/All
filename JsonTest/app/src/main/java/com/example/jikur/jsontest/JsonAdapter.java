package com.example.jikur.jsontest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by jikur on 2017-07-12.
 */

public class JsonAdapter extends BaseAdapter{

    private Context context;
    private JsonBean jsonBean;

    public JsonAdapter(Context context, JsonBean jsonBean)
    {
        this.context=context;
        this.jsonBean=jsonBean;
    }


    @Override
    public int getCount() {
        return jsonBean.getAdDealList().size();
    }

    @Override
    public Object getItem(int position) {
        return jsonBean.getAdDealList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //1. layout - xml 파일 가져오기
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.lay_json, null);

        //2. 데이터 취득
        JsonBean.AdDeal adDeal = jsonBean.getAdDealList().get(position);

        //3. 데이터 화면에 표시
        ImageView imgThumb = (ImageView)convertView.findViewById(R.id.imgThumb);
        TextView txtDealName = (TextView)convertView.findViewById(R.id.txtDealName);
        TextView txtPrice = (TextView)convertView.findViewById(R.id.txtPrice);

        txtDealName.setText(adDeal.getDealName());
        String price = String.format("%, d", new Integer(adDeal.getPrice())); //100단위 콤마 찍기
        txtPrice.setText(price);
        //이미지 비동기표시
        new ImageLoaderTask(imgThumb).execute(adDeal.getThumbnailPath());

        return convertView;
    }

    //이미지 비동기 로딩 TASK
    class ImageLoaderTask extends AsyncTask<String, Void, Bitmap>
    {
        private ImageView dispImageVIew;

        public ImageLoaderTask(ImageView dispImageVIew)
        {
            this.dispImageVIew=dispImageVIew;
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            String imgUrl = params[0];
            Bitmap bmp = null;

            try {
                bmp = BitmapFactory.decodeStream( (InputStream) new URL(imgUrl).getContent());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return bmp;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap)
        {
            if(bitmap!=null)
            {
                dispImageVIew.setImageBitmap(bitmap);
            }
        }
    }
}


