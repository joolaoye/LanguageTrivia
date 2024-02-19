package com.example.LinguaWarrior.utils

import android.content.Context
import com.example.LinguaWarrior.model.Answered
import com.example.LinguaWarrior.model.Question
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.system.exitProcess

object Constants {
    val COLUMNS = 6

    var csvFileName = "frenchDataset.csv"
    var points = 0
    var questionRight = 0
    lateinit var answers : MutableList<Answered>

    val readCSV: (input: String) -> List<String> = { input -> input.split(',', ignoreCase = false, limit = COLUMNS) }

    fun getQuestions(context: Context): MutableList<Question> {
            var questions = mutableListOf<Question>()

            try {
                val inputStream  = context.assets.open(csvFileName)
                val fReader = BufferedReader(InputStreamReader(inputStream))
                val contents = fReader.readLines()


                for (i in 1 until contents.size) {
                    val csvValues = readCSV(contents[i])
                    val question = Question(csvValues[0], csvValues[1], csvValues[2], csvValues[3], csvValues[4], csvValues[5])
                    questions.add(question)
                }


            }
            catch (e: Exception) {
                exitProcess(1)
            }

        return questions
    }
}
