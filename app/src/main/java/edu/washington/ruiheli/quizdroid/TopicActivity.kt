package edu.washington.ruiheli.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class TopicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)

        val position = QuizSingleton.currentTopicIndex
        val topic = findViewById<TextView>(R.id.txtTitle_Topic)
        val desc = findViewById<TextView>(R.id.txtDesc_Topic)
        val total = findViewById<TextView>(R.id.txtTotalQuestion_Topic)
        val btnBegin = findViewById<Button>(R.id.btnBeing_Topic)

        val topicStr = QuizSingleton.topics!![position]
        val numOfQuestions = QuizSingleton.topicQuestions!![topicStr]!!.size

        topic.text = topicStr
        desc.text = QuizSingleton.topicDescription!![topicStr]
        total.text = "Number of questions: " + numOfQuestions.toString()

        btnBegin.setOnClickListener({
            val i = Intent(this, Quiz::class.java)
            QuizSingleton.currentQuestionIndex = 0
            QuizSingleton.rightAnswerCount = 0
            QuizSingleton.currentSelectedAnswer = 0
            this.startActivity(i)
        })
    }
}
