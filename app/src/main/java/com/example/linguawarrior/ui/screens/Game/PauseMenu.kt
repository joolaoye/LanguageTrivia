package com.example.linguawarrior.ui.screens.Game

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.LinguaWarrior.R
import kotlinx.coroutines.newSingleThreadContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PauseMenu(
    onResumeQuiz : () -> Unit,
    onExit : () -> Unit,
    onNewTrivia : () -> Unit,
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
                    onExit = onExit,
                    newTrivia = onNewTrivia
                )
            }
        }
    }
}

@Composable
fun TextButtonGroup(
    onResumeQuiz: () -> Unit,
    newTrivia : () -> Unit,
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

        TextButton(onClick = newTrivia) {
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
