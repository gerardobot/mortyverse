package com.minroud.mortyverse.feature.characters.domain.di
import com.minroud.mortyverse.feature.characters.domain.usecase.GetCharacterDetailUseCase
import com.minroud.mortyverse.feature.characters.domain.usecase.GetCharacterPageUseCase
import com.minroud.mortyverse.feature.characters.domain.usecase.GetCharactersUseCase
import org.koin.core.module.Module

fun Module.charactersDomain() {
    factory { GetCharactersUseCase(get()) }
    factory { GetCharacterPageUseCase(get()) }
    factory { GetCharacterDetailUseCase(get()) }
}
