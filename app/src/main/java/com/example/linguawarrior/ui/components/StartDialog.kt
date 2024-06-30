package com.example.linguawarrior.ui.components

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
fun StartDialog(
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { /*TODO*/ }) {
        Card {
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_medium))
            ) {
                Text(
                    text = "TRIVIA",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "You will have 10 seconds to answer each question.",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "The faster you answer, the higher your score.",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "The harder the question, the higher your score.",
                    style = MaterialTheme.typography.bodyMedium
                )

                TextButton(onClick = { /*TODO*/ }) {
                    Text(
                        text = stringResource(id = R.string.play),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun StartDialogPreview() {
    LinguaWarriorTheme {
        StartDialog()
    }
}