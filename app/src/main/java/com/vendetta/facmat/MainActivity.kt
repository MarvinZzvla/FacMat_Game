package com.vendetta.facmat

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var mySong:MediaPlayer
    var actualPosition = 0;
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
            infoTeam.setOnClickListener {
                println("INFO")
                Intent(this, About::class.java).apply {
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