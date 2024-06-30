package com.example.linguawarrior.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.AlertDialog
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


@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PauseMenu(
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "TRIVIA PAUSED",
                style = MaterialTheme.typography.titleLarge
            )

            TextButtonGroup()

            RowWithTextAndSwitch(
                icon = Icons.Filled.VolumeUp,
                iconDescription = R.string.play,
                text = R.string.pause,
                checked = true,
                onCheckedChanged = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextButtonGroup(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(onClick = { /*TODO*/ }) {
            Text(
                text = "RESUME",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        TextButton(onClick = { /*TODO*/ }) {
            Text(
                text = "NEW TRIVIA",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        TextButton(onClick = { /*TODO*/ }) {
            Text(
                text = "EXIT",
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
    val mediumPadding = dimensionResource(id = R.dimen.padding_medium)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(mediumPadding),
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