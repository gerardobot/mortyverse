package com.minroud.mortyverse.di

import com.minroud.mortyverse.ui.screens.character.detail.CharacterDetailViewModel
import com.minroud.mortyverse.ui.screens.character.list.CharacterListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CharacterListViewModel(get()) }
    viewModel { CharacterDetailViewModel(get(), get()) }
}
