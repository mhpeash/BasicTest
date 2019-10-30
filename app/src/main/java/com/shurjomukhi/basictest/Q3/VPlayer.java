package com.shurjomukhi.basictest.Q3;

import android.net.Uri;

import android.widget.VideoView;

import com.shurjomukhi.basictest.R;

public class VPlayer implements IVideoPlayer {

    @Override
    public void Play(VideoView videoView) {

        videoView.setVideoURI(Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        videoView.requestFocus();
        videoView.start();
    }

    @Override
    public void Rewind(VideoView videoView) {
        videoView.seekTo(videoView.getCurrentPosition() - 5000);
    }

    @Override
    public void Forword(VideoView videoView) {
        videoView.seekTo(videoView.getCurrentPosition() + 5000);
    }


}
