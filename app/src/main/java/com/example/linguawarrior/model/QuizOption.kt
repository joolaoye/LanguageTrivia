package com.example.linguawarrior.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.LinguaWarrior.model.Question

data class QuizOption(
    @DrawableRes val image : Int,
    @StringRes val language: Int,
    val dataset : List<Question>
)
