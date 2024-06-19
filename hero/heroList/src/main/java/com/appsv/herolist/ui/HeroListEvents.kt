package com.appsv.herolist.ui

sealed class HeroListEvents {

    data object GetHeroes : HeroListEvents()

    object FilterHeros: HeroListEvents()

    data class UpdateHeroName(
        val heroName: String,
    ): HeroListEvents()

}