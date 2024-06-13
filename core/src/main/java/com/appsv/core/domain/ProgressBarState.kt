package com.appsv.core.domain

sealed class ProgressBarState{

    data object Loading: ProgressBarState()

    data object Idle: ProgressBarState()
}