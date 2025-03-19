package com.example.lab4;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private VideoView videoView;
    private Button playAudio, pauseAudio, stopAudio;
    private Button playVideo, pauseVideo, stopVideo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = new MediaPlayer();
        videoView = findViewById(R.id.videoView);
        playAudio = findViewById(R.id.play_audio);
        pauseAudio = findViewById(R.id.pause_audio);
        stopAudio = findViewById(R.id.stop_audio);
        playVideo = findViewById(R.id.play_video);
        pauseVideo = findViewById(R.id.pause_video);
        stopVideo = findViewById(R.id.stop_video);

        int audioResId = R.raw.audio_example;
        mediaPlayer = MediaPlayer.create(this, audioResId);

        playAudio.setOnClickListener(v -> mediaPlayer.start());
        pauseAudio.setOnClickListener(v -> mediaPlayer.pause());
        stopAudio.setOnClickListener(v -> {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this, audioResId);
        });

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_my;
        videoView.setVideoURI(Uri.parse(videoPath));
        videoView.requestFocus();

        playVideo.setOnClickListener(v -> videoView.start());
        pauseVideo.setOnClickListener(v -> videoView.pause());
        stopVideo.setOnClickListener(v -> {
            videoView.stopPlayback();
            videoView.setVideoURI(Uri.parse(videoPath));
            videoView.seekTo(0);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
