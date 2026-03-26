package com.minroud.mortyverse.feature.characters.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minroud.mortyverse.domain.result.DomainResult
import com.minroud.mortyverse.feature.characters.domain.error.CharacterError
import com.minroud.mortyverse.feature.characters.domain.model.MortyverseCharacterDetail
import com.minroud.mortyverse.feature.characters.domain.usecase.GetCharacterDetailUseCase
import com.minroud.mortyverse.feature.characters.ui.navigation.CharactersNavArgs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val getCharacterDetail: GetCharacterDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        val characterId = savedStateHandle.get<String>(CharactersNavArgs.CharacterId.key)
        if (characterId.isNullOrEmpty()) {
            _state.value = State(DomainResult.Error(CharacterError.InvalidId()))
        } else {
            getCharacterDetail(characterId = characterId)
        }
    }

    private fun getCharacterDetail(characterId: String) =
        viewModelScope.launch {
            _state.value = State(getCharacterDetail(GetCharacterDetailUseCase.Params(characterId)))
        }
}

data class State(
    val characterDetail: DomainResult<MortyverseCharacterDetail>? = null,
)
