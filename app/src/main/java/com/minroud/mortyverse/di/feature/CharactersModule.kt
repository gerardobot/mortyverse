package com.minroud.mortyverse.di.feature

import com.minroud.mortyverse.feature.characters.data.di.charactersData
import com.minroud.mortyverse.feature.characters.domain.di.charactersDomain
import com.minroud.mortyverse.feature.characters.infra.remote.di.charactersRemote
import com.minroud.mortyverse.feature.characters.ui.di.charactersUi
import org.koin.core.module.Module

fun Module.characters() {
    charactersData()
    charactersDomain()
    charactersRemote()
    charactersUi()
}
