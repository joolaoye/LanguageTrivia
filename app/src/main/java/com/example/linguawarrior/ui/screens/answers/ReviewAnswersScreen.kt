package com.example.linguawarrior.ui.screens.answers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.LinguaWarrior.R
import com.example.LinguaWarrior.ui.theme.extended
import com.example.linguawarrior.ui.shared.viewmodel.SharedViewModel
import com.example.linguawarrior.ui.shared.components.QuestionView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RevealAnswersScreen(
    sharedViewModel: SharedViewModel,
    navigateUp : () -> Unit,
    modifier: Modifier = Modifier
) {
    val sharedUiState by sharedViewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.review_answers))
                },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(sharedUiState.answerSet) { answer ->

                AnswerView(
                    questionNumber = answer.questionNumber,
                    answeredRight = answer.choice == answer.question.answer
                )

                QuestionView(
                    word = answer.question.word,
                    options = answer.question.options,
                    canClick = false,
                    answer = answer.question.answer,
                    selected = answer.choice
                )

            }
        }
    }
}

@Composable
fun AnswerView(
    questionNumber : Int,
    answeredRight : Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Text(
                text = stringResource(R.string.question_number, questionNumber),
                style = MaterialTheme.typography.bodyLarge
            )
            
            Spacer(modifier = modifier.weight(1f))
            
            if (answeredRight) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = null,
                    tint = extended.success.color
                )
            }
            
            else {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}