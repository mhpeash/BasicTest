package com.shurjomukhi.basictest.Q3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.shurjomukhi.basictest.R;

import static com.shurjomukhi.basictest.Helper.AppConstantAndHelper.IsNetworkAvailable;

public class ActVideoPlayer extends AppCompatActivity {
    VideoView videoView;
    ImageView rewind, play, forword;
    boolean isPlaying;
    int stopPosition;
    private IVideoPlayer iVideoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        /**
         *  Initialize related variables
         */
        UIInitialization();
        /**
         * Rewind function
         */
        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rewind();
            }
        });
        /**
         * Play Function
         */
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    Pause();
                } else {
                    Resume();
                }
            }
        });

        /**
         * Forword Function
         */
        forword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Forword();
            }
        });
    }

    private void UIInitialization() {
        getSupportActionBar().hide();
        videoView = findViewById(R.id.videoView);
        rewind = findViewById(R.id.inRewind);
        play = findViewById(R.id.imPlay);
        forword = findViewById(R.id.imForward);
        play.setBackgroundResource(R.drawable.btn_pause);
        iVideoPlayer = new VPlayer();
        if (IsNetworkAvailable(this)) {
            Play();
        }
        else {
            Toast.makeText(this, "Internet required for buffering", Toast.LENGTH_SHORT).show();
        }
    }

    private void Play() {
        iVideoPlayer.Play(videoView);
        isPlaying = true;
    }

    private void Rewind() {

        iVideoPlayer.Rewind(videoView);
    }

    private void Forword() {

        iVideoPlayer.Forword(videoView);
    }

    private void Pause() {
        stopPosition = videoView.getCurrentPosition(); //stopPosition is an int
        videoView.pause();
        play.setBackgroundResource(R.drawable.ic_play);
        isPlaying = false;

    }

    private void Resume() {
        videoView.seekTo(stopPosition);
        videoView.start();
        play.setBackgroundResource(R.drawable.btn_pause);
        isPlaying = true;

    }
}