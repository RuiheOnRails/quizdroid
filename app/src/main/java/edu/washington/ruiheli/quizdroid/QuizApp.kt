package edu.washington.ruiheli.quizdroid

import android.app.Application
import android.util.Log

class QuizApp(topicRepo: TopicRepository = LocalTopicRepository()): Application(){
    private val TAG = "QuizApp"
    private val topicRepo: TopicRepository = topicRepo
    var currentTopicIndex: Int = 0
    var currentQuestionIndex: Int = 0
    var currentSelectedAnswer: Int = 0
    var rightAnswerCount: Int = 0

    override fun onCreate() {
        Log.i(TAG, "QuizApp onCreate called")
        super.onCreate()
    }

    companion object {
         var instance: QuizApp = QuizApp()
            private set
    }

    fun getTopicRepo(): TopicRepository{
        return topicRepo
    }
}