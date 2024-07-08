package com.example.linguawarrior.ui.screens.start

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.LinguaWarrior.R
import com.example.LinguaWarrior.ui.theme.LinguaWarriorTheme

@Composable
fun QuizCard(
    onPlay : () -> Unit,
    modifier: Modifier = Modifier,
    @StringRes language : Int
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {

            Text(
                text = stringResource(id = language),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.padding_small))
            )

            Text(
                text = stringResource(id = R.string.how_strong, stringResource(id = language)),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.padding_small))
            )

            OutlinedButton(
                onClick = onPlay,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                }
                Text(
                    text = stringResource(R.string.play_to_find_out),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(end = dimensionResource(id = R.dimen.padding_small))
                )
            }
        }
    }
}

@Preview
@Composable
fun QuizCardLightThemePreviwew() {
    LinguaWarriorTheme {
        QuizCard(
            onPlay = {},
            language =  R.string.french
        )
    }
}

@Preview
@Composable
fun QuizCardDarkThemePreviwew() {
    LinguaWarriorTheme(
        darkTheme = true
    ) {
        QuizCard(
            onPlay = {},
            language =  R.string.french
        )
    }
}