package edu.washington.ruiheli.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class PreferenceActivity : AppCompatActivity() {
    var quizApp: QuizApp = QuizApp.instance
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)

        val applyBtn = findViewById<Button>(R.id.prefApplyBtn)
        val urlEditText = findViewById<EditText>(R.id.prefUrlEditText)
        val minEditText = findViewById<EditText>(R.id.prefTimeInMinInterval)

        applyBtn.setOnClickListener {
            quizApp.setJsonURL(urlEditText.text.toString())
            quizApp.setRefresh(minEditText.text.toString().toInt())
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }
}
