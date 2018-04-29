package edu.washington.ruiheli.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class FragHolderActivity : AppCompatActivity() {
    val fragManager = supportFragmentManager
    private val quizApp = QuizApp.instance


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frag_holder)
        showTopic()
    }

    fun showTopic(){
        val transition = fragManager.beginTransaction()
        transition.setCustomAnimations(R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom)
        val frag = TopicFrag()
        transition.addToBackStack(null)
        transition.replace(R.id.frame_fragHolder, frag)
        transition.commit()
    }

    override fun onBackPressed() {
        quizApp.rightAnswerCount = 0
        quizApp.currentTopicIndex = 0
        quizApp.currentQuestionIndex = 0
        quizApp.currentSelectedAnswer = 0
        val i = Intent(this,MainActivity::class.java)
        this.startActivity(i)
    }
}
