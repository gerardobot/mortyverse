package com.minroud.mortyverse.ui.screens.character.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.minroud.mortyverse.ui.ext.titleAsStringRes
import com.minroud.mortyverse.ui.common.AsyncContent
import com.minroud.mortyverse.ui.navigation.TopBar
import com.minroud.mortyverse.ui.navigation.TopBarButton
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetail(
    topBarButton: TopBarButton,
    viewModel: CharacterDetailViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                button = topBarButton,
                title = state.characterDetail?.errorOrNull()?.titleAsStringRes?.let {
                    stringResource(it)
                }.orEmpty()
            )
        }
    ) { padding ->
        AsyncContent(result = state.characterDetail, modifier = Modifier.padding(padding)) {
            CharacterBio(
                characterDetail = it,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            )
        }
    }
}
