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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.LinguaWarrior.R
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

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large))
    ) {
        quizUiState.currentQuestion.options.forEach {option ->
            SelectOption(
                option = option,
                onSelect = { quizViewModel.checkUserAnswer(option) }
            )
        }
    }
}

@Composable
fun SelectOption(
    onSelect : () -> Unit,
    option : String,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
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
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
