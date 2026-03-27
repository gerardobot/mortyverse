package com.minroud.mortyverse.ui.navigation

fun buildRoute(
    template: String,
    vararg valuePair: Pair<NavArg<*>, String>,
): String =
    valuePair.fold(template) { acc, (key, value) ->
        acc.replace("{${key.key}}", value)
    }
