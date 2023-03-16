package com.minroud.mortyverse.di

import com.minroud.mortyverse.domain.usecases.characters.GetCharacterDetailUseCase
import com.minroud.mortyverse.domain.usecases.characters.GetCharacterPageUseCase
import com.minroud.mortyverse.domain.usecases.characters.GetCharactersUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetCharactersUseCase(get()) }
    factory { GetCharacterPageUseCase(get()) }
    factory { GetCharacterDetailUseCase(get()) }
}
