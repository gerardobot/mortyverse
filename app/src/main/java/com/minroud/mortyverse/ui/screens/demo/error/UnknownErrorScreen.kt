package com.minroud.mortyverse.ui.screens.demo.error

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.minroud.mortyverse.R
import com.minroud.mortyverse.domain.result.error.DomainError
import com.minroud.mortyverse.ui.error.ErrorMessage
import com.minroud.mortyverse.ui.navigation.TopBar
import com.minroud.mortyverse.ui.navigation.TopBarButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnknownErrorScreen(
    topBarButton: TopBarButton
) = Scaffold(
    topBar = {
        TopBar(
            button = topBarButton,
            title = stringResource(id = R.string.error_unknown_title)
        )
    }
) { padding ->
    ErrorMessage(
        DomainError.Unknown(),
        modifier = Modifier.padding(padding)
    )
}
