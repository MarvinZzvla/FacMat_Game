package com.vendetta.facmat

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.ViewGroup
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var mySong:MediaPlayer
    var actualPosition = 0;
    lateinit var mAdView : AdView
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            MobileAds.initialize(this) {}

            mAdView = findViewById(R.id.adView)
            val adRequest = AdRequest.Builder().build()
            mAdView.loadAd(adRequest)



            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            val height = displayMetrics.heightPixels
            val width = displayMetrics.widthPixels


        home_playbtn.setOnClickListener {
            Intent(this, Levels::class.java).apply {
                this.putExtra("actualPosition",mySong.currentPosition)
                startActivity(this)

            }
        }

            mySong = MediaPlayer.create(this,R.raw.bgmusic)
        mySong.isLooping = true;
        mySong.start()


    }

    override fun onResume() {
        super.onResume()
        actualPosition = intent.getIntExtra("actualPosition",20)
        mySong.release()
        mySong = MediaPlayer.create(this,R.raw.bgmusic)
        mySong.seekTo(actualPosition)
        mySong.isLooping = true;
        mySong.setVolume(0.2f,0.2f)
        mySong.start()


    }

    override fun onPause() {
        super.onPause()
        actualPosition = mySong.currentPosition
        mySong.pause()
        mySong.release()
    }

    override fun onDestroy() {
        super.onDestroy()
        mySong.release()
    }


}