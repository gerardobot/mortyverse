package com.minroud.mortyverse.feature.characters.ui.screens.list.paging

import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

internal const val pageSize = 20
internal const val prefetchDistance = 2
internal const val defaultPage = 1
internal val appendRetryBaseDelay = 1500.milliseconds
internal val appendRetryMaxDelay = 8.seconds
