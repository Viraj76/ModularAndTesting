package com.appsv.herolist.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.appsv.core.domain.ProgressBarState
import com.appsv.herolist.components.HeroListItem

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HeroList(
    state : HeroListState
){

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn {
            items(state.heros) { hero ->
                HeroListItem(hero = hero, onSelectHero = {})
            }
        }
        if (state.progressBarState is ProgressBarState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}