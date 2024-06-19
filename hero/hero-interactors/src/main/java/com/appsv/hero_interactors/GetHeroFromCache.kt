package com.appsv.hero_interactors

import com.appsv.core.domain.DataState
import com.appsv.core.domain.ProgressBarState
import com.appsv.core.domain.UIComponent
import com.appsv.hero_datasource.cache.HeroCache
import com.appsv.hero_domain.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Retrieve a hero from the cache using the hero's unique id
 */
class GetHeroFromCache(
    private val cache: HeroCache
) {

    fun execute(
        id: Int,
    ): Flow<DataState<Hero>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

            // emit data from network
            val cachedHero = cache.getHero(id) ?: throw Exception("That hero does not exist in the cache.")

            emit(DataState.Data(cachedHero))
        }catch (e: Exception){
            e.printStackTrace()
            emit(DataState.Response<Hero>(
                uiComponent = UIComponent.Dialog(
                    title = "Error",
                    description = e.message?: "Unknown error"
                )
            ))
        }
        finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}