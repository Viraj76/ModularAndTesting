package com.appsv.multimoduleandtesting.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument


sealed class Screen(val route: String, val arguments: List<NamedNavArgument>){

    data object HeroList: Screen(
        route = "heroList",
        arguments = emptyList()
    )

    data object HeroDetail: Screen(
        route = "heroDetail",
        arguments = listOf(navArgument("heroId") {
            type = NavType.IntType
        })
    )
}