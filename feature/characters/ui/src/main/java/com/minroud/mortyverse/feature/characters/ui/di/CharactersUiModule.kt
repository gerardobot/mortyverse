package com.minroud.mortyverse.feature.characters.ui.di

import com.minroud.mortyverse.feature.characters.ui.error.CharactersFeatureErrorRenderer
import com.minroud.mortyverse.feature.characters.ui.screens.detail.CharacterDetailViewModel
import com.minroud.mortyverse.feature.characters.ui.screens.list.CharacterListViewModel
import com.minroud.mortyverse.ui.error.FeatureErrorRenderer
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel

fun Module.charactersUi() {
    factory<FeatureErrorRenderer> { CharactersFeatureErrorRenderer() }
    viewModel { CharacterListViewModel(get()) }
    viewModel { CharacterDetailViewModel(get(), get()) }
}
