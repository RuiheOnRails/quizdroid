package edu.washington.ruiheli.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class ResultFrag : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = QuizSingleton.currentTopicIndex
        val topicStr = QuizSingleton.topics!![position]
        val questions = QuizSingleton.topicQuestions!![topicStr]!!
        val question = questions[QuizSingleton.currentQuestionIndex]
        val userAnswer = QuizSingleton.options!![question]!![QuizSingleton.currentSelectedAnswer]
        val correctAnswer = QuizSingleton.answers!![question]!!

        val txtUserAnswer = view.findViewById<TextView>(R.id.txtUserAnswer_Result)
        val txtCorrectAnswer = view.findViewById<TextView>(R.id.txtCorrectAnswer_Result)
        val txtCorrectVs = view.findViewById<TextView>(R.id.txtCorrectVs_Result)
        val btnNext = view.findViewById<Button>(R.id.btnNextFinish_Result)

        txtUserAnswer.text = String.format("You answered: %s", userAnswer)
        txtCorrectAnswer.text = String.format("Correct answer: %s", correctAnswer)

        if (userAnswer == correctAnswer){
            QuizSingleton.preRightAnswerCount = QuizSingleton.rightAnswerCount++
        }

        txtCorrectVs.text = String.format("You have %d out of %d correct",
                QuizSingleton.rightAnswerCount, questions.size)

        if (questions.size - 1 > QuizSingleton.currentQuestionIndex) {
            btnNext.text = "Next"
            btnNext.setOnClickListener({
                QuizSingleton.currentQuestionIndex++
                val fragManager = activity!!.supportFragmentManager
                val transition = fragManager.beginTransaction()
                val nextFrag = QuizFrag()
                transition.setCustomAnimations(R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom)
                transition.replace(R.id.frame_fragHolder, nextFrag)
                transition.addToBackStack(null)
                transition.commit()
            })
        }else{
            btnNext.text = "Finish"
            btnNext.setOnClickListener({
                val i = Intent(activity, MainActivity::class.java)
                this.startActivity(i)
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_result, container,false)
    }

}
