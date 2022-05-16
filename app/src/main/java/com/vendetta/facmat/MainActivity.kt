package com.vendetta.facmat

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        home_playbtn.setOnClickListener {
            Intent(this, Levels::class.java).apply {
                startActivity(this)
            }
        }
    }
}