package com.example.linguawarrior.ui.screens.Game

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.LinguaWarrior.R
import com.example.LinguaWarrior.ui.theme.extended
import com.example.linguawarrior.ui.SharedViewModel

@Composable
fun QuestionView(
    quizViewModel: SharedViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

    val quizUiState by quizViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxHeight(0.7f)
            .padding(dimensionResource(id = R.dimen.padding_medium)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.pick_the_best_option),
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = quizUiState.currentQuestion.word,
            style = MaterialTheme.typography.titleLarge
        )

        QuestionOptions(
            quizViewModel = quizViewModel
        )
    }
}

@Composable
fun QuestionOptions(
    quizViewModel: SharedViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val quizUiState by quizViewModel.uiState.collectAsState()

    var selected by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large))
    ) {
        quizUiState.currentQuestion.options.forEach {option ->
            SelectOption(
                option = option,
                onSelect = { if (quizUiState.canClick) {
                    selected = option
                    quizViewModel.checkUserAnswer(option) } },
                color = when {
                    selected == option && (quizUiState.answeredWrong) ->
                        MaterialTheme.colorScheme.error
                    option == quizUiState.currentQuestion.answer && !(quizUiState.canClick)  ->
                            extended.success.color
                    else ->
                        MaterialTheme.colorScheme.primaryContainer
                },
                textColor  = when {
                    selected == option  ->
                        MaterialTheme.colorScheme.onError
                    option == quizUiState.currentQuestion.answer && !(quizUiState.canClick)  ->
                        MaterialTheme.colorScheme.onError
                    else ->
                        MaterialTheme.colorScheme.onPrimaryContainer
                }
            )
        }
    }
}

@Composable
fun SelectOption(
    onSelect : () -> Unit,
    color : Color = MaterialTheme.colorScheme.primaryContainer,
    textColor : Color = MaterialTheme.colorScheme.onPrimaryContainer,
    option : String,
    modifier: Modifier = Modifier
) {
    Surface(
        color = color,
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .clickable { onSelect() }
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Text(
                text = option,
                style = MaterialTheme.typography.bodyMedium,
                color = textColor
            )
        }
    }
}
