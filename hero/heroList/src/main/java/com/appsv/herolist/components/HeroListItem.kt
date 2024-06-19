package com.appsv.herolist.components


import android.view.Surface
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest

import com.android.volley.toolbox.ImageLoader
import com.appsv.hero_domain.Hero
import com.appsv.herolist.R
import com.appsv.herolist.ui.test.TAG_HERO_NAME
import com.appsv.herolist.ui.test.TAG_HERO_PRIMARY_ATTRIBUTE

import kotlin.math.round

@ExperimentalAnimationApi
@Composable
fun HeroListItem(
    hero: Hero,
    onSelectHero: (Int) -> Unit,
//    imageLoader: ImageLoader,
){

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .background(MaterialTheme.colorScheme.surface)
            .clickable {
                onSelectHero(hero.id)
            }
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            verticalAlignment = Alignment.CenterVertically
        ){

val painter = rememberAsyncImagePainter(model =  hero.img,)
            AsyncImage(
                modifier = Modifier
                    .width(120.dp)
                    .height(70.dp),

                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://compote.slate.com/images/22ce4663-4205-4345-8489-bc914da1f272.jpeg?crop=1560%2C1040%2Cx0%2Cy0&width=1280")
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(.8f) // fill 80% of remaining width
                    .padding(start = 12.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .testTag(TAG_HERO_NAME)
                    ,
                    text = hero.localizedName,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier
                        .testTag(TAG_HERO_PRIMARY_ATTRIBUTE)
                    ,
                    text = hero.primaryAttribute.uiValue,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth() // Fill the rest of the width (100% - 80% = 20%)
                    .padding(end = 12.dp)
                ,
                horizontalAlignment = Alignment.End
            ) {
                // Using remember in list item does not behave correctly?
//                val proWR: Int = remember{round(hero.proWins.toDouble() / hero.proPick.toDouble() * 100).toInt()}
                val proWR: Int = round(hero.proWins.toDouble() / hero.proPick.toDouble() * 100).toInt()
                Text(
                    text = "${proWR}%",
                    style = MaterialTheme.typography.titleMedium,
                    color = if(proWR > 50) Color(0xFF009a34) else MaterialTheme.colorScheme.error,
                )
            }
        }
    }
}
















