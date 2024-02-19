package com.example.LinguaWarrior.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.LinguaWarrior.R

class InformationActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        val startQuiz = findViewById<Button>(R.id.start_quiz)

        startQuiz.setOnClickListener {
            Intent(this, QuestionsActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}