package com.example.ijkapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button playMusic;
    private IjkMediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
        setContentView(R.layout.activity_main);
        playMusic = (Button)findViewById(R.id.play);
        playMusic.setOnClickListener(listener);
    }

    private void playmusic(){
        Log.i(TAG,"playmusic");
        player = new IjkMediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setScreenOnWhilePlaying(true);
        player.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                Log.i(TAG,"playmusic onPrepared");
                player.start();
            }
        });
        try {
            player.setDataSource("/storage/emulated/0/test.mp3");
            player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.play:
                    playmusic();
                    break;
            }
        }
    };
}
