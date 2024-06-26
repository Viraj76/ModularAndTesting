package com.appsv.herodetail


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.appsv.hero_domain.Hero
import com.appsv.hero_domain.maxAttackDmg
import com.appsv.hero_domain.minAttackDmg

import kotlin.math.round

@Composable
fun HeroDetail(
    state: HeroDetailState,
) {
    state.hero?.let{ hero ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            item {
                Column {
//                    val painter = rememberImagePainter(
//                        hero.img,
//                        imageLoader = imageLoader,
//                        builder = {
//                            placeholder(if (isSystemInDarkTheme()) R.drawable.black_background else R.drawable.white_background)
//                        }
//                    )
//                    Image(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .defaultMinSize(minHeight = 200.dp),
//                        painter = ,
//                        contentDescription = hero.localizedName,
//                        contentScale = ContentScale.Crop,
//                    )
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 200.dp),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://compote.slate.com/images/22ce4663-4205-4345-8489-bc914da1f272.jpeg?crop=1560%2C1040%2Cx0%2Cy0&width=1280")
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(end = 8.dp),
                                text = hero.localizedName,
                                style = MaterialTheme.typography.bodyMedium,
                            )
//                            val iconPainter = rememberImagePainter(
//                                hero.icon,
//                                imageLoader = imageLoader,
//                                builder = {
//                                    placeholder(if (isSystemInDarkTheme()) R.drawable.black_background else R.drawable.white_background)
//                                }
//                            )
//                            Image(
//                                modifier = Modifier
//                                    .height(30.dp)
//                                    .width(30.dp)
//                                    .align(Alignment.CenterVertically),
//                                painter = iconPainter,
//                                contentDescription = hero.localizedName,
//                                contentScale = ContentScale.Crop,
//                            )
                            AsyncImage(
                                modifier = Modifier
                                    .height(30.dp)
                                    .width(30.dp)
                                    .align(Alignment.CenterVertically),
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data("https://compote.slate.com/images/22ce4663-4205-4345-8489-bc914da1f272.jpeg?crop=1560%2C1040%2Cx0%2Cy0&width=1280")
                                    .crossfade(true)
                                    .build(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                            )
                        }
                        Text(
                            modifier = Modifier
                                .padding(bottom = 4.dp),
                            text = hero.primaryAttribute.uiValue,
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Text(
                            modifier = Modifier
                                .padding(bottom = 12.dp),
                            text = hero.attackType.uiValue,
                            style = MaterialTheme.typography.titleMedium,
                        )
                        HeroBaseStats(
                            hero = hero,
                            padding = 10.dp,
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        WinPercentages(hero = hero,)
                    }
                }
            }
        }
    }
}

/**
 * Displays Pro wins % and Turbo wins %
 */
@Composable
fun WinPercentages(
    hero: Hero,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ){
        // Pro Win %
        Column(
            modifier = Modifier.fillMaxWidth(.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .align(Alignment.CenterHorizontally)
                ,
                text = "Pro Wins",
                style = MaterialTheme.typography.bodyLarge,
            )
            val proWinPercentage = remember {round(hero.proWins.toDouble() / hero.proPick.toDouble() * 100).toInt()}
            Text(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .align(Alignment.CenterHorizontally)
                ,
                text = "${proWinPercentage} %",
                style = MaterialTheme.typography.bodyMedium,
                color = if(proWinPercentage > 50) Color(0xFF009a34) else MaterialTheme.colorScheme.error
            )
        }
        // Turbo win %
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .align(Alignment.CenterHorizontally)
                ,
                text = "Turbo Wins",
                style = MaterialTheme.typography.titleMedium
            )
            val turboWinPercentage = remember {round(hero.turboWins.toDouble() / hero.turboPicks.toDouble() * 100).toInt()}
            Text(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .align(Alignment.CenterHorizontally)
                ,
                text = "${turboWinPercentage} %",
                style = MaterialTheme.typography.bodyLarge,
                color = if(turboWinPercentage > 50) Color(0xFF009a34) else MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun HeroBaseStats(
    hero: Hero,
    padding: Dp,
){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
        ,
        shape = MaterialTheme.shapes.medium
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
            ,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                ,
                text = "Base Stats",
                style = MaterialTheme.typography.bodyLarge,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(end = 20.dp)
                ){ // Str, Agi, Int, Health
                    Row( // STR
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                        ,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            text = "${stringResource(R.string.strength)}:",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Row {
                            Text(
                                text = "${hero.baseStr}",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                            Text(
                                text = " + ${hero.strGain}",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                    Row( // AGI
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                        ,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            text = "${stringResource(R.string.agility)}:",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Row{
                            Text(
                                text = "${hero.baseAgi}",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                            Text(
                                text = " + ${hero.agiGain}",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                    Row( // INT
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                        ,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            text = "${stringResource(R.string.intelligence)}:",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Row{
                            Text(
                                text = "${hero.baseInt}",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                            Text(
                                text = " + ${hero.intGain}",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                    Row( // HP
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                        ,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            text = "${stringResource(R.string.health)}:",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        val health = remember{round(hero.baseHealth + hero.baseStr * 20).toInt()}
                        Text(
                            text = "${health}",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ){ // Atk Range, proj speed, move speed, atk dmg
                    Row( // Atk Range
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                            ,
                            text = "${stringResource(R.string.attack_range)}:",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Text(
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                            ,
                            text = "${hero.attackRange}",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    Row( // projectile speed
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                            ,
                            text = "${stringResource(R.string.projectile_speed)}:",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Text(
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                            ,
                            text = "${hero.projectileSpeed}",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    Row( // Move speed
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                            ,
                            text = "${stringResource(R.string.move_speed)}:",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Text(
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                            ,
                            text = "${hero.moveSpeed}",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    Row( // Attack damage
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                            ,
                            text = "${stringResource(R.string.attack_dmg)}:",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        val atkMin = remember{hero.minAttackDmg()}
                        val atkMax = remember{hero.maxAttackDmg()}
                        Text(
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                            ,
                            text = "${atkMin} - ${atkMax}",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
        }
    }
}