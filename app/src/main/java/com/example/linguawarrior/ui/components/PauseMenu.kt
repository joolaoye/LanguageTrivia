package com.example.linguawarrior.ui.components

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
import androidx.compose.material3.DismissState
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.LinguaWarrior.R
import com.example.LinguaWarrior.ui.theme.LinguaWarriorTheme


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

@Preview
@Composable
fun PreviewPauseMenu() {
    LinguaWarriorTheme {
        PauseMenu(
            checked = true,
            onCheckedChanged = {},
            onDissmissRequest = {},
            onResumeQuiz = {},
            onExit = {}
        )
    }
}