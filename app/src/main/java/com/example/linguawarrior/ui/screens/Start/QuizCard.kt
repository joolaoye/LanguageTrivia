package com.example.linguawarrior.ui.screens.Start

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.LinguaWarrior.R

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