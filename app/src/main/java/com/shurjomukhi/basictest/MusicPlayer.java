package com.shurjomukhi.basictest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MusicPlayer extends AppCompatActivity  implements MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener {

    private ImageButton btnPlay;
    private ImageButton btnForward;
    private ImageButton btnBackward;


    private SeekBar songProgressBar;
    private TextView songTitleLabel;
    private TextView songCurrentDurationLabel;
    private TextView songTotalDurationLabel;
    // Media Player
    private MediaPlayer mp;
    // Handler to update UI timer, progress bar etc,.
    private Handler mHandler = new Handler();;


    private int seekForwardTime = 5000; // 5000 milliseconds
    private int seekBackwardTime = 5000; // 5000 milliseconds
    private int currentSongIndex = 0;
    private boolean isShuffle = false;
    private boolean isRepeat = false;
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);


        // All player buttons
        btnPlay =  findViewById(R.id.btnPlay);
        btnForward = findViewById(R.id.btnForward);
        btnBackward =  findViewById(R.id.btnBackward);


        songProgressBar = findViewById(R.id.songProgressBar);
        songTitleLabel =  findViewById(R.id.songTitle);
        songCurrentDurationLabel = findViewById(R.id.songCurrentDurationLabel);
        songTotalDurationLabel = findViewById(R.id.songTotalDurationLabel);


        // Mediaplayer
        mp = new MediaPlayer();
        // Listeners
        songProgressBar.setOnSeekBarChangeListener(this); // Important
        mp.setOnCompletionListener(this); // Important


        // By default play first song
        try {
            playSong(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Play button click event plays a song and changes button to pause
         * image pauses a song and changes button to play image
         * */
        btnPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // check for already playing
                if (mp.isPlaying()) {
                    if (mp != null) {
                        mp.pause();
                        // Changing button image to play button
                        btnPlay.setImageResource(R.drawable.btn_play);
                    }
                } else {
                    // Resume song
                    if (mp != null) {
                        mp.start();
                        // Changing button image to pause button
                        btnPlay.setImageResource(R.drawable.btn_pause);
                    }
                }

            }
        });

        btnForward.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                // get current song position
                int currentPosition = mp.getCurrentPosition();
                // check if seekForward time is lesser than song duration
                if (currentPosition + seekForwardTime <= mp.getDuration()) {
                    // forward song
                    mp.seekTo(currentPosition + seekForwardTime);
                } else {
                    // forward to end position
                    mp.seekTo(mp.getDuration());
                }
                return false;
            }
        });

        /**
         * Forward button click event Forwards song specified seconds
         * */
        btnForward.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // get current song position
                // check if next song is there or not
                if (currentSongIndex < (songsList.size() - 1)) {
                    try {
                        playSong(currentSongIndex + 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    currentSongIndex = currentSongIndex + 1;
                } else {
                    // play first song
                    try {
                        playSong(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    currentSongIndex = 0;
                }
            }
        });

        btnBackward.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                int currentPosition = mp.getCurrentPosition();
                // check if seekBackward time is greater than 0 sec
                if (currentPosition - seekBackwardTime >= 0) {
                    // forward song
                    mp.seekTo(currentPosition - seekBackwardTime);
                } else {
                    // backward to starting position
                    mp.seekTo(0);
                }
                return false;
            }
        });

        /**
         * Backward button click event Backward song to specified seconds
         * */
        btnBackward.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (currentSongIndex > 0) {
                    try {
                        playSong(currentSongIndex - 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    currentSongIndex = currentSongIndex - 1;
                } else {
                    // play last song
                    try {
                        playSong(songsList.size() - 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    currentSongIndex = songsList.size() - 1;
                }

            }
        });
    }

    /**
     * Receiving song index from playlist view and play the song
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100) {
            currentSongIndex = data.getExtras().getInt("songIndex");
            // play selected song
            try {
                playSong(currentSongIndex);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Function to play a song
     *
     * @param songIndex
     *            - index of song
     * */
    public void playSong(int songIndex) throws IOException {
        // Play song
        try {
            mp.reset();
            mp.setDataSource(songsList.get(songIndex).get("songPath"));
            mp.prepare();
            mp.start();
            // Displaying Song title
            String songTitle = songsList.get(songIndex).get("songTitle");
            songTitleLabel.setText(songTitle);

            // Changing Button Image to pause image
            btnPlay.setImageResource(R.drawable.btn_pause);

            // set Progress bar values
            songProgressBar.setProgress(0);
            songProgressBar.setMax(100);

            // Updating progress bar
            updateProgressBar();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update timer on seekbar
     * */
    public void updateProgressBar() {
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mHandler.removeCallbacks(mUpdateTimeTask);
            mp.release();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Background Runnable thread
     * */
    private Runnable mUpdateTimeTask = new Runnable() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        public void run() {
            long totalDuration = mp.getDuration();
            long currentDuration = mp.getCurrentPosition();

            // Displaying Total Duration time
            songTotalDurationLabel.setText(""+ milliSecondsToTimer(totalDuration));
            // Displaying time completed playing
            songCurrentDurationLabel.setText("" + milliSecondsToTimer(currentDuration));

            // Updating progress bar
            int progress = (int) (getProgressPercentage(currentDuration,
                    totalDuration));
            // System.out.println("Progress : "+progress);
            songProgressBar.setProgress(progress);

            // Running this thread after 100 milliseconds
            mHandler.postDelayed(this, 100);
        }
    };

    private long getProgressPercentage(long currentDuration, long totalDuration) {
        return (currentDuration*100)/totalDuration;

    }

    /**
     *
     * */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromTouch) {

    }

    /**
     * When user starts moving the progress handler
     * */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // remove message Handler from updating progress bar
        mHandler.removeCallbacks(mUpdateTimeTask);
    }

    /**
     * When user stops moving the progress hanlder
     * */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(mUpdateTimeTask);
        int totalDuration = mp.getDuration();
        int currentPosition = totalDuration-seekBar.getProgress();

        // forward or backward to certain seconds
        mp.seekTo(currentPosition);

        // update timer progress again
        updateProgressBar();
    }
    /**
     * On Song Playing completed if repeat is ON play same song again if shuffle
     * is ON play random song
     * */
    @Override
    public void onCompletion(MediaPlayer arg0) {

        // check for repeat is ON or OFF
        if (isRepeat) {
            // repeat is on play same song again
            try {
                playSong(currentSongIndex);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (isShuffle) {
            // shuffle is on - play a random song
            Random rand = new Random();
            currentSongIndex = rand.nextInt((songsList.size() - 1) - 0 + 1) + 0;
            try {
                playSong(currentSongIndex);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // no repeat or shuffle ON - play next song
            if (currentSongIndex < (songsList.size() - 1)) {
                try {
                    playSong(currentSongIndex + 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                currentSongIndex = currentSongIndex + 1;
            } else {
                // play first song
                try {
                    playSong(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                currentSongIndex = 0;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.release();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static String milliSecondsToTimer(final long duration) {
        int dur, min, sec, mil;
        dur = Math.toIntExact(duration);
        min = dur / 60000;
        dur -= min * 60000;
        sec = dur / 1000;
        dur -= sec * 1000;
        mil = dur;
        return min + ":" + sec + "." + mil;
    }
}
