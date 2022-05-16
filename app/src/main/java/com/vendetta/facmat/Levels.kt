package com.vendetta.facmat

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_levels.*

class Levels : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)

        level_logoapp.setOnClickListener {
            Intent(this, MainActivity::class.java).apply { startActivity(this) }
        }

        level_suma.setOnClickListener {
            Intent(this, SumaLevel::class.java).apply { startActivity(this) }
        }

        level_resta.setOnClickListener {
            Intent(this, RestaLevel::class.java).apply { startActivity(this) }
        }

        level_multiply.setOnClickListener {
            Intent(this, MultiplyLevel::class.java).apply { startActivity(this) }
        }

        level_divide.setOnClickListener {
            Intent(this, DivideLevel::class.java).apply { startActivity(this) }
        }
    }
}