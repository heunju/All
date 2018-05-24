package com.example.jikur.mediaapp;

import android.app.ProgressDialog;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private static final String AUDIO_URL = "http://sites.google.com/site/ubiaccessmobile/sample_audio.amr";

    private MediaPlayer mMediaPlayer;
    private int mPlayBackPos=0; //재생 위치를 저장하는 변수
    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //버튼인식
        Button btnPlay = (Button) findViewById(R.id.btnPlay);
        Button btnStop = (Button) findViewById(R.id.btnStop);
        Button btnReplay = (Button) findViewById(R.id.btnReplay);
        Button btnVideo = (Button) findViewById(R.id.btnVideo);
        mVideoView = (VideoView) findViewById(R.id.videoView);

        //버튼클릭
        btnPlay.setOnClickListener(btnClick);
        btnStop.setOnClickListener(btnClick);
        btnReplay.setOnClickListener(btnClick);
        btnVideo.setOnClickListener(btnClick);
    }

    //미디어 재생 메소드
    private void playAudio(String url)
    {
        killMediaPlayer(); //기존에 재생하고있는 미디어를 먼저 해제시킴
        try{
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(url);
            mMediaPlayer.prepare(); //미디어 재생 준비작업 필요
            mMediaPlayer.start();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //오디오 재생 메소드
    private void playAssetAudio()
    {
        killMediaPlayer();
        try{
            AssetFileDescriptor afd = getAssets().openFd("audio/mp.mp3"); //mp3파일 이름
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    //미디어 재생 해제 메소드
    private void killMediaPlayer()
    {
        if(mMediaPlayer!=null)
        {
            try{
                mMediaPlayer.release();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    //버튼구현 메소드
    private View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //버튼을 누르면 오디오 재생
            switch (v.getId())
            {
                case R.id.btnPlay:
                    playAssetAudio();
                    break;
                case R.id.btnStop:
                    //PLAYING
                    if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                        //재생되던 마지막 위치 저장
                        mPlayBackPos = mMediaPlayer.getCurrentPosition();
                        mMediaPlayer.pause();
                        Toast.makeText(MainActivity.this, "재생 중지됨", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btnReplay:
                    //NOT PLAYING
                    if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
                        mMediaPlayer.start();
                        mMediaPlayer.seekTo(mPlayBackPos); //시작하고 마지막 정지된 곳으로 이동
                    }
                    break;
                case R.id.btnVideo:
                    String vUrl = "http://sites.google.com/site/ubiaccessmobile/sample_video.mp4";
                    MediaController mc = new MediaController(MainActivity.this);
                    mVideoView.setMediaController(mc);
                    mVideoView.setVideoURI(Uri.parse(vUrl));
                    mVideoView.requestFocus();

                    final ProgressDialog pd = new ProgressDialog(MainActivity.this);
                    pd.setMessage("비디오 재생 준비중");
                    pd.setCancelable(false);
                    pd.show();

                    mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            pd.dismiss();
                            //준비가 끝나면 호출
                            mp.start();
                        }
                    });

                    mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            Toast.makeText(MainActivity.this, "동영상 재생 준비중...", Toast.LENGTH_SHORT).show();
                        }
                    });
            }
        }
    };
}
