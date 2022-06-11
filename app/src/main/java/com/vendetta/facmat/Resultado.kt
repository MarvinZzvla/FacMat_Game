package com.vendetta.facmat

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_resultado.*
import java.util.logging.Level

class Resultado : AppCompatActivity() {
    var maxPuntaje = 0
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

    override fun onStart() {
        super.onStart()
        checkMaxPuntaje()
        maximapuntuacion.text = maxPuntaje.toString()
    }

    fun checkMaxPuntaje(){
        var puntaje = totalpuntuacion.text.toString().toInt()
        cargarPref()
        if(puntaje > maxPuntaje){
            savePref(puntaje)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        Intent(this,Levels::class.java).apply {
            startActivity(this)
        }
    }

    fun cargarPref(){
        getSharedPreferences("prefs", Context.MODE_PRIVATE).apply { maxPuntaje = this.getInt("max",0) }
    }

    fun savePref(maxPun:Int){
        getSharedPreferences("prefs", Context.MODE_PRIVATE).apply {
            this.edit().apply(){
                this.putInt("max",maxPun)
                this.commit()
            }
        }
    }
}