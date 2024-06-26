package com.appsv.herolist.viewmodle

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsv.core.domain.DataState
import com.appsv.core.domain.UIComponent
import com.appsv.core.utils.Logger
import com.appsv.hero_domain.Hero
import com.appsv.hero_interactors.GetHeros
import com.appsv.herolist.ui.HeroListState
import com.appsv.herolist.ui.HeroListEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val getHeros: GetHeros
)  : ViewModel(){

    val logger = Logger("GetHerosTest")
     val state: MutableState<HeroListState> = mutableStateOf(HeroListState())

    init {
        onEvent(event = HeroListEvents.GetHeroes)
    }
     fun onEvent(event : HeroListEvents){
        when(event){
            is HeroListEvents.GetHeroes ->{
                getHeroes()
            }
            is HeroListEvents.UpdateHeroName -> {
                updateHeroName(event.heroName)
            }
            is HeroListEvents.FilterHeros -> {
                filterHeros()
            }
        }
    }

    private fun filterHeros(){
        val filteredList: MutableList<Hero> = state.value.heros.filter {
            it.localizedName.lowercase().contains(state.value.heroName.lowercase())
        }.toMutableList()

        state.value = state.value.copy(filteredHeros = filteredList)

    }

    private fun updateHeroName(heroName: String) {
        state.value = state.value.copy(heroName = heroName)
    }
    private fun getHeroes(){
        getHeros.execute().onEach { dataState ->
            when(dataState){
                is DataState.Response -> {
                    when(dataState.uiComponent){
                        is UIComponent.Dialog -> {
                            logger.log((dataState.uiComponent as UIComponent.Dialog).description)
                        }
                        is UIComponent.None -> {
                            logger.log((dataState.uiComponent as UIComponent.None).message)
                        }
                    }
                }
                is DataState.Data -> {
                    state.value = state.value.copy(heros = dataState.data?: listOf())
                    filterHeros()
                }
                is DataState.Loading -> {
                    state.value = state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

}