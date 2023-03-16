package com.minroud.mortyverse.ui.navigation

sealed class Route(val name: String) {
    object CharacterList : Route("character-list")
    object CharacterDetail : Route("character-detail/{${NavArg.CharacterId.key}}")
    object NetworkError : Route("network-error")
    object UnknownError : Route("unknown-error")

    fun getRouteWithArgs(vararg valuePair: Pair<NavArg, String>): String =
        valuePair.fold(name) { acc, (key, value) -> acc.replace("{${key.key}}", value) }
}
