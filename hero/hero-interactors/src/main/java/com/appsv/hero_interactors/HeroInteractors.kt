package com.appsv.hero_interactors

import app.cash.sqldelight.db.SqlDriver
import com.appsv.hero_datasource.cache.HeroCache
import com.appsv.hero_datasource.network.HeroService


data class HeroInteractors(
    val getHeros: GetHeros,
    // TODO(Add other hero interactors)
) {

    // add Dependency injection

    companion object Factory {
        fun build(sqlDriver: SqlDriver): HeroInteractors{
            val service = HeroService.build()
            val cache = HeroCache.build(sqlDriver)
            return HeroInteractors(
                getHeros = GetHeros(
                    service = service,
                    cache = cache
                ),
            )
        }
    }
}