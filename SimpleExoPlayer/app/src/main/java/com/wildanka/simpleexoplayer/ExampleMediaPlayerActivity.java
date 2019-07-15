package com.wildanka.simpleexoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;

public class ExampleMediaPlayerActivity extends AppCompatActivity {
    private SimpleExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_media_player);

        player = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());
        PlayerView playerView = findViewById(R.id.player_view);
        playerView.setPlayer(player);

        //tell exo player what we want to play, in ExoPlayer everything you can play is MediaSource
        Uri videoUri = Uri.parse("http://sadsad/asdasd");
        //creating an ExtractorMediaSource (which support mp4,mp3 and matroska, and so on
        MediaSource mediaSource = new ExtractorMediaSource(videoUri);
//        MediaSource mediaSource = new DashMediaSource(videoUri); // (specification for streaming which allows for adaptation between qualities to handle varying network conditions)
//        MediaSource mediaSource = new HlsMediaSource(videoUri); // (supporting http live streaming for streaming)
//        MediaSource mediaSource = new SsMediaSource(videoUri); // (supporting smoothstreaming for streaming)

        //actually we can merge two mediaSource to be played together like below
//         MediaSource videoSource = new ExtractorMediaSource(videoUri, ...);
//         MediaSource subtitleSource = new SingleSampleMediaSource(subtitleUri, ...);

        //plays the video and subtitle in sync
//        MediaSource mergedSource = new MergingMediaSource(videoSource,subtitleSource);
        player.prepare(mediaSource);
        player.setPlayWhenReady(true); //tell the player as the buffering is complete, then the playback should start


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Don't forget to release the Player
        player.release();
    }
}
