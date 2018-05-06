package edu.washington.ruiheli.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private val localRepo = LocalTopicRepository()
    private var quizApp  = QuizApp(localRepo)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quizApp = QuizApp.instance
        setContentView(R.layout.activity_main)
        var listView = findViewById<ListView>(R.id.main_ListView)
        quizApp.rightAnswerCount = 0
        quizApp.currentTopicIndex = 0
        quizApp.currentQuestionIndex = 0
        quizApp.currentSelectedAnswer = 0
        listView.adapter = CustomeAdapter(quizApp.getTopicRepo().getTopics())
        setTopicFromJsonURL(quizApp.getJsonURL())

    }

    fun setTopicFromJsonURL(jsonUrl: String){
        var url = jsonUrl
        if (jsonUrl.isEmpty()){
            url = "http://tednewardsandbox.site44.com/questions.json"
        }
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("MainActivity", "failed to fetch json")
            }

            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                val gson = GsonBuilder().create()
                val arrTopic = gson.fromJson(body, Array<Topic>::class.java)
                quizApp.setTopicRepo(arrTopic)

                runOnUiThread {
                    main_ListView.adapter = CustomeAdapter(arrTopic)
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.prefMenuItem -> {
            val i = Intent(this@MainActivity, PreferenceActivity::class.java)
            startActivity(i)
            true
        }
        R.id.prefRefresh -> {
            this@MainActivity.finish()
            this@MainActivity.startActivity(this@MainActivity.intent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private class CustomeAdapter( topics: Array<Topic>): BaseAdapter() {
        private val myTopics = topics
        private val quizApp = QuizApp.instance
        override fun getCount(): Int {
            return this.myTopics.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }
        override fun getItem(position: Int): Any {
            return ""
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(parent?.context)
            val row = layoutInflater.inflate(R.layout.row_main, parent, false)
            val rowText = row.findViewById<TextView>(R.id.row_topic)
            val rowShortDescription = row.findViewById<TextView>(R.id.shortDesc_row)
            rowText.text = myTopics[position].getTitle()
            rowShortDescription.text = myTopics[position].getShortDesc()
            row.setOnClickListener({
                val i = Intent(parent?.context, FragHolderActivity::class.java)
                quizApp.currentTopicIndex = position
                parent?.context?.startActivity(i)
            })
            return row
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

