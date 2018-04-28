package edu.washington.ruiheli.quizdroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView


class QuizFrag: Fragment(){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit_Quiz)
        btnSubmit.isEnabled = false
        val position = QuizSingleton.currentTopicIndex
        val currentQuestionIdx = QuizSingleton.currentQuestionIndex
        val topic = view.findViewById<TextView>(R.id.txtTopic_Quiz)
        val progress = view.findViewById<TextView>(R.id.txtProgress_Quiz)
        val txtQuestion = view.findViewById<TextView>(R.id.txtQuestion_Quiz)
        val rgOptions = view.findViewById<RadioGroup>(R.id.rgOptions_Quiz)
        val rbOption1 = view.findViewById<RadioButton>(R.id.rbOption1_Quiz)
        val rbOption2 = view.findViewById<RadioButton>(R.id.rbOption2_Quiz)
        val rbOption3 = view.findViewById<RadioButton>(R.id.rbOption3_Quiz)
        val rbOption4 = view.findViewById<RadioButton>(R.id.rbOption4_Quiz)

        val topicStr = QuizSingleton.topics!![position]

        val questions = QuizSingleton.topicQuestions!![topicStr]!!
        val options = QuizSingleton.options!![questions[currentQuestionIdx]]!!

        topic.text = topicStr
        progress.text = String.format("%d / %d",currentQuestionIdx+1, questions.size)
        txtQuestion.text = questions[currentQuestionIdx]
        rbOption1.text = options[0]
        rbOption2.text = options[1]
        rbOption3.text = options[2]
        rbOption4.text = options[3]

        rgOptions.setOnCheckedChangeListener{ _,_ ->
            btnSubmit.isEnabled = true
            var selectedID = rgOptions.checkedRadioButtonId
            when(selectedID){
                R.id.rbOption1_Quiz -> QuizSingleton.currentSelectedAnswer = 0
                R.id.rbOption2_Quiz -> QuizSingleton.currentSelectedAnswer = 1
                R.id.rbOption3_Quiz -> QuizSingleton.currentSelectedAnswer = 2
                R.id.rbOption4_Quiz -> QuizSingleton.currentSelectedAnswer = 3
            }
        }

        btnSubmit.setOnClickListener{
            val fragManager = activity!!.supportFragmentManager
            val transition = fragManager.beginTransaction()
            transition.setCustomAnimations(R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom)
            val nextFrag = ResultFrag()
            transition.replace(R.id.frame_fragHolder, nextFrag)
            transition.addToBackStack(null)
            transition.commit()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_quiz,container,false)
    }
}