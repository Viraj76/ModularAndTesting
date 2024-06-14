package com.appsv.hero_interactors

import com.appsv.hero_datasource.network.HeroService


data class HeroInteractors(
    val getHeros: GetHeros,
    // TODO(Add other hero interactors)
) {

    // add Dependency injection

    companion object Factory {
        fun build(): HeroInteractors{
            val service = HeroService.build()
            return HeroInteractors(
                getHeros = GetHeros(
                    service = service,
                ),
            )
        }
    }
}