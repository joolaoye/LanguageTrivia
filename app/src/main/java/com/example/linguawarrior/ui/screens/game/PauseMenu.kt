package com.example.linguawarrior.ui.screens.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PauseMenu(
    onResumeQuiz : () -> Unit,
    onExit : () -> Unit,
    onNewTrivia : () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { },
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

                    TextButton(onClick = onNewTrivia) {
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
        }
    }
}

@Preview
@Composable
fun PauseMenuPreview() {
    LinguaWarriorTheme {
        PauseMenu(
            onResumeQuiz = { /*TODO*/ },
            onExit = { /*TODO*/ },
            onNewTrivia = { /*TODO*/ }
        )
    }
}