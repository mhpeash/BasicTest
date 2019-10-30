package com.shurjomukhi.basictest.Q3;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.shurjomukhi.basictest.R;

import java.util.concurrent.ScheduledExecutorService;

public class VideoPlayer extends AppCompatActivity {


    VideoView videoView;
    ImageView rewind, play, forword;
    boolean isPlaying;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        getSupportActionBar().hide();

        init();
        play();


        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rewind();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(isPlaying){
                        videoView.pause();
                    }
                    else {
                        videoView.resume();
                    }
                }
        });
        forword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Forword();
            }
        });

    }
    private void init(){
        videoView = findViewById(R.id.videoView);
        rewind = findViewById(R.id.inRewind);
        play = findViewById(R.id.imPlay);
        forword = findViewById(R.id.imForward);
    }
    private void play(){
        videoView.setVideoURI(Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        //videoView.requestFocus();
        videoView.start();
        isPlaying=true;
    }
    private void Rewind(){
        videoView.seekTo(videoView.getCurrentPosition() - 500);
    }
    private void Forword(){
        videoView.seekTo(videoView.getCurrentPosition() + 500);

    }

}
