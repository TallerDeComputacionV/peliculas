package com.tcv.peliculas.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerInitListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;
import com.tcv.peliculas.R;

public class VerPeliculaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pelicula);

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);

        youTubePlayerView.initialize(new YouTubePlayerInitListener() {
            @Override
            public void onInitSuccess(final YouTubePlayer initializedYouTubePlayer) {
                initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady() {
                        String videoId = "6JYIGclVQdw";
                        initializedYouTubePlayer.loadVideo(videoId, 0);
                    }
                });
            }
        }, true);
    }
}
