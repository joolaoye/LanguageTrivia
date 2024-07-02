package com.example.linguawarrior.ui.screens.Start

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.LinguaWarrior.R
import com.example.LinguaWarrior.ui.theme.LinguaWarriorTheme
import com.example.linguawarrior.data.quizOptions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(
    startViewModel: StartViewModel = viewModel(),
    onConfirmation: () -> Unit,
    modifier: Modifier = Modifier
) {
    val startUiState by startViewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                }
            )
        }
    ) {
        paddingValues ->

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .padding(paddingValues)
        ) {
            items(quizOptions) {it ->
                QuizCard(
                    language = it.language,
                    image = it.image,
                    onPlay = { startViewModel.onPlay() },
                    modifier =  Modifier
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.padding_small),
                            vertical = dimensionResource(id = R.dimen.padding_small)
                        )
                )
            }
        }
    }

    if (startUiState.displayDialog) {
        StartDialog(
            onConfirmation = {
                startViewModel.dismissStartDialog()
                onConfirmation()
            }
        )
    }
}

@Composable
fun QuizCard(
    onPlay : () -> Unit,
    modifier: Modifier = Modifier,
    @StringRes language : Int,
    @DrawableRes image : Int
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.padding_medium)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.small)
                        .size(dimensionResource(id = R.dimen.image_size))
                )

                Spacer(modifier = Modifier.weight(1f))

                OutlinedIconButton(
                    onClick = onPlay
                ) {
                    Icon(
                        imageVector = Icons.Outlined.PlayArrow,
                        contentDescription = stringResource(R.string.play)
                    )
                }
            }

            Text(
                text = stringResource(id = language),
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = stringResource(id = R.string.how_strong, stringResource(id = language)),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

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

@Preview
@Composable
fun StartScreenPreview() {
    LinguaWarriorTheme() {
        StartScreen(
            onConfirmation = {}
        )
    }
}
