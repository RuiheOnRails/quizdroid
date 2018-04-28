package edu.washington.ruiheli.quizdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class FragHolderActivity : AppCompatActivity() {
    val fragManager = supportFragmentManager
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
        super.onBackPressed()
        QuizSingleton.rightAnswerCount = QuizSingleton.preRightAnswerCount
    }
}
