package com.example.quizapp.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import com.example.quizapp.R
import com.example.quizapp.model.Question
import com.example.quizapp.utils.Constants
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.quizapp.model.Answered
import kotlin.random.Random
import kotlin.system.exitProcess

/*
Fix the edge case when the timers runs out
*/
class QuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private val DATASETSIZE = 50
    private val QUESTIONSIZE = 10
    private var questionCounter = 1
    private var questionNumber = -1

    private lateinit var countdownTimer: CountDownTimer

    private lateinit var questionsList: MutableList<Question>
    private var questionSet = MutableList<Int?>(1){null}

    private lateinit var progressTextView: TextView
    private lateinit var timerTextView: TextView
    private lateinit var pointsTextView: TextView

    private lateinit var promptTextView: TextView

    private lateinit var optionOneTextView: TextView
    private lateinit var optionTwoTextView: TextView
    private lateinit var optionThreeTextView: TextView
    private lateinit var optionFourTextView: TextView

    private var optionSelected = false

    private lateinit var submit: Button

    private lateinit var currentQuestion: Question

    private var startTime: Long = 0
    private var endTime: Long = 0

    private var currentPoints = 0
    private var questionsRight = 0

    private var endOfTrivia = false

    private var answers: MutableList<Answered> = MutableList(1) { Answered("", "", "", false) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        progressTextView = findViewById(R.id.progress)
        timerTextView = findViewById(R.id.timer)
        pointsTextView = findViewById(R.id.points)

        promptTextView = findViewById(R.id.prompt)

        optionOneTextView = findViewById(R.id.option_one)
        optionTwoTextView = findViewById(R.id.option_two)
        optionThreeTextView = findViewById(R.id.option_three)
        optionFourTextView = findViewById(R.id.option_four)

        optionOneTextView.setOnClickListener(this)
        optionTwoTextView.setOnClickListener(this)
        optionThreeTextView.setOnClickListener(this)
        optionFourTextView.setOnClickListener(this)

        submit = findViewById(R.id.submit)
        submit.visibility = View.GONE
        submit.setOnClickListener(this)

        questionsList = Constants.getQuestions(this)

        setQuestion()

    }

    private fun updateTimerText(millisUntilFinished: Long) {
        val seconds = millisUntilFinished / 1000

        // Construct the text with SECS in black
        val text = ":$seconds SECS"
        val spannableString = SpannableString(text)

        val customRedColor = Color.parseColor("#FF0000")
        val customGreenColor = Color.parseColor("#00A300")

        val textColor = if (seconds < 6) customRedColor else customGreenColor

        spannableString.setSpan(
            ForegroundColorSpan(textColor),
            0,
            text.indexOf(" "),
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        timerTextView.text = spannableString
    }

    private fun setQuestion() {
        resetOptions()
        optionSelected = false

        var spannableString = SpannableString("${questionCounter} OF ${QUESTIONSIZE}")
        var startIndex = spannableString.indexOf("${questionCounter}")
        var endIndex = startIndex + "${questionCounter}".length
        spannableString.setSpan(StyleSpan(Typeface.BOLD), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        progressTextView.text = spannableString

        while (questionCounter <= QUESTIONSIZE) {
            questionNumber = Random.nextInt(DATASETSIZE)

            if (!questionSet.contains(questionNumber)) {
                break
            }
        }

        questionSet.add(questionNumber)
        val question = questionsList[questionNumber]
        currentQuestion = question

        // Display prompt in TextView
        spannableString = SpannableString("Pick the option that best translates ${question.word} to English")
        startIndex = spannableString.indexOf(question.word)
        endIndex = startIndex + question.word.length
        spannableString.setSpan(StyleSpan(Typeface.BOLD), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        promptTextView.text = spannableString

        // Display Options
        optionOneTextView.text = question.option1
        optionTwoTextView.text = question.option2
        optionThreeTextView.text = question.option3
        optionFourTextView.text = question.option4

        countdownTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                updateTimerText(millisUntilFinished)
            }

            override fun onFinish() {
                val spannableString = SpannableString("TIME'S UP")
                val customRedColor = Color.parseColor("#FF0000")

                spannableString.setSpan(
                    ForegroundColorSpan(customRedColor),
                    0,
                    spannableString.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                timerTextView.text = spannableString

                selectedOption()
            }
        }

        countdownTimer.start()
        startTime = System.currentTimeMillis()

        questionCounter += 1

        if (questionCounter > QUESTIONSIZE) {
            submit.text = "Result"
            endOfTrivia = true
        }
    }

    private fun resetOptions() {
        val options = mutableListOf<TextView>()
        options.add(optionOneTextView)
        options.add(optionTwoTextView)
        options.add(optionThreeTextView)
        options.add(optionFourTextView)

        for (option in options) {
            option.setTextColor(Color.parseColor("#000000"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOption(textView: TextView? = null) {
        if (optionSelected) {
            return
        }

        endTime = System.currentTimeMillis()
        countdownTimer.cancel()
        val timeRemaining = (10 - ((endTime - startTime) / 1000))
        optionSelected = true

        val options = mutableListOf<TextView>()
        options.add(optionOneTextView)
        options.add(optionTwoTextView)
        options.add(optionThreeTextView)
        options.add(optionFourTextView)

        if (textView != null && textView.text == currentQuestion.answer) {
            questionsRight += 1
            currentPoints += 60 * timeRemaining.toInt()

            answers.add(Answered(currentQuestion.word, currentQuestion.answer, "", true))

            pointsTextView.text = "${currentPoints} PTS"
        }
        else if (textView != null && textView.text != currentQuestion.answer) {
            answers.add(Answered(currentQuestion.word, textView.text.toString(), currentQuestion.answer, false))

            textView.setTextColor(Color.parseColor("#FFFFFF"))
            textView.setTypeface(textView.typeface, Typeface.BOLD)
            textView.background = ContextCompat.getDrawable(
                this,
                R.drawable.wrong_option_border_bg
            )
        }

        for (option in options) {
            if (option.text == currentQuestion.answer) {
                option.setTextColor(Color.parseColor("#FFFFFF"))
                option.setTypeface(option.typeface, Typeface.BOLD)
                option.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.correct_option_border_bg
                )
            }
        }

        submit.visibility = View.VISIBLE
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.option_one -> {
                selectedOption(optionOneTextView)
            }

            R.id.option_two -> {
                selectedOption(optionTwoTextView)
            }

            R.id.option_three -> {
                selectedOption(optionThreeTextView)
            }

            R.id.option_four -> {
                selectedOption(optionFourTextView)
            }

            R.id.submit -> {
                if (endOfTrivia) {
                    Constants.points = currentPoints
                    Constants.questionRight = questionsRight
                    Constants.answers = answers

                    Intent(this, ResultActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                }
                else {
                    setQuestion()
                    submit.visibility = View.GONE
                }
            }

            else ->
                exitProcess(1)
        }
    }
}