package com.example.linguawarrior.ui.screens.game

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.example.LinguaWarrior.R
import com.example.LinguaWarrior.ui.theme.extended

@Composable
fun QuestionView(
    modifier: Modifier = Modifier,
    word : String,
    options: List<String>,
    canClick : Boolean,
    answer : String,
    onOptionSelected : (String) -> Unit = {},
    selected : String
) {

    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(dimensionResource(id = R.dimen.padding_medium)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = word,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(vertical = dimensionResource(id = R.dimen.padding_large))
        )

        QuestionOptions(
            options = options,
            canClick = canClick,
            answer = answer,
            onOptionSelected = onOptionSelected,
            selected = selected
        )
    }
}

@Composable
fun QuestionOptions(
    options : List<String>,
    canClick: Boolean,
    answer: String,
    onOptionSelected: (String) -> Unit,
    selected : String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large))
    ) {
        options.forEach {option ->
            SelectOption(
                option = option,
                onSelect = { if (canClick) {
                    onOptionSelected(option) } },
                color = when {
                    selected == option && (selected != answer) ->
                        MaterialTheme.colorScheme.error
                    option == answer && !(canClick)  ->
                            extended.success.color
                    else ->
                        MaterialTheme.colorScheme.primaryContainer
                },
                textColor  = when {
                    selected == option  ->
                        MaterialTheme.colorScheme.onError
                    option == answer && !(canClick)  ->
                        MaterialTheme.colorScheme.onError
                    else ->
                        MaterialTheme.colorScheme.onPrimaryContainer
                }
            )
        }
    }
}

@Composable
fun SelectOption(
    onSelect : () -> Unit,
    color : Color = MaterialTheme.colorScheme.primaryContainer,
    textColor : Color = MaterialTheme.colorScheme.onPrimaryContainer,
    option : String,
    modifier: Modifier = Modifier
) {
    Surface(
        color = color,
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .clickable { onSelect() }
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Text(
                text = option,
                style = MaterialTheme.typography.bodyMedium,
                color = textColor
            )
        }
    }
}
