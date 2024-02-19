package com.example.LinguaWarrior.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.LinguaWarrior.MainActivity
import com.example.LinguaWarrior.utils.Constants
import com.example.LinguaWarrior.R
import kotlin.system.exitProcess

class ResultActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val score = findViewById<TextView>(R.id.scoreboard)
        val answeredCorrectly = findViewById<TextView>(R.id.questionsCorrect)

        score.text = "SCORE\n\n ${Constants.points} OUT OF 5400"
        answeredCorrectly.text = "WORDS YOU GOT RIGHT\n\n${Constants.questionRight} OF 10"

        findViewById<View>(R.id.replay_quiz).setOnClickListener(this)
        findViewById<View>(R.id.review_answers).setOnClickListener(this)
        findViewById<View>(R.id.exit_quiz).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.replay_quiz -> {
                Intent(this, QuestionsActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }

            R.id.review_answers -> {
                Intent(this, AnswersActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }

            R.id.exit_quiz -> {
                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }

            else ->
                exitProcess(1)
        }
    }
}
