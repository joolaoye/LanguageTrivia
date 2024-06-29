package com.example.linguawarrior.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.LinguaWarrior.R
import com.example.linguawarrior.ui.components.SelectOption

@Preview
@Composable
fun QuestionScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar()
        }
    ) {
        paddingValues ->

        QuestionView(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun QuestionView(
    modifier: Modifier = Modifier
) {
    val mediumPadding = dimensionResource(id = R.dimen.padding_medium)

    Column(
        modifier = modifier
            .fillMaxHeight(0.7f)
            .padding(mediumPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Pick the best option that translates the word to English",
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "havoc",
            style = MaterialTheme.typography.titleLarge
        )

        QuestionOptions()
    }
}

@Composable
fun QuestionOptions(
    modifier: Modifier = Modifier
) {
    val mediumPadding = dimensionResource(id = R.dimen.padding_large)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(mediumPadding)
    ) {
        SelectOption(option = "despair")
        SelectOption(option = "anger")
        SelectOption(option = "loyalty")
        SelectOption(option = "confusion")
    }
}

@Preview
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_small))
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.Pause,
                contentDescription = stringResource(R.string.pause)
            )
        }

        LabelAndTextColumn(
            label = "QUESTION",
            text = "1/10"
        )

        LabelAndTextColumn(
            label = "TIME",
            text = ":05"
        )

        LabelAndTextColumn(
            label = "SCORE",
            text = "100"
        )
    }
}

@Composable
fun LabelAndTextColumn(
    label : String,
    text : String,
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
            style = MaterialTheme.typography.bodyMedium
        )
    }
}