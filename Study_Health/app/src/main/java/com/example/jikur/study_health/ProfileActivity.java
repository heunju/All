package com.example.jikur.study_health;

import android.Manifest;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.BuildConfig;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.io.IOException;

import static android.os.Build.VERSION_CODES.LOLLIPOP;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton btn_profile;
    private ImageView img_profile;
    private Uri fileUri; //에러 방지


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initView();
    }

    private void initView() {
        btn_profile =  findViewById(R.id.btn_profile);
        img_profile =  findViewById(R.id.img_profile);
        registerForContextMenu(btn_profile); //프로필 변경 메뉴
    }


    public void addProfile(View v) {
        openContextMenu(v);
    }


    //프로필 변경 메뉴
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("프로필 사진 설정"); //타이틀바
        menu.add(0, 1, 100, "갤러리에서 불러오기");
        menu.add(0, 2, 200, "사진 촬영");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 1: //앨범에서 사진 불러오기
                String[] permission = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}; //내부 접근 허용
                PermissionUtil permissionUtil = new PermissionUtil(111, permission); //마시맬로우 이후부터 권한 체크
                permissionUtil.check(this, new PermissionUtil.IPermissionGrant() {
                    //인터페이스 호출
                    @Override
                    public void run() {
                        gallery();
                    }

                    @Override
                    public void fail() {

                    }
                });
                return true;

            case 2: //새로운 사진 찍기
                String[] perm = new String[]{Manifest.permission.CAMERA};
                PermissionUtil permUtil = new PermissionUtil(222, perm);
                permUtil.check(this, new PermissionUtil.IPermissionGrant() {
                    @Override
                    public void run() {
                        camera();
                    }

                    @Override
                    public void fail() {

                    }
                });
                return true;
        }
        return super.onContextItemSelected(item);
    }


    //갤러리 함수 선언
    private void gallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 111);
    }


    //사진 촬영 + 앨범 저장 함수 선언
    private void camera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //사진 촬영, 권한 필요
        if (Build.VERSION.SDK_INT >= LOLLIPOP) //롤리팝 이후에서는 설정 필요
        {
            //try-catch
            try {
                File cameraFile = createCamera(); //파일 경로를 생성
                refreshMedia(cameraFile); //새로고침 코드
                fileUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", cameraFile); //사진 가져오기
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intent, 222);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //새로고침 함수 선언
    private void refreshMedia(File file) {
        MediaScannerConnection.scanFile(this, new String[]{file.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
            @Override
            public void onScanCompleted(String s, Uri uri) {
                //파일 가져오는게 아니라 새로고침만 하기 때문에 선언 안해도됨
            }
        });
    }

    //사진 촬영 하고 앨범에 저장하는 함수 선언
    private File createCamera() throws IOException //예외처리
    {
        String fileName = "Temp" + System.currentTimeMillis(); //사진 찍은 시간
        File dir = new File(Environment.getExternalStorageDirectory() + File.separator + "Health" + File.separator); //"Health"라는 앨범 디렉토리 생성

        //앨범이 생성됬는지 안됬는지 확인
        if (!dir.exists()) {
            dir.mkdir(); //없으면 디렉토리 생성
        }
        File imageFile = File.createTempFile(fileName, ".jpg", dir);
        return imageFile;
    }


    //onActivityResult: 갤러리나 사진을 찍고 , 결과 값(이미지) 가져와서 넣기
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 111:
                if (resultCode == RESULT_OK) {
                    Uri imageUri = null;
                    if (data != null) {
                        imageUri = data.getData();
                        Glide.with(this).load(imageUri).apply(new RequestOptions().circleCrop()).into(img_profile); //이미지 불러오기 //build.gradle에 추가
                    }
                    break;
                }
            case 222:
                if (resultCode == RESULT_OK) {
                    Uri imageUri = null;
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                        imageUri = data.getData();
                    } else {
                        imageUri = fileUri;
                    }
                    Glide.with(this).load(imageUri).apply(new RequestOptions().circleCrop()).into(img_profile);
                }
                break;
        }
    }
}
