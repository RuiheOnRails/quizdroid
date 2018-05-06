package edu.washington.ruiheli.quizdroid

import android.app.Application
import android.util.Log

class QuizApp(topicRepo: TopicRepository = LocalTopicRepository()): Application(){
    private val TAG = "QuizApp"
    private var topicRepo: TopicRepository = topicRepo
    private var url = "http://tednewardsandbox.site44.com/questions.json"
    var currentTopicIndex: Int = 0
    var currentQuestionIndex: Int = 0
    var currentSelectedAnswer: Int = 0
    var rightAnswerCount: Int = 0
    private var refreshInterval = 10

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

    fun setTopicRepo(arrTopic: Array<Topic>){
        this.topicRepo = object: TopicRepository{
            override fun getTopics(): Array<Topic> {
                return arrTopic
            }
        }
    }

    fun getJsonURL(): String{
        return this.url
    }

    fun setJsonURL(jsonUrl: String){
        this.url = jsonUrl
    }

    fun setRefresh(resfreshInterval: Int){
        this.refreshInterval = refreshInterval
    }

    fun getRefresh(): Int{
        return this.refreshInterval
    }
}