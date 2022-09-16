package com.vendetta.facmat


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_about.*

class About : AppCompatActivity() {
    var isDev = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        showInfo()
        btn_me.setOnClickListener {
            showInfo()
        }

        btn_ciencia.setOnClickListener { onBackPressed() }

    }

    fun showInfo(){
        if(isDev){
            isDev = false
            textView14.visibility = View.INVISIBLE
            textView15.visibility = View.INVISIBLE
            textView16.visibility = View.INVISIBLE
            textView17.visibility = View.INVISIBLE
        }
        else{
            isDev = true
            textView14.visibility = View.VISIBLE
            textView15.visibility = View.VISIBLE
            textView16.visibility = View.VISIBLE
            textView17.visibility = View.VISIBLE
        }
    }
}