package com.example.linguawarrior.ui.screens.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.LinguaWarrior.R
import com.example.LinguaWarrior.ui.theme.LinguaWarriorTheme
import com.example.linguawarrior.data.quizOptions
import com.example.linguawarrior.ui.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    score : Int,
    answeredCorrectly : Int,
    replayTrivia : () -> Unit,
    revealAnswers : () -> Unit,
    onExit : () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { /*TODO*/ }
    ) {
        Card {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_medium))
                    .fillMaxWidth()
            ) {

                OutlinedCard {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.padding_medium))
                            .fillMaxWidth()
                    ) {
                        LabelAndTextColumn(
                            label = stringResource(id = R.string.score),
                            text = stringResource(id = R.string.current_score, score),
                            modifier = Modifier
                                .padding(
                                    bottom = dimensionResource(id = R.dimen.padding_medium)
                                )
                        )
                        LabelAndTextColumn(
                            label = stringResource(R.string.words_you_got_right),
                            text = stringResource(R.string.answered_correctly, answeredCorrectly),
                            modifier = Modifier
                                .padding()
                        )
                    }

                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.padding_medium))
                ) {
                    TextButton(onClick = replayTrivia) {
                        Text(
                            text = stringResource(R.string.replay_trivia),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    TextButton(onClick = revealAnswers) {
                        Text(
                            text = stringResource(R.string.review_answers),
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
        }
    }
}

@Preview
@Composable
fun ResultScreenPreview() {
    LinguaWarriorTheme {
        ResultScreen(
            score = 100,
            answeredCorrectly = 1,
            replayTrivia = {},
            revealAnswers = {},
            onExit = {}
        )
    }
}