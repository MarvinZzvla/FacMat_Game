package com.vendetta.facmat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_suma_level.*
import java.lang.Integer.parseInt

class SumaLevel : AppCompatActivity() {

   var finalResult = 0
    var finalPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suma_level)
            generateProblem()

        sum_1option.setOnClickListener { var position = 1; makeChoice(position)}
        sum_2option.setOnClickListener {  var position = 2; makeChoice(position)}
        sum_3option.setOnClickListener { var position = 3; makeChoice(position) }
        sum_4option.setOnClickListener { var position = 4;makeChoice(position)}

    }

    fun makeChoice(position: Int){
        if(getResult(position))
        {
            var getScore = parseInt(sumScore.text.toString())
            getScore += 1;
            sumScore.text = getScore.toString()
            generateProblem()
            //HAS GANADO
        }
        else{
            var getScore = parseInt(sumScore.text.toString())
            if(getScore > 0){
            getScore -= 1;
            }
            sumScore.text = getScore.toString()
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
        fnumber = (1..10).random()
        snumber = (1..10).random()
        finalResult = fnumber + snumber
        var problem = "$fnumber + $snumber = ?"
        displayinScreen(problem,finalResult)
    }

    fun displayinScreen(problem: String,result: Int){
        var positionanswer = 0

        sum_1answer.text = (10..20).random().toString()
        sum_2answer.text = (10..20).random().toString()
        sum_3answer.text = (10..20).random().toString()
        sum_4answer.text = (10..20).random().toString()

        positionanswer = (1..4).random()
        println("$positionanswer este el numero")
        when (positionanswer){
            1-> {sum_1answer.text = result.toString()}
            2 -> {sum_2answer.text = result.toString()}
            3 -> {sum_3answer.text = result.toString()}
            4 -> {sum_4answer.text = result.toString()}
        }
        finalPosition = positionanswer;
        sum_exercise.text = problem
    }
}