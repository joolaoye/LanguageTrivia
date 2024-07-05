package com.example.linguawarrior.ui.screens.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.LinguaWarrior.R
import androidx.compose.ui.graphics.Color
import com.example.LinguaWarrior.ui.theme.extended
import com.example.linguawarrior.ui.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    questionNumber : Int,
    time : Long,
    score: Int,
    onPause : () -> Unit,
    modifier: Modifier = Modifier
) {

    TopAppBar(
        title = {
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
                    text = ":$time",
                    textColor = if (time < 5) {
                        MaterialTheme.colorScheme.error
                    } else {
                        extended.success.color
                    }
                )

                LabelAndTextColumn(
                    label = stringResource(R.string.score),
                    text = stringResource(R.string.current_score, score)
                )
            }
        }
    )
}

@Composable
fun LabelAndTextColumn(
    label: String,
    text: String,
    textColor : Color = MaterialTheme.colorScheme.onBackground,
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
            style = MaterialTheme.typography.bodyLarge,
            color = textColor
        )
    }
}