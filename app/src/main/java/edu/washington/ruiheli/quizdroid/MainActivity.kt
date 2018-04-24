package edu.washington.ruiheli.quizdroid

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView



class MainActivity : AppCompatActivity() {
    //
    private val topics = arrayOf("Math", "Physics", "Marvel")
    private val topicDescriptions = hashMapOf<String, String>("Math" to "Questions on basic math",
            "Physics" to "Questions on basic physics",
            "Marvel" to "Questions on Marvel Super Heros")
    private val questions = hashMapOf<String, Array<String>>("Math" to arrayOf("1+1 = ?", "1+2 = ?"),
            "Physics" to arrayOf("What's the 1kg/ m/s^2 in Newton unit?", "How many Gs is earth's gravity?"),
            "Marvel" to arrayOf("What's iron man's first name?", "What's Dr.Strange's last name?"))
    private val options = hashMapOf<String, Array<String>>("1+1 = ?" to arrayOf("1","2","3","4"),
            "1+2 = ?" to arrayOf("1","2","3","4"),
            "What's the 1kg/ m/s^2 in Newton unit?" to arrayOf("1","2","3","4"),
            "How many Gs is earth's gravity?" to arrayOf("1","2","3","4"),
            "What's iron man's first name?" to arrayOf("Tony", "Steven", "Bruce", "Strange"),
            "What's Dr.Strange's last name?" to arrayOf("Tony", "Steven", "Bruce", "Strange"))
    private val answers = hashMapOf<String, String>("1+1 = ?" to "2",
            "1+2 = ?" to "3",
            "What's the 1kg/ m/s^2 in Newton unit?" to "1",
            "How many Gs is earth's gravity?" to "1",
            "What's iron man's first name?" to "Tony",
            "What's Dr.Strange's last name?" to "Strange")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        QuizSingleton.topics = this.topics
        QuizSingleton.topicDescription = this.topicDescriptions
        QuizSingleton.topicQuestions = this.questions
        QuizSingleton.options = this.options
        QuizSingleton.answers = this.answers
        var listView = findViewById<ListView>(R.id.main_ListView)

        listView.adapter = CustomeAdapter(this, topics)
    }

    private class CustomeAdapter(context: Context, topics: Array<String>): BaseAdapter() {
        private val myContext: Context = context
        private val myTopics: Array<String> = topics

        override fun getCount(): Int {
            return myTopics.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }
        override fun getItem(position: Int): Any {
            return ""
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(myContext)
            val row = layoutInflater.inflate(R.layout.row_main, parent, false)
            val rowText = row.findViewById<TextView>(R.id.row_topic)
            rowText.text = myTopics[position]
            row.setOnClickListener({
                val i = Intent(myContext, TopicActivity::class.java)
                QuizSingleton.currentTopicIndex = position
                myContext.startActivity(i)
            })
            return row
        }
    }
}
