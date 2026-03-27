package com.minroud.mortyverse.feature.demo.ui.screens.errors.network

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.minroud.mortyverse.domain.error.DomainError
import com.minroud.mortyverse.ui.R
import com.minroud.mortyverse.ui.containers.MortyverseScaffold
import com.minroud.mortyverse.ui.error.ErrorMessage
import com.minroud.mortyverse.ui.theme.MortyverseTheme
import com.minroud.mortyverse.ui.topbar.TopBarButton

@Composable
internal fun NetworkErrorScreen(topBarButton: TopBarButton) = NetworkError(topBarButton = topBarButton)

@Composable
internal fun NetworkError(
    topBarButton: TopBarButton,
    modifier: Modifier = Modifier,
) = MortyverseScaffold(
    button = topBarButton,
    title = stringResource(id = R.string.error_network_title),
    modifier = modifier,
) { padding ->
    ErrorMessage(DomainError.Network(), modifier = Modifier.padding(padding))
}

@Preview(showBackground = true)
@Composable
private fun NetworkErrorPreview() {
    MortyverseTheme {
        NetworkError(topBarButton = TopBarButton.Back {})
    }
}
