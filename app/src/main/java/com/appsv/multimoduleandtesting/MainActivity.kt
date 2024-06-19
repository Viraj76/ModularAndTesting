package com.appsv.multimoduleandtesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.ui.Modifier
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

import com.appsv.hero_interactors.HeroInteractors
import com.appsv.multimoduleandtesting.navigation.SetNavGraph
import com.appsv.multimoduleandtesting.ui.theme.MultiModuleAndTestingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//                enableEdgeToEdge()
        val getHeros = HeroInteractors.build(
            sqlDriver =  AndroidSqliteDriver(
                schema = HeroInteractors.schema,
                context = this,
                name = HeroInteractors.dbName
            )
        ).getHeros

        setContent {

            MultiModuleAndTestingTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                   SetNavGraph()
                }

            }
        }
    }
}

