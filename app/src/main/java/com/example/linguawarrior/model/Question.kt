package com.example.LinguaWarrior.model

data class Question (
    val word: String = "",
    val options : List<String> = listOf(),
    val answer : String = ""
)