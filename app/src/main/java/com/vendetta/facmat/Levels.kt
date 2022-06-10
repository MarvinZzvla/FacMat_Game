package com.vendetta.facmat

import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_levels.*



class Levels : AppCompatActivity() {

    lateinit var mySong:MediaPlayer
    var actualPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)

            actualPosition = intent.getIntExtra("actualPosition",20)
        mySong = MediaPlayer.create(this,R.raw.bgmusic)
        mySong.seekTo(actualPosition)
        mySong.isLooping = true;
        mySong.start()

        level_logoapp.setOnClickListener {
            Intent(this, MainActivity::class.java).apply { this.putExtra("actualPosition",mySong.currentPosition); startActivity(this) }
        }

        level_suma.setOnClickListener {
            Intent(this, SumaLevel::class.java).apply { this.putExtra("actualPosition",mySong.currentPosition);startActivity(this) }
        }

        level_resta.setOnClickListener {
            Intent(this, RestaLevel::class.java).apply { this.putExtra("actualPosition",mySong.currentPosition);startActivity(this) }
        }

        level_multiply.setOnClickListener {
            Intent(this, MultiplyLevel::class.java).apply { this.putExtra("actualPosition",mySong.currentPosition); startActivity(this) }
        }

        level_divide.setOnClickListener {
            Intent(this, DivideLevel::class.java).apply { this.putExtra("actualPosition",mySong.currentPosition); startActivity(this) }
        }
    }

    override fun onResume() {
        super.onResume()
        mySong.release()
        mySong = MediaPlayer.create(this,R.raw.bgmusic)
        mySong.seekTo(actualPosition)
        mySong.isLooping = true;
        mySong.setVolume(0.2f,0.2f)
        mySong.start()
    }

    override fun onPause() {
        super.onPause()
        mySong.pause()
        mySong.release()
    }

    override fun onDestroy() {
        super.onDestroy()
        mySong.release()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Intent(this,MainActivity::class.java).apply {
            this.putExtra("actualPosition",mySong.currentPosition)
            startActivity(this)
        }
    }

}