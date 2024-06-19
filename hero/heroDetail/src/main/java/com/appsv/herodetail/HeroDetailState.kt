package com.appsv.herodetail

import com.appsv.core.domain.ProgressBarState
import com.appsv.hero_domain.Hero

data class HeroDetailState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val hero: Hero? = null,
)