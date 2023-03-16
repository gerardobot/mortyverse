package com.minroud.mortyverse.ui.screens.character.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minroud.mortyverse.domain.entities.character.MortyverseCharacterDetail
import com.minroud.mortyverse.domain.result.error.DomainError
import com.minroud.mortyverse.domain.usecases.characters.GetCharacterDetailUseCase
import com.minroud.mortyverse.ui.navigation.NavArg
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val getCharacterDetail: GetCharacterDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        val characterId = savedStateHandle.get<String>(NavArg.CharacterId.key)
        if (characterId.isNullOrEmpty()) {
            _state.update {
                it.copy(error = DomainError.CharacterDetail.InvalidId())
            }
        } else {
            getCharacterDetail(characterId = characterId)
        }
    }

    private fun getCharacterDetail(characterId: String) = viewModelScope.launch {
        getCharacterDetail(GetCharacterDetailUseCase.Params(characterId))
            .onSuccess { characterDetail ->
                _state.update {
                    it.copy(
                        characterDetail = characterDetail
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        error = error
                    )
                }
            }
    }
}

data class State(
    val characterDetail: MortyverseCharacterDetail? = null,
    val error: DomainError? = null
) {
    val isLoading = characterDetail == null && error == null
}
