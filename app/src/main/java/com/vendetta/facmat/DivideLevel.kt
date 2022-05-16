package com.vendetta.facmat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_divide_level.*

class DivideLevel : AppCompatActivity() {
    var finalResult = 0
    var finalPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_divide_level)

        generateProblem()

        divide_1option.setOnClickListener { var position = 1; makeChoice(position)}
        divide_2option.setOnClickListener {  var position = 2; makeChoice(position)}
        divide_3option.setOnClickListener { var position = 3; makeChoice(position) }
        divide_4option.setOnClickListener { var position = 4;makeChoice(position)}
    }

    fun makeChoice(position: Int){
        if(getResult(position))
        {
            var getScore = Integer.parseInt(divideScore.text.toString())
            getScore += 1;
            divideScore.text = getScore.toString()
            generateProblem()
            //HAS GANADO
        }
        else{
            var getScore = Integer.parseInt(divideScore.text.toString())
            if(getScore > 0){
                getScore -= 1;
            }
            divideScore.text = getScore.toString()
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
        fnumber = (10..20).random()
        snumber = (1..9).random()
        if(fnumber < snumber){generateProblem(); return }
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
}