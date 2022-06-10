package com.vendetta.facmat

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_resultado.*
import java.util.logging.Level

class Resultado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        totalpuntuacion.text = intent.getIntExtra("tpuntuacion",0).toString()
        acertadapuntuacion.text = intent.getIntExtra("rpuntuacion",0).toString()
        erroneaspuntuacion.text = intent.getIntExtra("fpuntuacion",0).toString()

        happyface.setOnClickListener {
            onBackPressed();
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Intent(this,Levels::class.java).apply {
            startActivity(this)
        }
    }
}