package com.wildanka.simpleexoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
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

        /**
         * we can also do a concatenating media source. So for example,
         * when user play firstMusic then swipe to the next playlist, he will have some buffer time to load the secondMedia (music)
         * we don't want users to wait, so this is what we do.
         *
         */
//        MediaSource firstSource = new ExtractorMediaSource(videoUri, ...);
//        MediaSource secondSource = ... ; //basically can be from different Source you can play music after video or else.

        //Plays the first video, then the second video
//        ConcatenatingMediaSource concatenatingMediaSource =
//        new ConcatenatingMediaSource(
//          firstSource,
//          secondSource,
//          new MergingMediaSource(videoSource,subtitleSource)); // even the concated mediaSource can be MergingMediaSource

        //to see some Use Case watch this (I/O 17) : https://www.youtube.com/watch?v=jAZn-J1I8Eg&list=PLWz5rJ2EKKc-odHd6XEaf7ykfsosYyCKp&index=12&t=738s
        //to see how to build things with ExoPlayer (I/O 18) : https://www.youtube.com/watch?v=svdq1BWl4r8

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
