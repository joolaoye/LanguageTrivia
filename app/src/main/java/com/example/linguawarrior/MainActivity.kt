package com.example.LinguaWarrior

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.LinguaWarrior.ui.QuestionsActivity
import android.content.Intent
import com.example.LinguaWarrior.ui.InformationActivity
import com.example.LinguaWarrior.utils.Constants

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val frenchButton = findViewById<Button>(R.id.french_button)
        val germanButton = findViewById<Button>(R.id.german_button)
        val spanishButton = findViewById<Button>(R.id.spanish_button)

        frenchButton.setOnClickListener {
                Intent(this, InformationActivity::class.java).also {
                    Constants.csvFileName = "frenchDataset.csv"
                    startActivity(it)
                    finish()
                }
        }

        germanButton.setOnClickListener {
            Intent(this, InformationActivity::class.java).also {
                Constants.csvFileName = "germanDataset.csv"
                startActivity(it)
                finish()
            }
        }

        spanishButton.setOnClickListener {
            Intent(this, InformationActivity::class.java).also {
                Constants.csvFileName = "spanishDataset.csv"
                startActivity(it)
                finish()
            }
        }
    }
}