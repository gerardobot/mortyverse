package com.minroud.mortyverse.di.core

import com.minroud.mortyverse.infra.remote.di.infraRemote
import com.minroud.mortyverse.infra.ui.di.infraUi
import org.koin.core.module.Module

fun Module.infra() {
    infraRemote()
    infraUi()
}
