package com.example.linguawarrior.ui.screens.start

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.LinguaWarrior.R
import com.example.LinguaWarrior.ui.theme.LinguaWarriorTheme
import com.example.linguawarrior.data.quizOptions
import com.example.linguawarrior.ui.shared.viewmodel.SharedUiEvent
import com.example.linguawarrior.ui.shared.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(
    onSharedEvent : (SharedUiEvent) -> Unit,
    onConfirmation: () -> Unit,
    modifier: Modifier = Modifier
) {
    var displayDialog by rememberSaveable { mutableStateOf(false) }

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

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            items(quizOptions) {
                QuizCard(
                    language = it.language,
                    onPlay = {
                        onSharedEvent(SharedUiEvent.UploadQuestionSet(it.dataset))
                        displayDialog = !displayDialog
                             },
                    modifier =  Modifier
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.padding_small),
                            vertical = dimensionResource(id = R.dimen.padding_small)
                        )
                )
            }
        }
    }

    if (displayDialog) {
        StartDialog(
            onConfirmation = {
                displayDialog = !displayDialog
                onConfirmation()
            }
        )
    }
}

@Preview
@Composable
fun StartScreenLightThemePreview() {
    LinguaWarriorTheme {
        StartScreen(
            onSharedEvent = {},
            onConfirmation = {}
        )
    }
}

@Preview
@Composable
fun StartScreenDarkThemePreview() {
    LinguaWarriorTheme(
        darkTheme = true
    ) {
        StartScreen(
            onSharedEvent = {},
            onConfirmation = {}
        )
    }
}
