package com.minroud.mortyverse.di

import com.minroud.mortyverse.data.repositories.CharacterRepositoryImpl
import com.minroud.mortyverse.domain.repositories.CharacterRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<CharacterRepository> { CharacterRepositoryImpl(get()) }
}
