package com.minroud.mortyverse.feature.characters.data.di

import com.minroud.mortyverse.feature.characters.data.repository.CharacterRepositoryImpl
import com.minroud.mortyverse.feature.characters.domain.repository.CharacterRepository
import org.koin.core.module.Module

fun Module.charactersData() {
    factory<CharacterRepository> { CharacterRepositoryImpl(get()) }
}
