package com.minroud.mortyverse.feature.demo.ui.screens.errors.unknown

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
internal fun UnknownErrorScreen(topBarButton: TopBarButton) = UnknownError(topBarButton = topBarButton)

@Composable
internal fun UnknownError(
    topBarButton: TopBarButton,
    modifier: Modifier = Modifier,
) = MortyverseScaffold(
    button = topBarButton,
    title = stringResource(id = R.string.error_unknown_title),
    modifier = modifier,
) { padding ->
    ErrorMessage(DomainError.Unknown(), modifier = Modifier.padding(padding))
}

@Preview(showBackground = true)
@Composable
private fun UnknownErrorPreview() {
    MortyverseTheme {
        UnknownError(topBarButton = TopBarButton.Back {})
    }
}
