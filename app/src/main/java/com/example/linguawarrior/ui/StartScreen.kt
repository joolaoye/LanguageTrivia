package com.example.linguawarrior.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.LinguaWarrior.R
import com.example.linguawarrior.data.quizOptions
import com.example.linguawarrior.ui.components.QuizCard

@Preview
@Composable
fun StartScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
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
                    modifier =  Modifier
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.padding_medium),
                            vertical = dimensionResource(id = R.dimen.padding_small)
                        )
                )
            }
        }
    }
}

