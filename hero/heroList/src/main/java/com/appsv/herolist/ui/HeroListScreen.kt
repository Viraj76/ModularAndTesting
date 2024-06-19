package com.appsv.herolist.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appsv.core.domain.ProgressBarState
import com.appsv.herolist.components.HeroListItem
import com.appsv.herolist.components.HeroListToolbar

@OptIn(ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun HeroListScreen(
    state : HeroListState,
    event : (HeroListEvents) -> Unit,
    navigateToHeroDetailScreen : (Int) ->  Unit
){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

    ){
        Column {
            val name = remember {
                mutableStateOf("")
            }

            HeroListToolbar(
                heroName = state.heroName,
                onHeroNameChanged = {heroName->
                     event(HeroListEvents.UpdateHeroName(heroName))
                },
                onExecuteSearch = {
                    event(HeroListEvents.FilterHeros)
                },
                onShowFilterDialog = {

                }
            )

            LazyColumn(
            ) {
                items(state.filteredHeros) { hero ->
                    HeroListItem(hero = hero, onSelectHero = {heroId ->
                        navigateToHeroDetailScreen(heroId)
                    })
                }
            }
        }
        if (state.progressBarState is ProgressBarState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}