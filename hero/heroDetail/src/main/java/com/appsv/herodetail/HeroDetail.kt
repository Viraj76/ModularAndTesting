package com.appsv.herodetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HeroDetail(
    heroId : Int
){
    Text(
        text = heroId.toString()
    )
}