package com.vendetta.facmat

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import kotlinx.android.synthetic.main.activity_divide_level.*
import kotlinx.android.synthetic.main.activity_divide_level.temporizador
import java.util.*
import java.util.concurrent.TimeUnit

class DivideLevel : AppCompatActivity() {
    var finalResult = 0
    var finalPosition = 0
    /*Cambios */
    var acertadasPuntuacion = 0
    var erradasPuntuacion = 0;
    var totalPuntuacion = 0;
    lateinit var mySong: MediaPlayer
    lateinit var tempo: CountDownTimer
    var actualPosition = 0
    var iscronometro = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_divide_level)

        /*Cambios*/
        actualPosition = intent.getIntExtra("actualPosition",20)
        mySong = MediaPlayer.create(this,R.raw.bgmusic)
        mySong.seekTo(actualPosition)
        mySong.isLooping = true;
        mySong.start()

        generateProblem()
       

        divide_1option.setOnClickListener { var position = 1; makeChoice(position)}
        divide_2option.setOnClickListener {  var position = 2; makeChoice(position)}
        divide_3option.setOnClickListener { var position = 3; makeChoice(position) }
        divide_4option.setOnClickListener { var position = 4;makeChoice(position)}
    }

    /*Cambios*/
    override fun onStart() {
        super.onStart()

        iscronometro = intent.getBooleanExtra("iscronometro",false)
        if(iscronometro){
            temporizador.visibility = View.VISIBLE;
            cronometroimg.visibility = View.VISIBLE;


            startTemporizador()
        } else{
            temporizador.visibility = View.INVISIBLE
            cronometroimg.visibility = View.INVISIBLE
        }
    }

    fun startTemporizador(){
        var duration:Long = TimeUnit.MINUTES.toMillis(1)
        tempo = object : CountDownTimer(duration,1000){
            override fun onTick(p0: Long) {
                temporizador.text = String.format(
                    Locale.ENGLISH,"%02d : %02d", TimeUnit.MILLISECONDS.toMinutes(p0),
                    (TimeUnit.MILLISECONDS.toSeconds(p0) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(p0))))

            }

            override fun onFinish() {
                gotoResultado()
            }

        }
        tempo.start()

    }

    fun gotoResultado(){
        Intent(this, Resultado::class.java).apply {
            this.putExtra("rpuntuacion",acertadasPuntuacion)
            this.putExtra("fpuntuacion", erradasPuntuacion)
            this.putExtra("tpuntuacion",totalPuntuacion)
            startActivity(this)
        }
    } /*Cambios*/

    fun makeChoice(position: Int){
        if(getResult(position))
        {
            var getScore = Integer.parseInt(divideScore.text.toString())
            getScore += 1;
            divideScore.text = getScore.toString()
            /*Cambios*/
            totalPuntuacion = getScore
            acertadasPuntuacion += 1;
            generateProblem()
            //HAS GANADO
        }
        else{
            var getScore = Integer.parseInt(divideScore.text.toString())
            if(getScore > 0){
                getScore -= 1;
            }
            divideScore.text = getScore.toString()
            /*Cambios*/
            totalPuntuacion = getScore
            erradasPuntuacion += 1;
            generateProblem()
            //HAS PERDIDO
        }
    }
    fun getResult(choice: Int): Boolean {
        return choice == finalPosition
    }

    fun generateProblem(){
        var fnumber = 0;
        var snumber = 0;
        fnumber = (10..30).random()
        snumber = (2..9).random()
        if(fnumber < snumber){generateProblem(); return }
        var restante = fnumber % snumber
        if (restante != 0){ generateProblem();return}
        finalResult = (fnumber / snumber)
        var problem = "$fnumber ÷ $snumber = ?"
        displayinScreen(problem,finalResult)
    }


    fun displayinScreen(problem: String, result: Int){
        var positionanswer = 0

        divide_1answer.text = (1..30).random().toString()
        divide_2answer.text = (1..30).random().toString()
        divide_3answer.text = (1..30).random().toString()
        divide_4answer.text = (1..30).random().toString()

        //CHECK numeros repetidos
        if(divide_1answer.text == result.toString() ||
            divide_2answer.text == result.toString() ||
            divide_3answer.text == result.toString() ||
            divide_4answer.text == result.toString()){generateProblem();return}


        positionanswer = (1..4).random()
        println("$positionanswer este el numero")
        when (positionanswer){
            1-> {divide_1answer.text = result.toString()}
            2 -> {divide_2answer.text = result.toString()}
            3 -> {divide_3answer.text = result.toString()}
            4 -> {divide_4answer.text = result.toString()}
        }
        finalPosition = positionanswer;
        divide_exercise.text = problem
    }

    /*Cambios*/
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
        if(iscronometro){
            tempo.cancel()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mySong.release()
        if(iscronometro) {
            tempo.cancel()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Intent(this,Levels::class.java).apply {
            this.putExtra("actualPosition",mySong.currentPosition)
            startActivity(this)
        }
    }
}