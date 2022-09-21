package com.vendetta.facmat

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.android.synthetic.main.activity_multiply_level.*
import kotlinx.android.synthetic.main.activity_multiply_level.cronometroimg
import kotlinx.android.synthetic.main.activity_multiply_level.temporizador
import java.util.*
import java.util.concurrent.TimeUnit


class MultiplyLevel : AppCompatActivity() {
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
    private var onBtnBack = false
    var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiply_level)

        /*Cambios*/
        actualPosition = intent.getIntExtra("actualPosition",20)
        mySong = MediaPlayer.create(this,R.raw.bgmusic)
        mySong.seekTo(actualPosition)
        mySong.isLooping = true;
        mySong.start()

        generateProblem()

        multiply_1option.setOnClickListener { var position = 1; makeChoice(position)}
        multiply_2option.setOnClickListener {  var position = 2; makeChoice(position)}
        multiply_3option.setOnClickListener { var position = 3; makeChoice(position) }
        multiply_4option.setOnClickListener { var position = 4;makeChoice(position)}
    }

    /*Cambios*/
    override fun onStart() {
        super.onStart()
        loadFullAd()
        onBtnBack = false

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

    fun loadFullAd(){
        var adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this,"ca-app-pub-2467116940009132/4331505671", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                println("Fail Ad")
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                println("Ad was load")
                mInterstitialAd = interstitialAd
                mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                    override fun onAdClicked() {
                        // Called when a click is recorded for an ad.
                        println("Ad was clicked.")
                    }

                    override fun onAdDismissedFullScreenContent() {
                        // Called when ad is dismissed.
                        println("Ad dismissed fullscreen content.")
                        mInterstitialAd = null
                        callAnotherScreen()
                    }

                    override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                        // Called when ad fails to show.
                        println("Ad failed to show fullscreen content.")
                        mInterstitialAd = null
                        callAnotherScreen()
                    }

                    override fun onAdImpression() {
                        // Called when an impression is recorded for an ad.
                        println("Ad recorded an impression.")
                    }

                    override fun onAdShowedFullScreenContent() {
                        // Called when ad is shown.
                        println("Ad sucess FULL")
                        //callAnotherScreen()
                    }
                }
            }
        })


    }

    fun  callAnotherScreen(){
        if(!onBtnBack) {
            Intent(this, Resultado::class.java).apply {
                this.putExtra("rpuntuacion", acertadasPuntuacion)
                this.putExtra("fpuntuacion", erradasPuntuacion)
                this.putExtra("tpuntuacion", totalPuntuacion)
                startActivity(this)
            }
        }else{
            Intent(this,Levels::class.java).apply {
                this.putExtra("actualPosition",mySong.currentPosition)
                startActivity(this)
            }
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
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        }
        else{
            callAnotherScreen()
        }

    /*
        Intent(this, Resultado::class.java).apply {
            this.putExtra("rpuntuacion",acertadasPuntuacion)
            this.putExtra("fpuntuacion", erradasPuntuacion)
            this.putExtra("tpuntuacion",totalPuntuacion)
            startActivity(this)
        }*/
    }

    fun makeChoice(position: Int){
        if(getResult(position))
        {
            var getScore = Integer.parseInt(multiplyScore.text.toString())
            getScore += 1;
            multiplyScore.text = getScore.toString()
            /*Cambios*/
            totalPuntuacion = getScore
            acertadasPuntuacion += 1;
            generateProblem()
            //HAS GANADO
        }
        else{
            var getScore = Integer.parseInt(multiplyScore.text.toString())
            if(getScore > 0){
                getScore -= 1;
            }
            multiplyScore.text = getScore.toString()
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
        fnumber = (1..12).random()
        snumber = (1..12).random()
        finalResult = fnumber * snumber
        var problem = "$fnumber x $snumber = ?"
        displayinScreen(problem,finalResult)
    }

    fun displayinScreen(problem: String,result: Int){
        var positionanswer = 0

        multiply_1answer.text = (10..30).random().toString()
        multiply_2answer.text = (10..30).random().toString()
        multiply_3answer.text = (10..30).random().toString()
        multiply_4answer.text = (10..30).random().toString()

        if(multiply_1answer.text == result.toString() ||
            multiply_2answer.text == result.toString() ||
            multiply_3answer.text == result.toString() ||
            multiply_4answer.text == result.toString()){generateProblem();return}

        positionanswer = (1..4).random()
        println("$positionanswer este el numero")
        when (positionanswer){
            1-> {multiply_1answer.text = result.toString()}
            2 -> {multiply_2answer.text = result.toString()}
            3 -> {multiply_3answer.text = result.toString()}
            4 -> {multiply_4answer.text = result.toString()}
        }
        finalPosition = positionanswer;
        multiply_exercise.text = problem
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
        onBtnBack = true
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        }
    }
}