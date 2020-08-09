package com.jacksean.jeackplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;

public class ExoPlayerActivity extends AppCompatActivity {
    private SimpleExoPlayer exoPlayer;
    private AspectRatioFrameLayout videoFrame;//用来控制视频的宽高比
    private SurfaceView surfaceView; //播放区
    private ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_exo_player);
        surfaceView = (SurfaceView) findViewById(R.id.surface_view);
        videoFrame = (AspectRatioFrameLayout) findViewById(R.id.video_frame);
        mProgressBar=findViewById(R.id.mProgressBar);
        exoPlayer=new SimpleExoPlayer.Builder(getApplicationContext()).build();
        exoPlayer.prepare(mHlsMediaSource.getMediaSource(this));
        exoPlayer.addListener(new ExoPlayerActivity.ExoPlayerrListener());
        // videoFrame.setAspectRatio(1.9f);
        exoPlayer.setVideoSurface(surfaceView.getHolder().getSurface());
        exoPlayer.setPlayWhenReady(true);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
    class ExoPlayerrListener implements Player.EventListener {
        @Override
        public void onTimelineChanged(Timeline timeline, int reason) {

        }
        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            switch (playbackState){
                case 2://缓冲
                    mProgressBar.setVisibility(View.VISIBLE);
                    break;
                case 3://就绪
                    mProgressBar.setVisibility(View.GONE);
                    break;
                case 4://完成
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (exoPlayer!=null){
            exoPlayer.release();
            exoPlayer=null;
        }
    }
}