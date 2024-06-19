package com.appsv.herodetail



sealed class HeroDetailEvents {

    data class GetHeroFromCache(val id: Int, ) : HeroDetailEvents()

}