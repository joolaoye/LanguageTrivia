package com.example.linguawarrior.model

import android.icu.lang.UProperty.NameChoice
import com.example.LinguaWarrior.model.Question

data class Answer(
    val question: Question,
    val choice: String,
    val questionNumber : Int
)
