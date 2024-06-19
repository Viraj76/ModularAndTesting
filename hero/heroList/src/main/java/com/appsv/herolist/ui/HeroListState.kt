package com.appsv.herolist.ui

import com.appsv.core.domain.ProgressBarState
import com.appsv.hero_domain.Hero


data class HeroListState(
    var progressBarState: ProgressBarState = ProgressBarState.Idle,
    val heros: List<Hero> = listOf(),
    val filteredHeros: List<Hero> = listOf(),
//    val filterDialogState: UIComponentState = UIComponentState.Hide, // show/hide the filter dialog
//    val heroFilter: HeroFilter = HeroFilter.Hero(FilterOrder.Descending),
//    val primaryAttrFilter: HeroAttribute = HeroAttribute.Unknown,
    val heroName: String = "",
//    val errorQueue: Queue<UIComponent> = Queue(mutableListOf())
)