package com.minroud.mortyverse.ui.screens.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minroud.mortyverse.domain.entities.character.MortyverseCharacter
import com.minroud.mortyverse.domain.result.error.DomainError
import com.minroud.mortyverse.domain.usecases.characters.GetCharacterPageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val getCharacterPage: GetCharacterPageUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        getNextCharacterPage()
    }

    fun onScrollEnd() {
        if (state.value.canGetNextPage) getNextCharacterPage()
    }

    private fun getNextCharacterPage() = state.value.nextPage?.let {
        viewModelScope.launch {
            _state.update { it.copy(isGettingNextCharacterPage = true) }
            getCharacterPage(GetCharacterPageUseCase.Params(it))
                .onSuccess { page ->
                    _state.update {
                        it.copy(
                            characterItems = state.value.characterItems + page.items,
                            nextPage = page.nextPage
                        )
                    }
                }
                .onError { error ->
                    if (state.value.characterItems.isEmpty()) {
                        _state.update { it.copy(error = error) }
                    }
                }
            _state.update { it.copy(isGettingNextCharacterPage = false) }
        }
    }

    data class State(
        val characterItems: List<MortyverseCharacter> = listOf(),
        val nextPage: Int? = 1,
        val error: DomainError? = null,
        val isGettingNextCharacterPage: Boolean = false
    ) {
        val isLoading = characterItems.isEmpty() && error == null
        val isLastPage = nextPage == null
        val canGetNextPage = !isLoading && !isGettingNextCharacterPage && !isLastPage
    }
}
