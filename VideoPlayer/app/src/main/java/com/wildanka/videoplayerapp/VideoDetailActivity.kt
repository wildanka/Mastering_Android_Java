package com.wildanka.videoplayerapp

import android.os.Bundle
import android.util.Log
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_video_detail.*

class VideoDetailActivity : YouTubeBaseActivity(), OnInitializedListener{

    private val TAG = "VideoDetailActivity"
    private lateinit var playerView: YouTubePlayerView
    private lateinit var ytPlayer: OnInitializedListener

    val VIDEO_ID = "mtzz8d1s-gw"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_detail)
        playerView = yt_player
        playerView.initialize(YoutubeConfig.API_KEY, this)
    }

    //region onInitialization
    override fun onInitializationSuccess(youTubePlayerProvider: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer?, b: Boolean) {
        youtubePlayer?.loadVideo(VIDEO_ID)
        youtubePlayer?.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
    }

    override fun onInitializationFailure(youTubePlayerProvider: YouTubePlayer.Provider?, youTubeInitializationResult: YouTubeInitializationResult?) {
        Log.e(TAG, "Failed to initialize")
    }
    //endregion onInitialization


}
