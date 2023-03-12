package com.minroud.mortyverse.framework.datasource.remote.rickandmorty

import com.minroud.mortyverse.framework.datasource.remote.BaseUrls
import com.minroud.mortyverse.framework.datasource.remote.ServiceInstanceCreator

class RickAndMortyApiService

fun createRickAndMortyApiServiceInstance(
    serviceInstanceCreator: ServiceInstanceCreator
): RickAndMortyApiService = serviceInstanceCreator.create(BaseUrls.rickAndMorty)
