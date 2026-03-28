package com.minroud.mortyverse.feature.characters.ui.screens.list.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.minroud.mortyverse.feature.characters.domain.model.MortyverseCharacter
import com.minroud.mortyverse.feature.characters.domain.usecase.GetCharacterPageUseCase

class CharactersPagingSource(
    private val getCharacterPage: GetCharacterPageUseCase,
) : PagingSource<Int, MortyverseCharacter>() {
    override fun getRefreshKey(state: PagingState<Int, MortyverseCharacter>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val closestPage = state.closestPageToPosition(anchorPosition) ?: return null
        return closestPage.prevKey?.plus(1) ?: closestPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MortyverseCharacter> {
        val pageNumber = params.key ?: defaultPage
        return getCharacterPage(GetCharacterPageUseCase.Params(pageNumber))
            .fold(
                onSuccess = { page ->
                    LoadResult.Page(
                        data = page.items,
                        prevKey = page.previousPage,
                        nextKey = page.nextPage,
                    )
                },
                onError = { LoadResult.Error(CharactersPagingException(it)) },
            )
    }
}
