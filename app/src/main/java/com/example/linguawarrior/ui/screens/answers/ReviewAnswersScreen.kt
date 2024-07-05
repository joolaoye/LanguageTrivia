package com.example.linguawarrior.ui.screens.answers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.LinguaWarrior.R
import com.example.linguawarrior.ui.SharedViewModel
import com.example.linguawarrior.ui.screens.game.QuestionView

@Composable
fun RevealAnswersScreen(
    sharedViewModel: SharedViewModel,
    modifier: Modifier = Modifier
        .fillMaxSize()
) {
    val sharedUiState by sharedViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        LazyColumn {
            items(sharedUiState.answers) {
                answer ->

                QuestionView(
                    word = answer.question.word,
                    options = answer.question.options,
                    canClick = false,
                    answer = answer.question.answer,
                    selected = answer.choice
                )

            }
        }
    }
}