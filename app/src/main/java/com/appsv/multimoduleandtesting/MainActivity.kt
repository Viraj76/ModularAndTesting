package com.appsv.multimoduleandtesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

import com.appsv.core.domain.DataState
import com.appsv.core.domain.ProgressBarState
import com.appsv.core.domain.UIComponent
import com.appsv.core.utils.Logger
import com.appsv.hero_domain.Hero
import com.appsv.hero_interactors.HeroInteractors
import com.appsv.herolist.ui.HeroList
import com.appsv.herolist.ui.HeroListState
import com.appsv.herolist.viewmodle.HeroListViewModel
import com.appsv.multimoduleandtesting.ui.theme.MultiModuleAndTestingTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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
                    val viewModel : HeroListViewModel = hiltViewModel()
                   HeroList(state = viewModel.state.value)
                }

            }



        }
    }
}

