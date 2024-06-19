package com.appsv.multimoduleandtesting.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.appsv.herodetail.HeroDetail
import com.appsv.herodetail.HeroDetailViewModel
import com.appsv.herolist.ui.HeroListScreen
import com.appsv.herolist.viewmodle.HeroListViewModel


@Composable
fun  SetNavGraph() {

    val navController = rememberNavController()

    
    NavHost(navController = navController, startDestination = Screen.HeroList.route){


        composable(route = Screen.HeroList.route){
            val viewModel : HeroListViewModel = hiltViewModel()
            HeroListScreen(
                state = viewModel.state.value,
                event = viewModel::onEvent
            ){heroId->
                navController.navigate("${Screen.HeroDetail.route}/$heroId")
            }
        }
        composable(
            route = Screen.HeroDetail.route + "/{heroId}",
            arguments = Screen.HeroDetail.arguments

        ){
            val heroDetailViewModel : HeroDetailViewModel= hiltViewModel()
            HeroDetail(heroDetailViewModel.state.value)
        }
    }
}