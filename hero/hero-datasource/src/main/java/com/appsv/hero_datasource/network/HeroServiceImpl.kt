package com.appsv.hero_datasource.network

import com.appsv.hero_domain.Hero
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.server.util.url

class HeroServiceImpl(
    private val httpClient: HttpClient,
): HeroService {

    override suspend fun getHeroStats(): List<Hero> {
        return httpClient.get(EndPoints.HERO_STATS)
            .body<List<HeroDto>>()
            .map { it.toHero() }
    }

}