package com.appsv.hero_domain


sealed class HeroAttackType(
    val uiValue: String,
){

    data object Melee: HeroAttackType(
        uiValue = "Melee",
    )

    data object Ranged: HeroAttackType(
        uiValue = "Ranged",
    )

    data object Unknown: HeroAttackType(
        uiValue = "Unknown",
    )
}

fun getHeroAttackType(uiValue: String): HeroAttackType{
    return when(uiValue){
        HeroAttackType.Melee.uiValue -> {
            HeroAttackType.Melee
        }
        HeroAttackType.Ranged.uiValue -> {
            HeroAttackType.Ranged
        }
        else -> {
            HeroAttackType.Unknown
        }
    }
}