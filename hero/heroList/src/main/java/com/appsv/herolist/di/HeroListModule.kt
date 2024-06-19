package com.appsv.herolist.di

import com.appsv.hero_interactors.GetHeros
import com.appsv.hero_interactors.HeroInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeroListModule {

    /**
     * @param interactors is provided in app module.
     */
    @Provides
    @Singleton
    fun provideGetHeros(
        interactors: HeroInteractors
    ): GetHeros {
        return interactors.getHeros
    }

//    @Provides
//    @Singleton
//    fun provideFilterHeros(
//        interactors: HeroInteractors
//    ): FilterHeros {
//        return interactors.filterHeros
//    }
}