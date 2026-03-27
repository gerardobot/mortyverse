package com.minroud.mortyverse.feature.characters.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minroud.mortyverse.domain.error.DomainError
import com.minroud.mortyverse.feature.characters.domain.model.MortyverseCharacter
import com.minroud.mortyverse.feature.characters.domain.usecase.GetCharacterPageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class CharacterListViewModel(
    private val getCharacterPage: GetCharacterPageUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()
    private val fetchMutex = Mutex()

    init {
        loadInitialCharacters()
    }

    fun onScrollEnd() {
        if (state.value.canAppend) appendCharacters()
    }

    private fun loadInitialCharacters() {
        viewModelScope.launch {
            fetchMutex.withLock {
                _state.update {
                    it.copy(
                        characterItems = emptyList(),
                        nextPage = 1,
                        isInitialLoading = true,
                        isAppending = false,
                        error = null,
                    )
                }
                getCharacterPage(GetCharacterPageUseCase.Params(1))
                    .onSuccess { page ->
                        _state.update {
                            it.copy(
                                characterItems = page.items,
                                nextPage = page.nextPage,
                                error = null,
                            )
                        }
                    }
                    .onError { error ->
                        _state.update { it.copy(error = error) }
                    }
                _state.update { it.copy(isInitialLoading = false) }
            }
        }
    }

    private fun appendCharacters() {
        viewModelScope.launch {
            fetchMutex.withLock {
                if (state.value.isAppending || state.value.isLastPage) return@withLock
                val pageToLoad = state.value.nextPage ?: return@withLock
                _state.update { it.copy(isAppending = true) }
                getCharacterPage(GetCharacterPageUseCase.Params(pageToLoad))
                    .onSuccess { page ->
                        _state.update {
                            it.copy(
                                characterItems = it.characterItems + page.items,
                                nextPage = page.nextPage,
                            )
                        }
                    }
                _state.update { it.copy(isAppending = false) }
            }
        }
    }

    data class State(
        val characterItems: List<MortyverseCharacter> = listOf(),
        val nextPage: Int? = 1,
        val error: DomainError? = null,
        val isInitialLoading: Boolean = false,
        val isAppending: Boolean = false,
    ) {
        val isLastPage get() = nextPage == null
        val canAppend = !isInitialLoading && !isAppending && !isLastPage
    }
}
