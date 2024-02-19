package com.example.LinguaWarrior.model

data class Question (
    var word: String,
    var option1: String,
    var option2: String,
    var option3: String,
    var option4: String,
    var answer: String
)

data class Answered (
    var word: String,
    var selected: String,
    var correct: String,
    var answeredCorrectly: Boolean
)