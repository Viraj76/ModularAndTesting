package com.appsv.hero_datasource.network

import com.appsv.hero_domain.Hero
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import kotlinx.serialization.json.Json


interface HeroService {

    suspend fun getHeroStats(): List<Hero>

    companion object Factory {
        fun build(): HeroService {
            return HeroServiceImpl(
                httpClient = HttpClient(Android) {
                    install(ContentNegotiation) {
                        Json {
                            ignoreUnknownKeys =
                                true // if the server sends extra fields, ignore them
                        }
                    }
                }
            )
        }
    }
}