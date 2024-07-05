package com.example.linguawarrior.ui.screens.start

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
import com.example.LinguaWarrior.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartDialog(
    onConfirmation : () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {},
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
                    text = stringResource(R.string.trivia),
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = stringResource(R.string.pick_the_best_option),
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = stringResource(R.string.you_will_have_10_seconds_to_answer_each_question),
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = stringResource(R.string.the_faster_you_answer_the_higher_your_score),
                    style = MaterialTheme.typography.bodyMedium
                )

                TextButton(onClick = onConfirmation) {
                    Text(
                        text = stringResource(id = R.string.play),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}