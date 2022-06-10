package com.vendetta.facmat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_resta_level.*

class RestaLevel : AppCompatActivity() {
    var finalResult = 0
    var finalPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resta_level)

        generateProblem()

        resta_1option.setOnClickListener { var position = 1; makeChoice(position)}
        resta_2option.setOnClickListener {  var position = 2; makeChoice(position)}
        resta_3option.setOnClickListener { var position = 3; makeChoice(position) }
        resta_4option.setOnClickListener { var position = 4;makeChoice(position)}
    }

    fun makeChoice(position: Int){
        if(getResult(position))
        {
            var getScore = Integer.parseInt(restaScore.text.toString())
            getScore += 1;
            restaScore.text = getScore.toString()
            generateProblem()
            //HAS GANADO
        }
        else{
            var getScore = Integer.parseInt(restaScore.text.toString())
            if(getScore > 0){
                getScore -= 1;
            }
            restaScore.text = getScore.toString()
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
        fnumber = (1..100).random()
        snumber = (1..100).random()
        if(fnumber < snumber){generateProblem(); return }
        finalResult = fnumber - snumber
        var problem = "$fnumber - $snumber = ?"
        displayinScreen(problem,finalResult)
    }

    fun displayinScreen(problem: String,result: Int){
        var positionanswer = 0

        resta_1answer.text = (1..100).random().toString()
        resta_2answer.text = (1..100).random().toString()
        resta_3answer.text = (1..100).random().toString()
        resta_4answer.text = (1..100).random().toString()

        if(resta_1answer.text == result.toString() ||
            resta_2answer.text == result.toString() ||
            resta_3answer.text == result.toString() ||
            resta_4answer.text == result.toString()){generateProblem();return}

        positionanswer = (1..4).random()
        println("$positionanswer este el numero")
        when (positionanswer){
            1-> {resta_1answer.text = result.toString()}
            2 -> {resta_2answer.text = result.toString()}
            3 -> {resta_3answer.text = result.toString()}
            4 -> {resta_4answer.text = result.toString()}
        }
        finalPosition = positionanswer;
        resta_exercise.text = problem
    }
}