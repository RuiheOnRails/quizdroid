package edu.washington.ruiheli.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class ResultFrag : Fragment() {
    private val quizApp = QuizApp.instance

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = quizApp.currentTopicIndex
        val currentTopic = quizApp.getTopicRepo().getTopics()[position]
        val questions = currentTopic.getQuizzes()
        val question = questions[quizApp.currentQuestionIndex]
        val userAnswer = quizApp.currentSelectedAnswer
        val correctAnswer = question.getCorrectIndex() - 1 //due to 1 based index used in json

        val txtUserAnswer = view.findViewById<TextView>(R.id.txtUserAnswer_Result)
        val txtCorrectAnswer = view.findViewById<TextView>(R.id.txtCorrectAnswer_Result)
        val txtCorrectVs = view.findViewById<TextView>(R.id.txtCorrectVs_Result)
        val btnNext = view.findViewById<Button>(R.id.btnNextFinish_Result)

        txtUserAnswer.text = String.format("You answered: %s", question.getOptions()[userAnswer])
        txtCorrectAnswer.text = String.format("Correct answer: %s", question.getOptions()[correctAnswer])

        if (userAnswer == correctAnswer){
            quizApp.rightAnswerCount++
        }

        txtCorrectVs.text = String.format("You have %d out of %d correct",
                quizApp.rightAnswerCount, questions.size)

        if (questions.size - 1 > quizApp.currentQuestionIndex) {
            btnNext.text = "Next"
            btnNext.setOnClickListener({
                quizApp.currentQuestionIndex++
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
            quizApp.rightAnswerCount = 0
            quizApp.currentTopicIndex = 0
            quizApp.currentQuestionIndex = 0
            quizApp.currentSelectedAnswer = 0
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
