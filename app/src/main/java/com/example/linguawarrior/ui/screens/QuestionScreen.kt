package com.example.linguawarrior.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.LinguaWarrior.R
import com.example.LinguaWarrior.model.Question
import com.example.linguawarrior.data.frenchQuestions
import com.example.linguawarrior.ui.components.PauseMenu
import com.example.linguawarrior.ui.components.SelectOption

@Composable
fun QuestionScreen(
    quizViewModel: QuizViewModel = viewModel(),
    onExitPauseMenu : () -> Unit,
    modifier: Modifier = Modifier
) {
    val quizUiState by quizViewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                questionNumber = "1",
                time = ":05",
                score = "100",
                onPause = { quizViewModel.pauseGame() }
            )
        }
    ) {
        paddingValues ->

        QuestionView(
            question = frenchQuestions[0],
            modifier = Modifier.padding(paddingValues)
        )

        if (quizUiState.isPaused) {
            PauseMenu(
                checked = quizUiState.audioToggleChecked,
                onCheckedChanged = { quizViewModel.toggleAudio() },
                onDissmissRequest = { quizViewModel.dismissPauseDialog() },
                onResumeQuiz = { quizViewModel.dismissPauseDialog() },
                onExit = {
                    quizViewModel.dismissPauseDialog()
                    onExitPauseMenu()
                }
            )
        }
    }
}

@Composable
fun QuestionView(
    question: Question,
    modifier: Modifier = Modifier
) {

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
            text = question.word,
            style = MaterialTheme.typography.titleLarge
        )

        QuestionOptions(
            optionList = question.options
        )
    }
}

@Composable
fun QuestionOptions(
    optionList : List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large))
    ) {
        optionList.forEach {option ->
            SelectOption(option = option)
        }
    }
}

@Composable
fun TopBar(
    questionNumber : String,
    time : String,
    score: String,
    onPause : () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_small))
    ) {
        IconButton(onClick = onPause) {
            Icon(
                imageVector = Icons.Filled.Pause,
                contentDescription = stringResource(R.string.pause)
            )
        }

        LabelAndTextColumn(
            label = stringResource(R.string.question),
            text = "$questionNumber/10"
        )

        LabelAndTextColumn(
            label = stringResource(R.string.time),
            text = time
        )

        LabelAndTextColumn(
            label = stringResource(R.string.score),
            text = score
        )
    }
}

@Composable
fun LabelAndTextColumn(
    label : String,
    text : String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}