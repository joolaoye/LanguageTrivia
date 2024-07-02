package com.example.linguawarrior.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.LinguaWarrior.R

@Composable
fun QuestionScreen(
    modifier: Modifier = Modifier,
    quizViewModel: GameViewModel = viewModel(),
    onExitPauseMenu : () -> Unit
) {
    val quizUiState by quizViewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                questionNumber = quizUiState.questionNumber,
                time = ":05",
                score = quizUiState.currentScore,
                onPause = { quizViewModel.pauseGame() }
            )
        }
    ) {
        paddingValues ->

        QuestionView(
            quizViewModel = quizViewModel,
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
    quizViewModel: GameViewModel = viewModel(),
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
    quizViewModel: GameViewModel = viewModel(),
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
fun TopBar(
    questionNumber : Int,
    time : String,
    score: Int,
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
            text = stringResource(R.string.question_count, questionNumber)
        )

        LabelAndTextColumn(
            label = stringResource(R.string.time),
            text = time
        )

        LabelAndTextColumn(
            label = stringResource(R.string.score),
            text = stringResource(R.string.current_score, score)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PauseMenu(
    checked: Boolean,
    onCheckedChanged: (Boolean) -> Unit,
    onResumeQuiz : () -> Unit,
    onExit : () -> Unit,
    onDissmissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDissmissRequest,
    ) {
        Card {
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_medium))
            ) {
                Text(
                    text = stringResource(R.string.trivia_paused),
                    style = MaterialTheme.typography.titleLarge
                )

                TextButtonGroup(
                    onResumeQuiz = onResumeQuiz,
                    onExit = onExit
                )

                RowWithTextAndSwitch(
                    icon = Icons.Filled.VolumeUp,
                    iconDescription = R.string.toggle_audio,
                    text = R.string.audio,
                    checked = checked,
                    onCheckedChanged = onCheckedChanged
                )
            }

        }
    }
}

@Composable
fun TextButtonGroup(
    onResumeQuiz: () -> Unit,
    onExit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(onClick = onResumeQuiz) {
            Text(
                text = stringResource(R.string.resume),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        TextButton(onClick = { /*TODO*/ }) {
            Text(
                text = stringResource(R.string.new_trivia),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        TextButton(onClick = onExit) {
            Text(
                text = stringResource(R.string.exit),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun RowWithTextAndSwitch(
    icon : ImageVector,
    @StringRes iconDescription : Int,
    @StringRes text : Int,
    checked : Boolean,
    onCheckedChanged : (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = iconDescription)
        )

        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.bodyMedium
        )

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChanged
        )

    }
}
