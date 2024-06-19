package com.appsv.core.domain

sealed class FilterOrder {

    data object Ascending: FilterOrder()

    data object Descending: FilterOrder()
}