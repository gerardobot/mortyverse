package com.minroud.mortyverse.infra.remote.rickandmorty

sealed interface RickAndMortyEndpoint {
    data object Character : RickAndMortyEndpoint {
        private const val path = "api/character"

        const val page = path
        const val detail = "$path/{${Param.detail}}"

        object Query {
            const val page = "page"
        }

        object Param {
            const val detail = "id"
        }
    }

    companion object {
        const val base = "https://rickandmortyapi.com/"
    }
}

