package edu.washington.ruiheli.quizdroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class TopicFrag : Fragment() {
    private val quizApp = QuizApp.instance
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = quizApp.currentTopicIndex
        val currentTopic = quizApp.getTopicRepo().getTopics()[position]
        val topic = view.findViewById<TextView>(R.id.txtTitle_Topic)
        val desc = view.findViewById<TextView>(R.id.txtDesc_Topic)
        val total = view.findViewById<TextView>(R.id.txtTotalQuestion_Topic)
        val btnBegin = view.findViewById<Button>(R.id.btnBeing_Topic)

        val topicStr = currentTopic.getTitle()
        val numOfQuestions = currentTopic.getQuizzes().size

        topic.text = topicStr
        desc.text = currentTopic.getLongDesc()
        total.text = String.format("Number of questions: %d", numOfQuestions )

        btnBegin.setOnClickListener({
            quizApp.currentQuestionIndex = 0
            quizApp.rightAnswerCount = 0
            quizApp.currentSelectedAnswer = 0
            val fragManager = activity!!.supportFragmentManager
            val transition = fragManager.beginTransaction()
            val nextFrag = QuizFrag()
            transition.setCustomAnimations(R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom)
            transition.replace(R.id.frame_fragHolder, nextFrag)
            transition.addToBackStack(null)
            transition.commit()
        })
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_topic, container,false)
    }
}