package com.example.quizapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.MainActivity
import com.example.quizapp.R
import com.example.quizapp.model.Answered
import com.example.quizapp.utils.Constants
import kotlin.system.exitProcess

class AnswersActivity: AppCompatActivity(), View.OnClickListener {
    private lateinit var reverseButton : ImageView

    private lateinit var questionOneWord : TextView
    private lateinit var questionOneSelected: TextView
    private lateinit var questionOneCorrect: TextView

    private lateinit var questionTwoWord : TextView
    private lateinit var questionTwoSelected: TextView
    private lateinit var questionTwoCorrect: TextView

    private lateinit var questionThreeWord : TextView
    private lateinit var questionThreeSelected: TextView
    private lateinit var questionThreeCorrect: TextView

    private lateinit var questionFourWord : TextView
    private lateinit var questionFourSelected: TextView
    private lateinit var questionFourCorrect: TextView

    private lateinit var questionFiveWord : TextView
    private lateinit var questionFiveSelected: TextView
    private lateinit var questionFiveCorrect: TextView

    private lateinit var questionSixWord : TextView
    private lateinit var questionSixSelected: TextView
    private lateinit var questionSixCorrect: TextView

    private lateinit var questionSevenWord : TextView
    private lateinit var questionSevenSelected: TextView
    private lateinit var questionSevenCorrect: TextView

    private lateinit var questionEightWord : TextView
    private lateinit var questionEightSelected: TextView
    private lateinit var questionEightCorrect: TextView

    private lateinit var questionNineWord : TextView
    private lateinit var questionNineSelected: TextView
    private lateinit var questionNineCorrect: TextView

    private lateinit var questionTenWord : TextView
    private lateinit var questionTenSelected: TextView
    private lateinit var questionTenCorrect: TextView

    private lateinit var answerList: MutableList<Answered>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        reverseButton = findViewById(R.id.backButton)

        questionOneWord = findViewById(R.id.question1_word)
        questionOneSelected = findViewById(R.id.question1_selected)
        questionOneCorrect = findViewById(R.id.question1_correct)

        questionTwoWord = findViewById(R.id.question2_word)
        questionTwoSelected = findViewById(R.id.question2_selected)
        questionTwoCorrect = findViewById(R.id.question2_correct)

        questionThreeWord = findViewById(R.id.question3_word)
        questionThreeSelected = findViewById(R.id.question3_selected)
        questionThreeCorrect = findViewById(R.id.question3_correct)

        questionFourWord = findViewById(R.id.question4_word)
        questionFourSelected = findViewById(R.id.question4_selected)
        questionFourCorrect = findViewById(R.id.question4_correct)

        questionFiveWord = findViewById(R.id.question5_word)
        questionFiveSelected = findViewById(R.id.question5_selected)
        questionFiveCorrect = findViewById(R.id.question5_correct)

        questionSixWord = findViewById(R.id.question6_word)
        questionSixSelected = findViewById(R.id.question6_selected)
        questionSixCorrect = findViewById(R.id.question6_correct)

        questionSevenWord = findViewById(R.id.question7_word)
        questionSevenSelected = findViewById(R.id.question7_selected)
        questionSevenCorrect = findViewById(R.id.question7_correct)

        questionEightWord = findViewById(R.id.question8_word)
        questionEightSelected = findViewById(R.id.question8_selected)
        questionEightCorrect = findViewById(R.id.question8_correct)

        questionNineWord = findViewById(R.id.question9_word)
        questionNineSelected = findViewById(R.id.question9_selected)
        questionNineCorrect = findViewById(R.id.question9_correct)

        questionTenWord = findViewById(R.id.question10_word)
        questionTenSelected = findViewById(R.id.question10_selected)
        questionTenCorrect = findViewById(R.id.question10_correct)

        answerList = Constants.answers

        revealAnswers()

        reverseButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.backButton -> {
                Intent(this, ResultActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }

            else ->
                exitProcess(1)
        }
    }

    private fun revealAnswers() {
        questionOneWord.text = answerList[1].word
        questionOneSelected.text =  if (answerList[1].answeredCorrectly) "${answerList[1].selected} ✅" else "${answerList[1].selected} ❌"
        questionOneCorrect.text = if (answerList[1].answeredCorrectly) "${answerList[1].correct}" else "${answerList[1].correct} ✅"

        questionTwoWord.text = answerList[2].word
        questionTwoSelected.text =  if (answerList[2].answeredCorrectly) "${answerList[2].selected} ✅" else "${answerList[2].selected} ❌"
        questionTwoCorrect.text = if (answerList[2].answeredCorrectly) "${answerList[2].correct}" else "${answerList[2].correct} ✅"

        questionThreeWord.text = answerList[3].word
        questionThreeSelected.text =  if (answerList[3].answeredCorrectly) "${answerList[3].selected} ✅" else "${answerList[3].selected} ❌"
        questionThreeCorrect.text = if (answerList[3].answeredCorrectly) "${answerList[3].correct}" else "${answerList[3].correct} ✅"

        questionFourWord.text = answerList[4].word
        questionFourSelected.text =  if (answerList[4].answeredCorrectly) "${answerList[4].selected} ✅" else "${answerList[4].selected} ❌"
        questionFourCorrect.text = if (answerList[4].answeredCorrectly) "${answerList[4].correct}" else "${answerList[4].correct} ✅"

        questionFiveWord.text = answerList[5].word
        questionFiveSelected.text =  if (answerList[5].answeredCorrectly) "${answerList[5].selected} ✅" else "${answerList[5].selected} ❌"
        questionFiveCorrect.text = if (answerList[5].answeredCorrectly) "${answerList[5].correct}" else "${answerList[5].correct} ✅"

        questionSixWord.text = answerList[6].word
        questionSixSelected.text =  if (answerList[6].answeredCorrectly) "${answerList[6].selected} ✅" else "${answerList[6].selected} ❌"
        questionSixCorrect.text = if (answerList[6].answeredCorrectly) "${answerList[6].correct}" else "${answerList[6].correct} ✅"

        questionSevenWord.text = answerList[7].word
        questionSevenSelected.text =  if (answerList[7].answeredCorrectly) "${answerList[7].selected} ✅" else "${answerList[7].selected} ❌"
        questionSevenCorrect.text = if (answerList[7].answeredCorrectly) "${answerList[7].correct}" else "${answerList[7].correct} ✅"

        questionEightWord.text = answerList[8].word
        questionEightSelected.text =  if (answerList[8].answeredCorrectly) "${answerList[8].selected} ✅" else "${answerList[8].selected} ❌"
        questionEightCorrect.text = if (answerList[8].answeredCorrectly) "${answerList[8].correct}" else "${answerList[8].correct} ✅"

        questionNineWord.text = answerList[9].word
        questionNineSelected.text =  if (answerList[9].answeredCorrectly) "${answerList[9].selected} ✅" else "${answerList[9].selected} ❌"
        questionNineCorrect.text = if (answerList[9].answeredCorrectly) "${answerList[9].correct}" else "${answerList[9].correct} ✅"


        questionTenWord.text = answerList[10].word
        questionTenSelected.text =  if (answerList[10].answeredCorrectly) "${answerList[10].selected} ✅" else "${answerList[10].selected} ❌"
        questionTenCorrect.text = if (answerList[10].answeredCorrectly) "${answerList[10].correct}" else "${answerList[10].correct} ✅"

    }
}