package com.minroud.mortyverse.feature.characters.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.minroud.mortyverse.feature.characters.domain.usecase.GetCharacterPageUseCase
import com.minroud.mortyverse.feature.characters.ui.screens.list.paging.CharactersPagingSource

internal class CharacterListViewModel(
    private val getCharacterPage: GetCharacterPageUseCase,
) : ViewModel() {
    val characters = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = { CharactersPagingSource(getCharacterPage) },
    ).flow.cachedIn(viewModelScope)

    private companion object {
        const val PAGE_SIZE = 20
        const val PREFETCH_DISTANCE = 2
    }
}
