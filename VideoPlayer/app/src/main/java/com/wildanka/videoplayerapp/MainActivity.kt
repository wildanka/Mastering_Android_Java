package com.wildanka.videoplayerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_show_video.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, VideoDetailActivity::class.java)
            startActivity(intent)
        })
    }
}
