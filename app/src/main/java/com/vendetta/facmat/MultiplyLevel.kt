package com.vendetta.facmat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_multiply_level.*


class MultiplyLevel : AppCompatActivity() {
    var finalResult = 0
    var finalPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiply_level)
        generateProblem()

        multiply_1option.setOnClickListener { var position = 1; makeChoice(position)}
        multiply_2option.setOnClickListener {  var position = 2; makeChoice(position)}
        multiply_3option.setOnClickListener { var position = 3; makeChoice(position) }
        multiply_4option.setOnClickListener { var position = 4;makeChoice(position)}
    }

    fun makeChoice(position: Int){
        if(getResult(position))
        {
            var getScore = Integer.parseInt(multiplyScore.text.toString())
            getScore += 1;
            multiplyScore.text = getScore.toString()
            generateProblem()
            //HAS GANADO
        }
        else{
            var getScore = Integer.parseInt(multiplyScore.text.toString())
            if(getScore > 0){
                getScore -= 1;
            }
            multiplyScore.text = getScore.toString()
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
}