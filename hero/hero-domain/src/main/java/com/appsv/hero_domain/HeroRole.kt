package com.appsv.hero_domain


sealed class HeroRole(
    val uiValue: String,
){

    data object Carry: HeroRole(
        uiValue = "Carry"
    )

    data object Escape: HeroRole(
        uiValue = "Escape"
    )

    data object Nuker: HeroRole(
        uiValue = "Nuker"
    )

    data object Initiator: HeroRole(
        uiValue = "Initiator"
    )

    data object Durable: HeroRole(
        uiValue = "Durable"
    )

    data object Disabler: HeroRole(
        uiValue = "Disabler"
    )

    data object Jungler: HeroRole(
        uiValue = "Jungler"
    )

    data object Support: HeroRole(
        uiValue = "Support"
    )

    data object Pusher: HeroRole(
        uiValue = "Pusher"
    )

    data object Unknown: HeroRole(
        uiValue = "Unknown"
    )
}



fun getHeroRole(uiValue: String): HeroRole{
    return when(uiValue){
        HeroRole.Carry.uiValue -> {
            HeroRole.Carry
        }
        HeroRole.Escape.uiValue -> {
            HeroRole.Escape
        }
        HeroRole.Nuker.uiValue -> {
            HeroRole.Nuker
        }
        HeroRole.Initiator.uiValue -> {
            HeroRole.Initiator
        }
        HeroRole.Durable.uiValue -> {
            HeroRole.Durable
        }
        HeroRole.Disabler.uiValue -> {
            HeroRole.Disabler
        }
        HeroRole.Jungler.uiValue -> {
            HeroRole.Jungler
        }
        HeroRole.Support.uiValue -> {
            HeroRole.Support
        }
        HeroRole.Pusher.uiValue -> {
            HeroRole.Pusher
        }
        else -> {
            HeroRole.Unknown
        }
    }
}










