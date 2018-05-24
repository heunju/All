package com.example.jikur.gallaryimgupapp;

import android.Manifest;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMG_REQUEST = 108;
    private ProgressBar mProgressBar;
    private ImageView mUpImgView;
    private String filePath;

    //굳이 버튼은 선언 할 필요없음

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);
        mUpImgView = (ImageView)findViewById(R.id.imageView);
        findViewById(R.id.button_choose).setOnClickListener(mBtnImgChooseClick);
        findViewById(R.id.button_upload).setOnClickListener(mBtnImgUploadClick);


        //퍼미션 권한 요청
        ActivityCompat.requestPermissions(this, new String[]
                {
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, 0);
    }


    //갤러리 이미지 선택 클릭 이벤트
    private View.OnClickListener mBtnImgChooseClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, PICK_IMG_REQUEST);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            if(requestCode==PICK_IMG_REQUEST)
            {
                Uri picUri = data.getData();
                filePath = getPath(picUri);
                Log.d("picUri", picUri.toString());
                Log.d("filePath", filePath);

                mUpImgView.setImageURI(picUri);
            }
        }
    }

    //선택한 사진의 전체 경로를 알 수 있게 해주는 메소드
    private String getPath(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(getApplicationContext(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }


    //갤러리에서 선택된 이미지를 업로드 클릭 이벤트
    private View.OnClickListener mBtnImgUploadClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            new ImgUploadTask().execute();
        }
    };


    class ImgUploadTask extends AsyncTask<Void, Void, String>
    {
        public static final String URL_IMG_UPLOAD = "http://172.16.8.195:8585/rest/imgUp.do";

        @Override
        protected void onPreExecute()
        {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {

            try{
                String charset="UTP-8";
                File uploadFile1 = new File(filePath);
                MultipartUtility multipart = new MultipartUtility(URL_IMG_UPLOAD, charset);
                multipart.addFilePart("imageFile", uploadFile1);

                //서버로 전송
                List<String> response = multipart.finish(); //응답이 JSON 으로 날라옴
                StringBuffer sb = new StringBuffer();
                for(String line : response) {
                    sb.append(line);
                }
                return sb.toString(); //onPostExecute 로 전달.

            }catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            mProgressBar.setVisibility(View.INVISIBLE);
            if (s != null) {
                Log.i("TEST", s);

            }
        }
    }
}
