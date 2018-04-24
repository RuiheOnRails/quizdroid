package edu.washington.ruiheli.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Result : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val position = QuizSingleton.currentTopicIndex
        val topicStr = QuizSingleton.topics!![position]
        val questions = QuizSingleton.topicQuestions!![topicStr]!!
        val question = questions[QuizSingleton.currentQuestionIndex]
        val userAnswer = QuizSingleton.options!![question]!![QuizSingleton.currentSelectedAnswer]
        val correctAnswer = QuizSingleton.answers!![question]!!

        val txtUserAnswer = findViewById<TextView>(R.id.txtUserAnswer_Result)
        val txtCorrectAnswer = findViewById<TextView>(R.id.txtCorrectAnswer_Result)
        val txtCorrectVs = findViewById<TextView>(R.id.txtCorrectVs_Result)
        val btnNext = findViewById<Button>(R.id.btnNextFinish_Result)

        txtUserAnswer.text = String.format("You answered: %s", userAnswer)
        txtCorrectAnswer.text = String.format("Correct answer: %s", correctAnswer)

        if (userAnswer == correctAnswer){
            QuizSingleton.rightAnswerCount++
        }

        txtCorrectVs.text = String.format("You have %d out of %d correct",
                QuizSingleton.rightAnswerCount, questions.size)

        if (questions.size - 1 > QuizSingleton.currentQuestionIndex) {
            btnNext.text = "Next"
            btnNext.setOnClickListener({
                QuizSingleton.currentQuestionIndex++
                val i = Intent(this,Quiz::class.java)
                this.startActivity(i)
            })
        }else{
            btnNext.text = "Finish"
            btnNext.setOnClickListener({
                val i = Intent(this, MainActivity::class.java)
                this.startActivity(i)
            })
        }
    }
}
