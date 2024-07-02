package com.example.LinguaWarrior.model

data class Question (
    val word: String = "",
    val options : List<String> = listOf(),
    val answer : String = ""
)

data class Answered (
    var word: String,
    var selected: String,
    var correct: String,
    var answeredCorrectly: Boolean
)