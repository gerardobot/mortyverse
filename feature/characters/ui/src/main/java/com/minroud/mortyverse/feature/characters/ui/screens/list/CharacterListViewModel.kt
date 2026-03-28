package com.minroud.mortyverse.feature.characters.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.minroud.mortyverse.feature.characters.domain.usecase.GetCharacterPageUseCase
import com.minroud.mortyverse.feature.characters.ui.screens.list.paging.CharactersPagingSource
import com.minroud.mortyverse.feature.characters.ui.screens.list.paging.pageSize
import com.minroud.mortyverse.feature.characters.ui.screens.list.paging.prefetchDistance

internal class CharacterListViewModel(
    private val getCharacterPage: GetCharacterPageUseCase,
) : ViewModel() {
    val characters = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = prefetchDistance,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = { CharactersPagingSource(getCharacterPage) },
    ).flow.cachedIn(viewModelScope)
}
