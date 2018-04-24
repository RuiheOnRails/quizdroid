package edu.washington.ruiheli.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import org.w3c.dom.Text

class Quiz : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit_Quiz)
        btnSubmit.isEnabled = false
        val position = QuizSingleton.currentTopicIndex
        val currentQuestionIdx = QuizSingleton.currentQuestionIndex
        val topic = findViewById<TextView>(R.id.txtTopic_Quiz)
        val progress = findViewById<TextView>(R.id.txtProgress_Quiz)
        val txtQuestion = findViewById<TextView>(R.id.txtQuestion_Quiz)
        val rgOptions = findViewById<RadioGroup>(R.id.rgOptions_Quiz)
        val rbOption1 = findViewById<RadioButton>(R.id.rbOption1_Quiz)
        val rbOption2 = findViewById<RadioButton>(R.id.rbOption2_Quiz)
        val rbOption3 = findViewById<RadioButton>(R.id.rbOption3_Quiz)
        val rbOption4 = findViewById<RadioButton>(R.id.rbOption4_Quiz)

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
            val i = Intent(this, Result::class.java)
            this.startActivity(i)
        }
    }
}
