package edu.washington.ruiheli.quizdroid

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private val localRepo = LocalTopicRepository()
    private var quizApp  = QuizApp(localRepo)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quizApp = QuizApp.instance
        setSupportActionBar(findViewById(R.id.my_toolbar))
        setContentView(R.layout.activity_main)
        var listView = findViewById<ListView>(R.id.main_ListView)
        quizApp.rightAnswerCount = 0
        quizApp.currentTopicIndex = 0
        quizApp.currentQuestionIndex = 0
        quizApp.currentSelectedAnswer = 0
        listView.adapter = CustomeAdapter(this, quizApp.getTopicRepo().getTopics())

    }

    private class CustomeAdapter(context: Context, topics: Array<Topic>): BaseAdapter() {
        private val myContext: Context = context
        private val myTopics: Array<Topic> = topics
        private val quizApp = QuizApp.instance
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
            val rowShortDescription = row.findViewById<TextView>(R.id.shortDesc_row)
            rowText.text = myTopics[position].getTitle()
            rowShortDescription.text = myTopics[position].getShortDesc()
            row.setOnClickListener({
                val i = Intent(myContext, FragHolderActivity::class.java)
                quizApp.currentTopicIndex = position
                myContext.startActivity(i)
            })
            return row
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.prefMenuItem -> {
            val newToast = Toast.makeText(this.applicationContext, "clicked on preference", Toast.LENGTH_LONG)
            newToast.show()
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        //exit to home
        val i = Intent(Intent.ACTION_MAIN)
        i.addCategory(Intent.CATEGORY_HOME)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
    }
}

